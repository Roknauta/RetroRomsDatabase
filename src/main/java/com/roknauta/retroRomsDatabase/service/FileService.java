package com.roknauta.retroRomsDatabase.service;

import com.roknauta.retroRomsDatabase.domain.Sistema;
import com.roknauta.retroRomsDatabase.domain.noIntro.Game;
import com.roknauta.retroRomsDatabase.utils.AppFileUtils;
import com.roknauta.retroRomsDatabase.utils.AppUtils;
import com.roknauta.retroRomsDatabase.utils.CompressUtils;
import com.roknauta.retroRomsDatabase.utils.DetalheExecucao;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FileService {

    private static final String PACKS = "/run/media/douglas/Games/Emulation/Packs/Prontos/";
    private static final String DEST_BASE = "/home/douglas/roms";
    private static final String ROMS_HOME = "/home/douglas/roms";
    private static final String ROMS_SELECIONADAS_FOLDER = "roms-selecionadas";
    private static final String ROMS_EXTRAIDAS_FOLDER = "roms-extraidas";

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private RomRepository romRepository;

    public void extrairDePacks(Sistema sistema) throws IOException {
        DetalheExecucao detalheExecucao = new DetalheExecucao(sistema.getName());
        String destinoSistema = DEST_BASE + File.separator + sistema.getName();
        FileUtils.deleteDirectory(new File(destinoSistema));
        String destinoRoms = destinoSistema + File.separator + ROMS_EXTRAIDAS_FOLDER;
        criarTarget(destinoRoms);

        Set<String> hashsMd5 = romRepository.findMd5(sistema.getName());
        Predicate<File> arquivoValido =
            file -> hashsMd5.contains(AppUtils.getMd5(file))&&sistema.getExtensions().contains(FilenameUtils.getExtension(file.getName()).toLowerCase());
        for (File file : FileUtils.listFiles(new File(PACKS + File.separator + sistema.getName()), null, true)) {
            if (CompressUtils.isCompactedFile(file)) {
                CompressUtils.descompactarArquivoCompactado(file, destinoRoms, arquivoValido, detalheExecucao);
            } else if (arquivoValido.test(file)) {
                AppUtils.renomearParaCheckSumMd5(file, destinoRoms);
            }else{
                detalheExecucao.getArquivosIgnorados().add(file.getName());
            }
        }
        finalizarExecucao(detalheExecucao, destinoSistema);
        System.out.println("Finalizado o sistema: " + sistema.name());
    }

    private void finalizarExecucao(DetalheExecucao detalheExecucao, String destino) throws IOException {
        detalheExecucao.setFim(LocalDateTime.now());
        File resumo = new File(destino + File.separator + "Info da Execução.txt");
        resumo.createNewFile();
        String builder = "Duração total da execução: " + AppUtils.formatDuration(
            Duration.between(detalheExecucao.getInicio(),
                detalheExecucao.getFim())) + "\n\n" + "Arquivos com erro de descompactar:\n" + String.join("\n",
            detalheExecucao.getArquivosComErro()) + "\n\n" + "Arquivos ignorados:\n" + String.join("\n",
            detalheExecucao.getArquivosIgnorados()) + "\n";
        Files.writeString(Paths.get(resumo.getPath()), builder, StandardCharsets.UTF_8);
    }

    private void criarTarget(String targetDir) {
        File diretorio = new File(targetDir);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void filtrarArquivosPreferenciais(Sistema sistema) throws IOException {
        File[] roms =
            Objects.requireNonNull(AppUtils.getFolderFromPath(ROMS_HOME, sistema.getName(), ROMS_EXTRAIDAS_FOLDER).listFiles());
        Map<Long, File> escolhidos = new HashMap<>();
        Set<Long> processados = new HashSet<>();
        for (File rom : roms) {
            List<Game> gamesInfo = gameRepository.findByRom_Md5(AppUtils.getMd5(rom));
            if (CollectionUtils.isNotEmpty(gamesInfo)) {
                if(gamesInfo.size() > 1) {
                    System.out.println("Mais de um retorno..."+gamesInfo.stream().map(Game::getId).toList());
                }
                Game gameInfo = gamesInfo.getFirst();
                List<Game> gamesRelacionados =
                    gameRepository.findBySistemaAndOrNumberAndClone(sistema.getName(), StringUtils.firstNonEmpty(gameInfo.getCloneOfGameId(),gameInfo.getGameId()));
                Game preferencial = obterPreferencial(gamesRelacionados);
                if (preferencial != null && !escolhidos.containsKey(preferencial.getId())&&!processados.contains(preferencial.getId())) {
                    escolhidos.put(preferencial.getId(), rom);
                    processados.addAll(gamesRelacionados.stream().map(Game::getId).collect(Collectors.toSet()));
                }
            } else {
                //rom.deleteOnExit();
            }
        }
        File folderRomsEscolhidas = AppUtils.getFolderFromPath(ROMS_HOME, sistema.getName(), ROMS_SELECIONADAS_FOLDER);
        FileUtils.deleteDirectory(folderRomsEscolhidas);
        folderRomsEscolhidas.mkdir();
        escolhidos.forEach((idGame, rom) -> gameRepository.findById(idGame).ifPresent(game -> {
            String extension = game.getRom().getExtension();
            try {
                FileUtils.copyFile(rom,
                    new File(folderRomsEscolhidas, game.getName() + FilenameUtils.EXTENSION_SEPARATOR + extension));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    private Game obterPreferencial(List<Game> gamesBloco) {
        if (gamesBloco.size() == 1) {
            return gamesBloco.getFirst();
        }
        List<Game> gamesComConquista = gamesBloco.stream().filter(Game::isAchievement).toList();
        return preferencialPorRegiao(gamesComConquista.isEmpty()?gamesBloco:gamesComConquista);
    }

    private Game preferencialPorRegiao(List<Game> gamesBloco) {
        for (String regiao : AppFileUtils.getRegionsOrder()) {
            List<Game> games = gamesBloco.stream().filter(game -> game.getRegions().contains(regiao))
                .sorted(Comparator.comparing(Game::getVersion).reversed()).toList();
            if (CollectionUtils.isNotEmpty(games)) {
                return games.getFirst();
            }
        }
        return null;
    }

}

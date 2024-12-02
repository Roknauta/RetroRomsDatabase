package com.roknauta.retroRomsDatabase.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.roknauta.retroRomsDatabase.dataSource.noIntro2.DataFile;
import com.roknauta.retroRomsDatabase.domain.Sistema;
import com.roknauta.retroRomsDatabase.domain.noIntro.Game;
import com.roknauta.retroRomsDatabase.domain.noIntro.Rom;
import com.roknauta.retroRomsDatabase.utils.AppFileUtils;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private RomRepository romRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private DatFileService datFileService;

    @PostConstruct
    public void fillDatabase() throws IOException {
        for (Sistema sistema : Sistema.values()) {
            System.out.println("Processando o sistema..." + sistema.getName().toUpperCase());
            salvarGame(sistema);
            fileService.extrairDePacks(sistema);
            fileService.filtrarArquivosPreferenciais(sistema);
        }
    }


    private void salvarGame(Sistema sistema) throws IOException {
        List<String> hashes = datFileService.getHashsFromDatFileAchviments(sistema);
        for (com.roknauta.retroRomsDatabase.dataSource.noIntro2.Game gameXml : getGamesFromDatFile(sistema)) {
            Game game = getGameFromGameXml(sistema, gameXml);
            if(game.getRom()!=null){
                game.setAchievement(hashes.contains(game.getRom().getMd5()));
                romRepository.save(game.getRom());
            }
            gameRepository.save(game);
        }
    }

    private List<com.roknauta.retroRomsDatabase.dataSource.noIntro2.Game> getGamesFromDatFile(Sistema sistema)
        throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        List<com.roknauta.retroRomsDatabase.dataSource.noIntro2.Game> games = new ArrayList<>();
        for(java.io.File xmlFile : Objects.requireNonNull(
            new java.io.File("/home/douglas/Documents/roms/dat/retro-roms/", sistema.getName()).listFiles())) {
            DataFile datafile = xmlMapper.readValue(xmlFile, DataFile.class);
            if(datafile != null&&datafile.getGame() != null) {
                games.addAll(datafile.getGame());
            }
        }
        return games;
    }

    private Game getGameFromGameXml(Sistema sistema,com.roknauta.retroRomsDatabase.dataSource.noIntro2.Game gameXml) {
        Game game = new Game();
        game.setSystem(sistema.getName());
        game.setName(gameXml.getName());
        game.setGameId(gameXml.getId());
        game.setCloneOfGameId(gameXml.getCloneOfId());
        game.setValid(gameXml.isValid());
        game.setRegions(AppFileUtils.getRegionsList(gameXml.getName()));
        game.setVersion(getVersion(gameXml.getName()));
        Optional.ofNullable(gameXml.getRom()).ifPresent(romXml-> game.setRom(new Rom(FilenameUtils.getExtension(romXml.getName()), romXml.getSize(), romXml.getCrc(), romXml.getMd5(),
            romXml.getSha1(), romXml.getSha256())));
        return game;
    }

    private Integer getVersion(String name){
        for (String flag : StringUtils.substringsBetween(name, "(", ")")) {
            if(flag.matches("Rev [0-9]")){
                return Integer.parseInt(StringUtils.substringAfter(flag, "Rev").trim());
            }
        }
        return 1;
    }



    private List<String> getAsList(String fullName) {
        return Arrays.stream(fullName.split(",")).map(String::trim).collect(Collectors.toList());
    }

}

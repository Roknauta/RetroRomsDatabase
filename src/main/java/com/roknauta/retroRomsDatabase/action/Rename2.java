package com.roknauta.retroRomsDatabase.action;

import com.roknauta.retroRomsDatabase.domain.Game;
import com.roknauta.retroRomsDatabase.domain.Sistema;
import com.roknauta.retroRomsDatabase.service.GameRepository;
import com.roknauta.retroRomsDatabase.utils.AppFileUtils;
import com.roknauta.retroRomsDatabase.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class Rename2 {

    @Autowired
    @Lazy
    private GameRepository gameRepository;

    private static final String ROMS_HOME = "/home/douglas/roms";

    public void processar(Sistema sistema) {
        List<File> roms = Arrays.asList(
            Objects.requireNonNull(AppUtils.getFolderFromPath(ROMS_HOME, sistema.getName(), "roms").listFiles()));
        Map<String, String> novosNomes = new HashMap<>();
        for (File rom : roms) {
            Game gameInfo = gameRepository.findBySources_Md5(AppUtils.getMd5(rom));
            if(gameInfo != null){
                List<Game> gamesRelacionados = gameRepository.findBySistemaAndOrNumberAndClone(sistema.getName().toUpperCase(),gameInfo.isParent()? gameInfo.getNumber():gameInfo.getClone());
                Game preferencial = obterPreferencial(gamesRelacionados);
                System.out.println("");
            }else{
                //rom.deleteOnExit();
            }
        }

    }

    private Game obterPreferencial(List<Game> gamesBloco) {
        if (gamesBloco.size() == 1) {
            return gamesBloco.getFirst();
        }
        List<Game> ordenadaPorRegiaoPreferencial = new ArrayList<>();
        for (String regiao : AppFileUtils.getRegionsOrder()) {
            for (Game game : gamesBloco) {
                if(game.getRegions().contains(regiao)){
                    ordenadaPorRegiaoPreferencial.add(game);
                    break;
                }
            }
        }
        for (String regiao : AppFileUtils.getRegionsOrder()) {


            List<Game> games = gamesBloco.stream().filter(game -> game.getRegions().contains(regiao))
                .sorted(Comparator.comparing(Game::getName).reversed()).toList();
            if (!games.isEmpty()) {
                return games.getFirst();
            }
        }
        return null;
    }

}

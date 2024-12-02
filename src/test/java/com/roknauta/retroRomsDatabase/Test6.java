package com.roknauta.retroRomsDatabase;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.roknauta.retroRomsDatabase.dataSource.noIntro.Datafile;
import com.roknauta.retroRomsDatabase.dataSource.noIntro.Game;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Test6 {

    public static void main(String[] args) throws IOException {
        File folderPai = new File("/home/douglas/Documents/roms/dat/No-Intro Love Pack (DB Export) (2024-11-27)/No-Intro");
        XmlMapper xmlMapper = new XmlMapper();
        List<Game> games = new ArrayList<>();
        Set<String> regions = new HashSet<>();
        for (File xml : folderPai.listFiles()) {
            Datafile datafile = xmlMapper.readValue(xml, Datafile.class);
            if(datafile != null&&datafile.getGame() != null) {
                games.addAll(datafile.getGame());
                for (Game game : datafile.getGame()) {
                    regions.addAll(Arrays.stream(game.getArchive().getRegion().split(",")).map(String::trim).collect(
                        Collectors.toSet()));
                }
            }
        }
        System.out.println(games.size());
    }

}

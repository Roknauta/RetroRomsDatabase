package com.roknauta.retroRomsDatabase;

import com.roknauta.retroRomsDatabase.dataSource.noIntro.Datafile;
import com.roknauta.retroRomsDatabase.dataSource.noIntro.Game;
import jakarta.xml.bind.JAXBContext;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Test3 {

    public static void main(String[] args) throws Exception {
        List<Game> games = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance(Datafile.class);
        for (File file : new File("/home/douglas/Documents/roms/dat/No-Intro Love Pack (DB Export) (2024-11-27)/No-Intro/").listFiles()) {
            Datafile datafile = (Datafile) context.createUnmarshaller()
                .unmarshal(new FileReader(file));
            games.addAll(datafile.getGame());
        }
        printSpecials(games);
    }

    private static void printSpecials(List<Game> games){
        Set<String> regions = new HashSet<>();
        Set<String> languages = new HashSet<>();
        for (Game game : games) {
            regions.addAll(Arrays.stream(game.getArchive().getRegion().split(",")).map(String::trim).collect(
                Collectors.toSet()));
            languages.addAll(Arrays.stream(game.getArchive().getLanguages().split(",")).map(String::trim).collect(
                Collectors.toSet()));
        }
        System.out.println(regions);
        System.out.println(languages);
    }

}

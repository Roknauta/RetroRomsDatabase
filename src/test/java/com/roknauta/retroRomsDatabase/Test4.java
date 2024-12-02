package com.roknauta.retroRomsDatabase;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.roknauta.retroRomsDatabase.dataSource.noIntro2.DataFile;
import com.roknauta.retroRomsDatabase.dataSource.noIntro2.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test4 {

    public static void main(String[] args) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        List<Game> games = new ArrayList<>();
            DataFile datafile = xmlMapper.readValue(new File("/home/douglas/Documents/roms/dat/No-Intro Love Pack (Standard) (2024-11-27)/No-Intro/Nintendo - Nintendo Entertainment System (Headered) (20241126-233520).dat"), DataFile.class);
            if(datafile != null&&datafile.getGame() != null) {
                games.addAll(datafile.getGame());
        }

        System.out.println(games);
    }

}

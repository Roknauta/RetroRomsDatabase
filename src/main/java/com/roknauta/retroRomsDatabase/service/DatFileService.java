package com.roknauta.retroRomsDatabase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roknauta.retroRomsDatabase.dataSource.noIntro.Datafile;
import com.roknauta.retroRomsDatabase.dataSource.noIntro.Game;
import com.roknauta.retroRomsDatabase.dataSource.retroAchviments.InfoGameRetroAchviment;
import com.roknauta.retroRomsDatabase.domain.Sistema;
import jakarta.xml.bind.JAXBContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatFileService {


    public List<Game> getGamesFromDatFile(Sistema sistema) {
        List<Game> games = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(Datafile.class);
            Datafile datafile = (Datafile) context.createUnmarshaller()
                .unmarshal(new FileReader("/home/douglas/dat/no-intro/" + sistema.getName() + ".xml"));
            games.addAll(datafile.getGame());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }

    public List<String> getHashsFromDatFileAchviments(Sistema sistema) throws IOException {
        List<String> hashes = new ArrayList<>();
        File jsonSistema = new File("/home/douglas/dat/retroachviments/" + sistema.getName() + ".json");
        if(jsonSistema.exists()){
            InfoGameRetroAchviment[] response = new ObjectMapper().readValue(               jsonSistema,
                InfoGameRetroAchviment[].class);
            for (InfoGameRetroAchviment infoGameRetroAchviment : response) {
                hashes.addAll(infoGameRetroAchviment.getHashs());
            }
        }
        return hashes;
    }

}

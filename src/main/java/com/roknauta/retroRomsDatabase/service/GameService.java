package com.roknauta.retroRomsDatabase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roknauta.retroRomsDatabase.action.Rename2;
import com.roknauta.retroRomsDatabase.dataSource.noIntro.Datafile;
import com.roknauta.retroRomsDatabase.dataSource.noIntro.File;
import com.roknauta.retroRomsDatabase.dataSource.retroAchviments.InfoGameRetroAchviment;
import com.roknauta.retroRomsDatabase.domain.Game;
import com.roknauta.retroRomsDatabase.domain.Sistema;
import com.roknauta.retroRomsDatabase.domain.Source;
import jakarta.xml.bind.JAXBContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    @Lazy
    private GameRepository gameRepository;
    @Autowired
    @Lazy
    private SourceRepository sourceRepository;
    @Autowired
    @Lazy
    private Rename2 rename2;


    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() throws IOException {
        for (Sistema sistema : Sistema.values()) {
            System.out.println("Processando o sistema..."+sistema.getName().toUpperCase());
            salvarGame(sistema);
        }
        //rename2.processar(Sistema.SNES);
    }


    private void salvarGame(Sistema sistema) throws IOException {
        List<String> hashes = getHashs(sistema);
        List<com.roknauta.retroRomsDatabase.dataSource.noIntro.Game> gamesXml = getGames(sistema);
        for (com.roknauta.retroRomsDatabase.dataSource.noIntro.Game gameXml : gamesXml) {
            Game game = new Game(gameXml.getName(), gameXml.getArchive().getName(), gameXml.getArchive().getNumber(),
                gameXml.getArchive().getClone(),gameXml.getArchive().getDevstatus(), getAsList(gameXml.getArchive().getRegion()),
                getAsList(gameXml.getArchive().getLanguages()), sistema.getName().toUpperCase());
            game = gameRepository.save(game);
            for (com.roknauta.retroRomsDatabase.dataSource.noIntro.Source sourceXml : gameXml.getSource()) {
                if (sourceXml.getFile() != null) {
                    for (File fileXml : sourceXml.getFile()) {
                        Source source =
                            new Source(fileXml.getExtension(), fileXml.getCrc32(), fileXml.getMd5(), fileXml.getSha1(),
                                hashes.contains(fileXml.getMd5()), game);
                        sourceRepository.save(source);
                    }
                }
            }
        }
    }

    private List<String> getHashs(Sistema sistema) throws IOException {
        List<String> hashes = new ArrayList<>();
        InfoGameRetroAchviment[] response = new ObjectMapper().readValue(
            new java.io.File("/home/douglas/dat/retroachviments/" + sistema.getName() + ".json"),
            InfoGameRetroAchviment[].class);
        for (InfoGameRetroAchviment infoGameRetroAchviment : response) {
            hashes.addAll(infoGameRetroAchviment.getHashs());
        }
        return hashes;
    }

    private List<String> getAsList(String fullName) {
        return Arrays.stream(fullName.split(",")).map(String::trim).collect(Collectors.toList());
    }

    private List<com.roknauta.retroRomsDatabase.dataSource.noIntro.Game> getGames(Sistema sistema) {
        List<com.roknauta.retroRomsDatabase.dataSource.noIntro.Game> games = new ArrayList<>();
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

}

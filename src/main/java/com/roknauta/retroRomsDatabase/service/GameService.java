package com.roknauta.retroRomsDatabase.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.roknauta.retroRomsDatabase.dataSource.noIntro2.DataFile;
import com.roknauta.retroRomsDatabase.domain.Sistema;
import com.roknauta.retroRomsDatabase.domain.noIntro.Game;
import com.roknauta.retroRomsDatabase.domain.noIntro.Rom;
import com.roknauta.retroRomsDatabase.utils.AppFileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


    public void atualizarBaseInterna(Sistema sistema) throws IOException {
        List<String> md5OfGamesWithRetroAchievements = datFileService.getHashsFromDatFileAchviments(sistema);
        for (com.roknauta.retroRomsDatabase.dataSource.noIntro2.Game gameXml : getGamesFromDatFile(sistema)) {
            Game game = new Game(sistema.getName(),gameXml.getName(),gameXml.getId(),gameXml.getCloneOfId(),getVersion(gameXml.getName()),AppFileUtils.getRegionsList(gameXml.getName()),
                gameXml.isValid());
            Optional.ofNullable(gameXml.getRom()).ifPresent(romXml-> {
                Rom rom = new Rom(FilenameUtils.getExtension(romXml.getName()), romXml.getSize(), romXml.getCrc(), romXml.getMd5(),
                    romXml.getSha1(), romXml.getSha256());
                game.setAchievement(md5OfGamesWithRetroAchievements.contains(rom.getMd5()));
                game.setRom(rom);
                romRepository.save(game.getRom());
            });
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

    private Integer getVersion(String name){
        for (String flag : StringUtils.substringsBetween(name, "(", ")")) {
            if(flag.matches("Rev [0-9]")){
                return Integer.parseInt(StringUtils.substringAfter(flag, "Rev").trim());
            }
        }
        return 1;
    }

}

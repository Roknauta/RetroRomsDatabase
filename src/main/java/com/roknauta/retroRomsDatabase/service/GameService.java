package com.roknauta.retroRomsDatabase.service;

import com.roknauta.retroRomsDatabase.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    @Lazy
    private GameRepository gameRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void salvarGame(){
        Game game = new Game();
        game.setName("XD");
        gameRepository.save(game);
    }

}

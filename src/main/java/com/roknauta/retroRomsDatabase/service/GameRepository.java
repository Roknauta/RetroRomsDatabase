package com.roknauta.retroRomsDatabase.service;

import com.roknauta.retroRomsDatabase.domain.noIntro.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

   List<Game> findByRom_Md5(String romMd5);

    @Query(
        "select game from Game game where game.system = :sistema and game.valid = true and (game.gameId = :number or game.cloneOfGameId = :number)")
    List<Game> findBySistemaAndOrNumberAndClone(@Param("sistema") String sistema, @Param("number") String number);
}

package com.roknauta.retroRomsDatabase.service;

import com.roknauta.retroRomsDatabase.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findBySistema(String sistema);

    Game findBySources_Md5(String hashMd5);

    List<Game> findBySistemaIgnoreCaseAndClone(String sistema, String cloneId);

    @Query(
        "select game from Game game where game.sistema = :sistema and (game.number = :number or game.clone = :number)")
    List<Game> findBySistemaAndOrNumberAndClone(@Param("sistema") String sistema, @Param("number") String number);
}

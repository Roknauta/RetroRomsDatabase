package com.roknauta.retroRomsDatabase.service;

import com.roknauta.retroRomsDatabase.domain.noIntro.Rom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RomRepository extends JpaRepository<Rom, Long> {

   boolean existsRomByMd5Is(String md5);

   @Query("select distinct rom.md5 from Game game join game.rom rom where game.system = :sistem")
   Set<String> findMd5(@Param("sistem") String sistem);

   Rom findByCrc32(String crc32);

   Rom findByMd5IgnoreCase(String md5);

}

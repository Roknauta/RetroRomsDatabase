package com.roknauta.retroRomsDatabase.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sistema {

    NES("nes"),
    SNES("snes"),
    GB("gb"),
    GBC("gbc"),
    GBA("gba"),
    N64("n64"),
    MASTER_SYSTEM("mastersystem"),
    MEGA_DRIVE("megadrive"),
    SEGA32X("sega32x"),
    GAME_GEAR("gamegear"),
    ATARI_2600("atari2600"),
    //ATARI_5200("atari5200"),
    //ATARI_7800("atari7800"),
    ATARI_LYNX("lynx"),
    ATARI_JAGUAR("jaguar"),
    COLECO_VISION("colecovision"),
    //COMODORE_64("c64"),
    CHANNEL_F("channelf"),
    ODYSSEY2("o2em"),
    INTELLIVISION("intellivision"),
    PC_ENGINE("pcengine");

    private final String name;


}

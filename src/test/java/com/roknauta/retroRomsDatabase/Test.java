package com.roknauta.retroRomsDatabase;

import com.roknauta.retroRomsDatabase.domain.Sistema;
import org.apache.commons.compress.compressors.xz.XZUtils;
import org.apache.commons.io.FileUtils;
import org.tukaani.xz.LZMA2InputStream;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Test {

    private static final String ORIGINAL_ROMS_FOLDER = "/home/douglas/roms-escolhidas";
    private static final String COMPRESSED_ROMS_FOLDER = "/home/douglas/roms-compactadas/";
    private static final String PATH = "/home/douglas/Documents/roms/dat/retro-roms/";

    public static void main(String[] args) throws IOException {
        for (Sistema sistema : Sistema.values()) {
            FileUtils.forceMkdir(new File(PATH,sistema.getName()));
        }

    }

}

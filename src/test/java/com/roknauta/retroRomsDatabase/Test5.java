package com.roknauta.retroRomsDatabase;

import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class Test5 {

    public static void main(String[] args) throws IOException {
        LocalDateTime startTime = LocalDateTime.now();
        //Compactador.compactarParaZip("/home/douglas/Downloads/test.zip","/home/douglas/Downloads/alz-prod-wallen051124.zip");
        //SevenZ.compress("/home/douglas/Downloads/test.zip",new File("/home/douglas/Downloads/alz-prod-wallen051124.zip"));
        //compactarArquivosPara7z("/home/douglas/Downloads/test.7z","/home/douglas/Downloads/alz-prod-wallen051124.zip");
        for (File file : FileUtils.listFiles(
            new File("/home/douglas/Documents/roms/test/Nintendo - Super Nintendo Entertainment System"), null, true)) {
            //compactarArquivosPara7z("/home/douglas/Documents/roms/new/"+ FilenameUtils.getBaseName(file.getName())+".7z",file.getAbsolutePath());
            Compactador.compactarParaZip("/home/douglas/Documents/roms/new/"+ FilenameUtils.getBaseName(file.getName())+".zip",file.getAbsolutePath());
        }
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println(Duration.between(startTime, endTime).toString());
    }

    public static void compactarArquivosPara7z(String arquivo7z, String... arquivosParaCompactar) throws IOException {
        File arquivoCompactado = new File(arquivo7z);

        try (SevenZOutputFile sevenZOutputFile = new SevenZOutputFile(arquivoCompactado)) {
            // Adiciona arquivos no formato 7z
            for (String caminhoArquivo : arquivosParaCompactar) {
                File arquivo = new File(caminhoArquivo);
                if (arquivo.exists() && arquivo.isFile()) {
                    // Adiciona cada arquivo ao arquivo 7z
                    sevenZOutputFile.putArchiveEntry(sevenZOutputFile.createArchiveEntry(arquivo, arquivo.getName()));
                    try (FileInputStream fis = new FileInputStream(arquivo)) {
                        sevenZOutputFile.write(fis);
                    }
                    sevenZOutputFile.closeArchiveEntry();
                } else {
                    System.out.println("Arquivo não encontrado: " + caminhoArquivo);
                }
            }
        }
    }

}

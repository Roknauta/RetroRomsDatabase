package com.roknauta.retroRomsDatabase;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compactador {

    //Constantes
    static final int TAMANHO_BUFFER = 4096; // 4kb

    //método para compactar arquivo
    public static void compactarParaZip(String arqSaida,String arqEntrada)
        throws IOException {
        int cont;
        byte[] dados = new byte[TAMANHO_BUFFER];

        BufferedInputStream origem;
        FileInputStream streamDeEntrada;
        FileOutputStream destino;
        ZipOutputStream saida;
        ZipEntry entry;

        try {
            destino = new FileOutputStream(new File(arqSaida));
            saida = new ZipOutputStream(new BufferedOutputStream(destino));
            File file = new File(arqEntrada);
            streamDeEntrada = new FileInputStream(file);
            origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
            entry = new ZipEntry(file.getName());
            saida.putNextEntry(entry);

            while((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
                saida.write(dados, 0, cont);
            }
            origem.close();
            saida.close();
        } catch(IOException e) {
            throw new IOException(e.getMessage());
        }
    }

}

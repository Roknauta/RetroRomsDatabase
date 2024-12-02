package com.roknauta.retroRomsDatabase;

public class RetroRomsException extends RuntimeException {

    public RetroRomsException(String fileName,Throwable tr) {
       super("Erro no arquivo: "+fileName,tr);
    }
}

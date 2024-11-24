
package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import jakarta.xml.bind.annotation.XmlRegistry;



@XmlRegistry
public class ObjectFactory {


    
    public ObjectFactory() {
    }

    
    public Datafile createDatafile() {
        return new Datafile();
    }

    
    public Game createDatafileGame() {
        return new Game();
    }

    
    public Release createDatafileGameRelease() {
        return new Release();
    }

    
    public Source createDatafileGameSource() {
        return new Source();
    }

    
    public Archive createDatafileGameArchive() {
        return new Archive();
    }

    
    public Details createDatafileGameReleaseDetails() {
        return new Details();
    }

    
    public File createDatafileGameReleaseFile() {
        return new File();
    }

    
    public Details createDatafileGameSourceDetails() {
        return new Details();
    }

    
    public Serials createDatafileGameSourceSerials() {
        return new Serials();
    }

    
    public File createDatafileGameSourceFile() {
        return new File();
    }

}

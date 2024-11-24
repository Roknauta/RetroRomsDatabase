package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"details", "serials", "file"})
public class Source {

    @XmlElement(required = true)
    protected Details details;
    @XmlElement(required = true)
    protected Serials serials;
    @XmlElement(required = true)
    protected List<File> file;


    public Details getDetails() {
        return details;
    }


    public void setDetails(Details value) {
        this.details = value;
    }


    public Serials getSerials() {
        return serials;
    }


    public void setSerials(Serials value) {
        this.serials = value;
    }


    public List<File> getFile() {
        return file;
    }


    public void setFile(List<File> value) {
        this.file = value;
    }



}

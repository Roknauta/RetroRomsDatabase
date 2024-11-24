package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"details", "serials", "file"})
public class Release {

    @XmlElement(required = true)
    protected Details details;
    @XmlElement(required = true)
    protected Object serials;
    protected File file;


    public Details getDetails() {
        return details;
    }


    public void setDetails(Details value) {
        this.details = value;
    }


    public Object getSerials() {
        return serials;
    }


    public void setSerials(Object value) {
        this.serials = value;
    }


    public File getFile() {
        return file;
    }


    public void setFile(File value) {
        this.file = value;
    }



}

package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Archive {

    @XmlAttribute(name = "number", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected String number;
    @XmlAttribute(name = "clone", required = true)
    protected String clone;
    @XmlAttribute(name = "regparent")
    protected String regparent;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "region", required = true)
    protected String region;
    @XmlAttribute(name = "languages", required = true)
    protected String languages;
    @XmlAttribute(name = "sticky_note")
    protected String stickyNote;
    @XmlAttribute(name = "devstatus")
    protected String devstatus;
    @XmlAttribute(name = "complete")
    @XmlSchemaType(name = "unsignedByte")
    protected Short complete;
    @Getter
    @Setter
    @XmlAttribute(name = "version1")
    protected String version1;
    @Getter
    @Setter
    @XmlAttribute(name = "bios")
    protected String bios;
    @Getter
    @Setter
    @XmlAttribute(name = "licensed")
    protected String licensed;


    public String getNumber() {
        return number;
    }


    public void setNumber(String value) {
        this.number = value;
    }


    public String getClone() {
        return clone;
    }


    public void setClone(String value) {
        this.clone = value;
    }


    public String getRegparent() {
        return regparent;
    }


    public void setRegparent(String value) {
        this.regparent = value;
    }


    public String getName() {
        return name;
    }


    public void setName(String value) {
        this.name = value;
    }


    public String getRegion() {
        return region;
    }


    public void setRegion(String value) {
        this.region = value;
    }


    public String getLanguages() {
        return languages;
    }


    public void setLanguages(String value) {
        this.languages = value;
    }


    public String getStickyNote() {
        return stickyNote;
    }


    public void setStickyNote(String value) {
        this.stickyNote = value;
    }


    public String getDevstatus() {
        return devstatus;
    }


    public void setDevstatus(String value) {
        this.devstatus = value;
    }


    public Short getComplete() {
        return complete;
    }


    public void setComplete(Short value) {
        this.complete = value;
    }

}

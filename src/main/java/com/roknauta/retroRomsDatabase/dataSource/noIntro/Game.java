package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"archive", "source", "release"})
public class Game {

    @XmlElement(required = true)
    protected Archive archive;
    protected List<Source> source;
    protected List<Release> release;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive value) {
        this.archive = value;
    }

    public List<Source> getSource() {
        if (source == null) {
            source = new ArrayList<>();
        }
        return this.source;
    }

    public List<Release> getRelease() {
        if (release == null) {
            release = new ArrayList<>();
        }
        return this.release;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }



}

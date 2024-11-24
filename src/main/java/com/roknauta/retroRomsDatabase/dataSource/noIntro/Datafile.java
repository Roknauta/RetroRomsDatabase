package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"game"})
@XmlRootElement(name = "datafile")
public class Datafile {

    @XmlElement(required = true)
    protected List<Game> game;

    public List<Game> getGame() {
        if (game == null) {
            game = new ArrayList<>();
        }
        return this.game;
    }


}

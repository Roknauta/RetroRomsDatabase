package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JacksonXmlRootElement(localName = "datafile")
@Getter
@Setter
public class Datafile {

    @JacksonXmlElementWrapper(useWrapping = false)
    protected List<Game> game;
}

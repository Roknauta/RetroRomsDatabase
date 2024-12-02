package com.roknauta.retroRomsDatabase.dataSource.noIntro2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JacksonXmlRootElement(localName = "datafile")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataFile {

    @JacksonXmlElementWrapper(useWrapping = false)
    protected List<Game> game;
}

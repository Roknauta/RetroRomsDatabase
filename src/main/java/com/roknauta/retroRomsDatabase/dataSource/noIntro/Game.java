package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Game {

    private String name;
    private Archive archive;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("source")
    private List<Source> sources;
    private Release release;
}

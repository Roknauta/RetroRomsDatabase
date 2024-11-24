package com.roknauta.retroRomsDatabase.dataSource.retroAchviments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoGameRetroAchviment {

    @JsonProperty("Hashes")
    private List<String> hashs;

}

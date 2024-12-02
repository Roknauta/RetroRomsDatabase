package com.roknauta.retroRomsDatabase.dataSource.noIntro2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Rom {

    private String name;
    private String size;
    private String crc;
    private String md5;
    private String sha1;
    private String sha256;

}

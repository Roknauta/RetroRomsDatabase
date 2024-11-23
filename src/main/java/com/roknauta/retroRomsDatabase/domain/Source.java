package com.roknauta.retroRomsDatabase.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String extension;
    private String crc32;
    private String md5;
    private String sha1;
    private Boolean retroachievements;
    @ManyToOne
    private Game game;

}

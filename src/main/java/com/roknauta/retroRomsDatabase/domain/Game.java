package com.roknauta.retroRomsDatabase.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String baseName;
    private String number;
    private String clone;
    private String devStatus;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "region")
    private List<String> regions;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "language")
    private List<String> languages;
    private String sistema;
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private List<Source> sources;

    public Game(String name, String baseName, String number, String clone,String devStatus, List<String> regions, List<String> languages,
        String sistema) {
        this.name = name;
        this.baseName = baseName;
        this.number = number;
        this.clone = clone;
        this.devStatus = devStatus;
        this.regions = regions;
        this.languages = languages;
        this.sistema = sistema;
        this.sources = new ArrayList<>();
    }

    public boolean isClone(){
        return !"P".equals(this.clone);
    }

    public boolean isParent(){
        return "P".equals(this.clone);
    }
}

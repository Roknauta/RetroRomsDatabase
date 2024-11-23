package com.roknauta.retroRomsDatabase.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String baseName;
    private String number;
    private String clone;
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "region")
    private List<String> regions;
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "language")
    private List<String> languages;
    private String sistema;
    @OneToMany(mappedBy = "game")
    private List<Source> sources;

    public boolean isClone(){
        return "P".equals(this.clone);
    }
}

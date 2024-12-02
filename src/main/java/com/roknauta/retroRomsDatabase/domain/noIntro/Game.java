package com.roknauta.retroRomsDatabase.domain.noIntro;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {@Index(name = "idx_game_system", columnList = "system"),
    @Index(name = "idx_game_gameId", columnList = "gameId"),
    @Index(name = "idx_game_cloneOfGameId", columnList = "cloneOfGameId"),
    @Index(name = "idx_game_valid", columnList = "valid")
})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String system;
    private String name;
    private String gameId;
    private String cloneOfGameId;
    private Integer version;
    @Column(name = "region")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(foreignKey = @ForeignKey(name = "FK_game_region_game"))
    private List<String> regions;
    private boolean valid;
    private boolean achievement;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_game_rom"))
    private Rom rom;

    public Game(String system, String name, String gameId, String cloneOfGameId, Integer version, List<String> regions,
        boolean valid) {
        this.system = system;
        this.name = name;
        this.gameId = gameId;
        this.cloneOfGameId = cloneOfGameId;
        this.version = version;
        this.regions = regions;
        this.valid = valid;
    }
}

package com.roknauta.retroRomsDatabase.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String extension;
    private String crc32;
    private String md5;
    private String sha1;
    private boolean retroachievements;
    @ManyToOne
    private Game game;

    public Source(String extension, String crc32, String md5, String sha1, Boolean retroachievements, Game game) {
        this.extension = extension;
        this.crc32 = crc32;
        this.md5 = md5;
        this.sha1 = sha1;
        this.retroachievements = retroachievements;
        this.game = game;
    }
}

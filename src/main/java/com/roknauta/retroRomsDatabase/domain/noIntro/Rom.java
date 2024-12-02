package com.roknauta.retroRomsDatabase.domain.noIntro;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {@Index(name = "idx_rom_md5", columnList = "md5"),@Index(name = "idx_rom_crc32", columnList = "crc32")})
public class Rom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String extension;
    private String size;
    private String crc32;
    private String md5;
    private String sha1;
    private String sha256;

    public Rom(String extension, String size, String crc32, String md5, String sha1, String sha256) {
        this.extension = extension;
        this.size = size;
        this.crc32 = crc32;
        this.md5 = md5;
        this.sha1 = sha1;
        this.sha256 = sha256;
    }
}

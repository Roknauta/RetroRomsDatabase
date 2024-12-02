package com.roknauta.retroRomsDatabase.dataSource.noIntro2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Game {

    private String name;
    private String id;
    @JsonProperty("cloneofid")
    private String cloneOfId;
    private Rom rom;

    public boolean isValid() {
        if (StringUtils.contains(this.name, "[BIOS]")) {
            return false;
        }
        for (String flags : StringUtils.substringsBetween(this.name, "(", ")")) {
            for (String flag : flags.split(",")) {
                if (StringUtils.containsIgnoreCase(flag.trim(), "Beta") || StringUtils.containsIgnoreCase(flag.trim(),
                    "Proto") || StringUtils.containsIgnoreCase(flag.trim(), "Pirate") || StringUtils.containsIgnoreCase(
                    flag.trim(), "Competition") || flag.trim().equals("Unl") || flag.trim()
                    .contains("Demo") || flag.trim().equals("Unknown")) {
                    return false;
                }
            }
        }
        return true;
    }

}

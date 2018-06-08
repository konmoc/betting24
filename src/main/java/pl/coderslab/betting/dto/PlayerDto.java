package pl.coderslab.betting.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PlayerDto {

    @JsonProperty("player_id")
    private Long id;
    @JsonProperty("player_nickname")
    private String nickname;
    @JsonProperty("player_firstname")
    private String firstName;
    @JsonProperty("player_lastname")
    private String lastName;
    @JsonProperty("player_winratio")
    private double winRatio;

}

package pl.coderslab.betting.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.coderslab.betting.entity.Genre;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class VideoGameDto {

    @JsonProperty("videogame_id")
    private Long id;
    @JsonProperty("videogame_name")
    private String name;
    @JsonProperty("videogame_roundduration")
    private int roundDuration;
    @JsonProperty("videogame_numberofrounds")
    private Long numberOfRounds;
    @JsonProperty("videogame_maxamountofplayersinteam")
    private Long maxAmountOfPlayersInTeam;
}

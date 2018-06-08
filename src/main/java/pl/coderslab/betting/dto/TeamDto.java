package pl.coderslab.betting.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.entity.GameResult;
import pl.coderslab.betting.entity.Player;
import pl.coderslab.betting.entity.VideoGame;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TeamDto {
    @JsonProperty("team_id")
    private Long id;
    @JsonProperty("team_name")
    private String name;
    @JsonProperty("team_winratio")
    private double winRatio;
}

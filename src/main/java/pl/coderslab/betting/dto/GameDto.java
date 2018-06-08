package pl.coderslab.betting.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GameDto {
    @JsonProperty("game_id")
    private long id;
    @JsonProperty("game_start_date")
    private LocalDateTime dateAndTime;
    @JsonProperty("game_status")
    private String status;
    @JsonProperty("game_odd1")
    private double odd1;
    @JsonProperty("game_odd2")
    private double odd2;
}

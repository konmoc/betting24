package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "video_games")
@Data
public class VideoGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Genre genre;
    private int roundDuration;
    private Long numberOfRounds;
    private Long maxAmountOfPlayersInTeam;
}

package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * This entity describes video games. Each video game is described by:
 * -name(String name)
 * -one genre(Genre genre) like FPS,Strategy,RTS, etc..
 * -round duration in minutes(int roundDuration)
 * -number of rounds(Long numberOfRounds)
 * -maximal amount of players in ONE team(Long maxAmountOfPlayersInTeam)
 */

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

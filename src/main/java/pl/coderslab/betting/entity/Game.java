package pl.coderslab.betting.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**This entity represents game. Each game has its date and time(LocalDateTime dateAndTime),
 * list of players(List<Player> playersInGame) OR teams(List<Team> teamsInGame),
 * list of ALL bets placed on this game(List<Bet> gameBets),
 * result of game(private GameResult gameResult) created after the game is finished,
 * type of video game which is played in this game(VideoGame videoGame),
 * status("PLANNED", "STARTED" or "FINISHED"),
 * win ratios of both sides calculated in the methods below(TRANSIENT!!!).
 */

@Entity
@Table(name = "games")
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private LocalDateTime dateAndTime;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Player> playersInGame;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Team> teamsInGame;

    @OneToMany
    private List<Bet> gameBets;

    @OneToOne
    private GameResult gameResult;

    @OneToOne
    private VideoGame videoGame;

    private String status;

    private double odd1;

    private double odd2;



}

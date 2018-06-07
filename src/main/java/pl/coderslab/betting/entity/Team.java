package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * This entity represents team. Each team is described by its name(String name),
 * video game(VideoGame videoGame -JUST ONE!), (won games/all games) ratio (double winRatio),
 * list of games they play/ed(List<Game> gameList), games won(List<GameResult> gamesWon).
 * Each team can have many players(List<Player> playerList) defined by (VideoGame videoGame) they play.
 *
 */

@Entity
@Table(name = "teams")
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private VideoGame videoGame;
    private double winRatio;


    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Player> playerList;

    @ManyToMany(mappedBy = "teamsInGame")

    private List<Game> gameList;
    @OneToMany(mappedBy = "teamWhichWon")
    private List<GameResult> gamesWon;


}

package pl.coderslab.betting.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Player> playerList;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "teamsInGame")
    private List<Game> gameList;

    @OneToMany(mappedBy = "teamWhichWon")
    private List<GameResult> gamesWon;


}

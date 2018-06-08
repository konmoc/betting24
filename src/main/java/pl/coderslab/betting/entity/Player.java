package pl.coderslab.betting.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * This entity describes players who play in our games. Each player is described by:
     * -his/her nickname(String nickname)
     * -first name(String firstName)
     * -last name(String lastName)
     * -(won games/all games) ratio (double winRatio)
     * -games he played(List<Game> playerGames)
     * -games he won(List<Game> gamesWon).
 * Each player can be member of many teams(private List<Team> playerTeams).
 */

@Entity
@Table(name = "players")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nickname;
    private String firstName;
    private String lastName;
    private double winRatio;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "playerList")
    private List<Team> playerTeams;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "playersInGame")
    private List<Game> playerGames;

    @OneToMany(mappedBy = "playerWhoWon")
    List<GameResult> gamesWon;



}

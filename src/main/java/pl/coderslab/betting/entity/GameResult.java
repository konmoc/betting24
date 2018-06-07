package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * This entity represents game result. Each game result has:
 * -status(String status) saying which player won
 * -game to which it is assigned(Game game)
 * -player(Player playerWhoWon) or team(Team teamWhichWon) who/which won the game.
 */

@Entity
@Table(name = "game_results")
@Data
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;

    @OneToOne(mappedBy = "gameResult")
    private Game game;

    @ManyToOne
    private Player playerWhoWon;

    @ManyToOne
    private Team teamWhichWon;
}

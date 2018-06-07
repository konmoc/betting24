package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;

/** This entity represents bet. Each bet has:
 *  -owner(User user)
 *  -value(double money).
 * Each bet specifies the team(Team teamWhichWeBetFor) OR player(Player playerWhoWeBetFor)
 *  on who or which our User placed bet.
 *  Bets can be placed on numerous players or teams(@ManyToMany)
 *  One User can place many bets(@ManyToOne)
 * */

@Entity
@Table(name="bets")
@Data
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Game game;
    private double money;
    @ManyToOne
    private User user;
    @ManyToOne
    private Player playerWhoWeBetFor;
    @ManyToOne
    private Team teamWhichWeBetFor;

}

package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @ManyToMany(mappedBy = "playerList")
    private List<Team> playerTeams;
}

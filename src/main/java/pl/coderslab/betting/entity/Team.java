package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

}

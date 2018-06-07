package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * This entity represents game genre. It is described by:
 * -name(String name) like "FPS-First Person Shooter" etc..
 */

@Entity
@Table(name = "genres")
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
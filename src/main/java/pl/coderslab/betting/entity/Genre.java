package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
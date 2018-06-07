package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * This entity represents security role. It is described just by name(String name)
 * There are only two roles: "ROLE_USER" and "ROLE_ADMIN".
 */

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "role")
    private String name;
}

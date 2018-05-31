package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class  User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Size(min = 4, max = 20, message = "Username should be at least 4 characters long. Maximal length is 20.")
    private String username;
    @NotBlank(message = "This field cannot be left empty!")
    private String password;
    @NotBlank(message = "This field cannot be left empty!")
    private String firstName;
    @NotBlank(message = "This field cannot be left empty!")
    private String lastName;
    private Double money;
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

//    @OneToMany
//    private List<Bet> bets;

    @OneToMany
    private List<Message> messagesSent;

    @ManyToMany
    private List<Message> messagesReceived;


}

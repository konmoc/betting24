package pl.coderslab.betting.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

/**
 * This entity describes user who uses our application.
 * He/She is described by:
 *  -username(String username)
 *  -first name(String firstName)
 *  -last name(String lastName)
 *  -encoded password(String password)
 *  -money on his account(Double money)
 *  -enabled status(int enabled).
 * EACH USER:
 *  -can have many bets(List<Bet> bets)
 *  -can have many sent messages(List<Message> messagesSent)
 *  -can have many received messages(List<Message> messagesReceived
 *  -can have many roles(Set<Role> roles)
 */

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

    @OneToMany(mappedBy = "user")
    private List<Bet> bets;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messagesSent;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Message> messagesReceived;


}

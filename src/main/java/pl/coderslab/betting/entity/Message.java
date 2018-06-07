package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * This entity represents messages which are sent to or by user. Each message is described by:
 * -its title(String title)
 * -body of message(String content)
 */

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Message title can't be empty!")
    private String title;
    @NotEmpty(message = "Message content can't be empty!")
    private String content;

}

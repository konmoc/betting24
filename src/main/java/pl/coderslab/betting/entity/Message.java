package pl.coderslab.betting.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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

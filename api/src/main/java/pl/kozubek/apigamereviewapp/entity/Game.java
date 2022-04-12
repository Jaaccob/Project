package pl.kozubek.apigamereviewapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "GAME")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String imageURL;

    private String description;

    private String author;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idGame", updatable = false, insertable = false)
    private List<ConnectGameType> cGame;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Game", updatable = false, insertable = false)
    private List<Review> reviews;

}

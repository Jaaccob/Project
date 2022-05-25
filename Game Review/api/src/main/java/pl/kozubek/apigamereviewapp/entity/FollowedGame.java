package pl.kozubek.apigamereviewapp.entity;

import lombok.*;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "FOLLOWED_GAMES")
public class FollowedGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_User")
    private Long idUser;

    @Column(name = "followed_Id_Game")
    private Long idFollowGame;

}

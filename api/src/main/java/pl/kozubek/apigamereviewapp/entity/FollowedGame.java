package pl.kozubek.apigamereviewapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;

@Embeddable
@RestController
@Getter
@Setter
@Table(name = "FOLLOWED_GAMES")
public class FollowedGame {

    @Column(name = "id_User")
    private Long idUser;

    @Column(name = "followed_Id_Game")
    private Long idFollowGame;

}

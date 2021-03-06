package pl.kozubek.apigamereviewapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;


@Entity
@RestController
@Getter
@Setter
@Table(name = "FOLLOWED_USERS")
public class FollowedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_User")
    private Long idUser;

    @Column(name = "id_Followed_User")
    private Long idUserFollow;



}

package pl.kozubek.apigamereviewapp.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "REVIEW")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Review")
    private Long id;

    @Column(name = "id_User", nullable = false)
    private Long idUser;

    @Column(name = "id_Game", nullable = false)
    private Long idGame;

    @Column(nullable = false)
    private double mark;

    @Column(nullable = false)
    private Date date;

    @Column(length = 500, nullable = false)
    private String description;

}

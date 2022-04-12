package pl.kozubek.apigamereviewapp.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "connect_game_type")
@Setter
@Getter
@RequiredArgsConstructor
public class ConnectGameType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idGame;

    private Long idType;

}
package pl.kozubek.apigamereviewapp.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowedGameDto {
    private Long idGame;
    private Long idUser;
}

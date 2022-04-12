package pl.kozubek.apigamereviewapp.controller.mapper;

import pl.kozubek.apigamereviewapp.controller.dto.GameDto;
import pl.kozubek.apigamereviewapp.entity.Game;

public class GameMapper {
    public static Game mapToGame(GameDto game){
        return Game.builder()
                .id(game.getId())
                .title(game.getTitle())
                .description("")
                .author("")
                .build();
    }
}

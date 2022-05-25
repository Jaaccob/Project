package pl.kozubek.apigamereviewapp.service.mapper;

import pl.kozubek.apigamereviewapp.entity.FollowedGame;
import pl.kozubek.apigamereviewapp.service.dto.GameWithTypeDTO;

import java.util.List;

public class FollowedGameMapper {

    private FollowedGameMapper() {
    }

    public static List<GameWithTypeDTO> mapToGameDtos(List<FollowedGame> followedGames, List<GameWithTypeDTO> games) {
        return followedGames.stream()
                .flatMap(followedGame -> games.stream()
                        .filter(game -> followedGame.getIdFollowGame().equals(game.getId())))
                .toList();
    }
}

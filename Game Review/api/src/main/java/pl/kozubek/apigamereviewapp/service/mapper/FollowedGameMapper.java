package pl.kozubek.apigamereviewapp.service.mapper;

import pl.kozubek.apigamereviewapp.entity.FollowedGame;
import pl.kozubek.apigamereviewapp.entity.Game;
import pl.kozubek.apigamereviewapp.entity.Review;
import pl.kozubek.apigamereviewapp.entity.User;
import pl.kozubek.apigamereviewapp.service.dto.FollowedGameDto;
import pl.kozubek.apigamereviewapp.service.dto.GameWithTypeDTO;
import pl.kozubek.apigamereviewapp.service.dto.ReviewDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FollowedGameMapper {

    private FollowedGameMapper() {
    }

    public static List<FollowedGameDto> mapToGameDtos(List<FollowedGame> followedGames, List<Game> games) {
        return followedGames.stream()
                .flatMap(followedGame -> games.stream()
                        .filter(game -> followedGame.getIdFollowGame().equals(game.getId()))
                        .map(FollowedGameMapper::mapFollowedGameToDto))
                .toList();
    }

    private static FollowedGameDto mapFollowedGameToDto(Game game) {
        return FollowedGameDto.builder()
                .id(game.getId())
                .imageURL(game.getImageURL())
                .title(game.getTitle())
                .description(game.getDescription())
                .author(game.getAuthor())
                .build();
    }
}

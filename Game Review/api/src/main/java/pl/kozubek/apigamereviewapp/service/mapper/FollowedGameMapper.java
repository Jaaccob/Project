package pl.kozubek.apigamereviewapp.service.mapper;

import lombok.Data;
import pl.kozubek.apigamereviewapp.entity.FollowedGame;
import pl.kozubek.apigamereviewapp.entity.Game;
import pl.kozubek.apigamereviewapp.service.dto.FollowedGameDto;
import pl.kozubek.apigamereviewapp.service.dto.GameWithoutTypeDTO;
import pl.kozubek.apigamereviewapp.service.dto.ReviewMarkDto;

import java.util.List;

@Data
public class FollowedGameMapper {

    private static GameWithoutTypMapper gameWithoutTypMapper;

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

    public static List<GameWithoutTypeDTO> mapToGameWithotTypeDtos(List<FollowedGame> allFollowedGame, List<Game> games, List<ReviewMarkDto> marks) {
        List<GameWithoutTypeDTO> gameWithoutTypeDTO = GameWithoutTypMapper.mapToGameDtos(games,marks);
        return allFollowedGame.stream()
                .flatMap(followedGame -> gameWithoutTypeDTO.stream()
                        .filter(game -> followedGame.getIdFollowGame().equals(game.getId())))
                .toList();
    }
}

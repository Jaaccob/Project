package pl.kozubek.apigamereviewapp.service.mapper;

import pl.kozubek.apigamereviewapp.entity.ConnectGameType;
import pl.kozubek.apigamereviewapp.entity.Game;
import pl.kozubek.apigamereviewapp.entity.Type;
import pl.kozubek.apigamereviewapp.service.dto.GameWithTypeDTO;

import java.util.List;

public class GameWithTypeDTOMapper {
    private GameWithTypeDTOMapper() {

    }

    public static List<GameWithTypeDTO> mapToGameDtos(List<ConnectGameType> cgt, List<Game> games, List<Type> types) {
        return cgt.stream()
                .flatMap(connectGameType -> types.stream()
                        .flatMap(type -> games.stream()
                                .filter(game -> connectGameType.getIdGame().equals(game.getId())
                                        && connectGameType.getIdType().equals(type.getId()))
                                .map(game -> mapToGameDto(game, type))
                        )
                )
                .toList();
    }

    private static GameWithTypeDTO mapToGameDto(Game game, Type type) {
        return GameWithTypeDTO.builder()
                .id(game.getId())
                .title(game.getTitle())
                .description(game.getDescription())
                .author(game.getAuthor())
                .types(type.getName())
                .build();
    }
}

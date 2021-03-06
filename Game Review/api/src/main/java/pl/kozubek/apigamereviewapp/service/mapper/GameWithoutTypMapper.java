package pl.kozubek.apigamereviewapp.service.mapper;

import pl.kozubek.apigamereviewapp.entity.Game;
import pl.kozubek.apigamereviewapp.service.dto.GameWithoutTypeDTO;
import pl.kozubek.apigamereviewapp.service.dto.ReviewMarkDto;

import java.util.List;
import java.util.stream.Stream;

public class GameWithoutTypMapper {
    private GameWithoutTypMapper() {
    }

    public static List<GameWithoutTypeDTO> mapToGameDtos(List<Game> games, List<ReviewMarkDto> marks) {
        return games.stream()
                .flatMap(game -> marks.stream()
                        .filter(mark -> mark.getIdGame().equals(game.getId()))
                        .map(mark -> mapToGameDto(game, mark)))
                .toList();

    }

    public static GameWithoutTypeDTO mapToSingleGameDto(Game game, List<ReviewMarkDto> marks) {
        double mark = marks.stream()
                .filter(m -> m.getIdGame().equals(game.getId()))
                .mapToDouble(ReviewMarkDto::getMark)
                .average()
                .orElse(Double.MIN_VALUE);
        return mapToSingleGameDto(game,mark);
    }

    private static GameWithoutTypeDTO mapToGameDto(Game game, ReviewMarkDto mark) {
        return GameWithoutTypeDTO.builder()
                .id(game.getId())
                .imageURL(game.getImageURL())
                .title(game.getTitle())
                .description(game.getDescription())
                .author(game.getAuthor())
                .mark(mark.getMark())
                .build();
    }

    private static GameWithoutTypeDTO mapToSingleGameDto(Game game, double mark) {
        return GameWithoutTypeDTO.builder()
                .id(game.getId())
                .imageURL(game.getImageURL())
                .title(game.getTitle())
                .description(game.getDescription())
                .author(game.getAuthor())
                .mark(mark)
                .build();
    }
}

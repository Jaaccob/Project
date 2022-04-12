package pl.kozubek.apigamereviewapp.service.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class GameWithTypeDTO {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String types;
}

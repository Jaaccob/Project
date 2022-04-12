package pl.kozubek.apigamereviewapp.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameWithoutTypeDTO {
    private Long id;
    private String imageURL;
    private String title;
    private String description;
    private String author;
    private Double mark;
}

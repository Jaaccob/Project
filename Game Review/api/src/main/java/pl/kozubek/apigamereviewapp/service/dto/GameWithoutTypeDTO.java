package pl.kozubek.apigamereviewapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
@Builder
public class GameWithoutTypeDTO {
    private Long id;
    private String imageURL;
    private String title;
    private String description;
    private String author;
    private Double mark;
}

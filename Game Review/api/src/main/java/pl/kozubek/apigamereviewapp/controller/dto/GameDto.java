package pl.kozubek.apigamereviewapp.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameDto {
    private Long id;
    private String title;
}

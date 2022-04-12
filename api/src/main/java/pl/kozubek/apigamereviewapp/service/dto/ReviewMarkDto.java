package pl.kozubek.apigamereviewapp.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewMarkDto {
    private Long idGame;
    private double mark;
}

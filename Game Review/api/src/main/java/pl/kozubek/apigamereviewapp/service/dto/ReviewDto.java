package pl.kozubek.apigamereviewapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Data
@Builder
public class ReviewDto {
    private String title;
    private String nick;
    private double mark;
    private Date date;
    private String description;
}

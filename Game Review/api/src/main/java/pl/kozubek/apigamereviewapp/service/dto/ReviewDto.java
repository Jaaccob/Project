package pl.kozubek.apigamereviewapp.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ReviewDto {
    private String title;
    private String nick;
    private double mark;
    private Date date;
    private String description;
}

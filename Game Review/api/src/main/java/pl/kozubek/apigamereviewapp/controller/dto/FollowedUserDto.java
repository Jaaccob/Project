package pl.kozubek.apigamereviewapp.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowedUserDto {
    private Long idUser;
    private Long idFollowedUser;
}

package pl.kozubek.apigamereviewapp.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private Long id;
    private String imageURL;
    private String nick;
    private String email;
    private String firstName;
    private String lastName;
}

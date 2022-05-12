package pl.kozubek.apigamereviewapp.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthenticationJwtToken {
    private final String jwtToken;
}

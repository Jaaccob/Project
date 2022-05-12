package pl.kozubek.apigamereviewapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import pl.kozubek.apigamereviewapp.enums.AuthenticationMessageEnum;
import pl.kozubek.apigamereviewapp.exception.InvalidNickOrPassword;
import pl.kozubek.apigamereviewapp.repository.UserRepository;
import pl.kozubek.apigamereviewapp.service.AuthenticationService;
import pl.kozubek.apigamereviewapp.service.JWTService;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsDto;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsServiceImpl;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserDetailsServiceImpl userDetailsService;

    private AuthenticationService authenticationService;

    private static final String USER_NICK = "userNick";
    private static final String USER_PASSWORD = "userPass";

    @BeforeEach
    public void setup() {
        var jwtService = new JWTService();
        authenticationService = new AuthenticationService(
                userDetailsService,
                jwtService,
                authenticationManager
        );
    }


    @Test
    void shouldThrowAnInvalidNickOrPasswordExceptionWhenUsernameIsIncorrect() {
        // given
        UserDetailsDto authenticationUser = new UserDetailsDto();
        authenticationUser.setUsername(USER_NICK);
        authenticationUser.setPassword(USER_PASSWORD);

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(USER_NICK, USER_PASSWORD);
        when(authenticationManager.authenticate(authenticationToken)).thenThrow(BadCredentialsException.class);

        // when
        var result = assertThrows(InvalidNickOrPassword.class,
                () -> authenticationService.createAuthenticationToken(authenticationUser));
        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(AuthenticationMessageEnum.INVALID_NICK_OR_PASSWORD.getMessage());
    }
}

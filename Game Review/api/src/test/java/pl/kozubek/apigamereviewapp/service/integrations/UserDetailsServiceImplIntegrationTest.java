package pl.kozubek.apigamereviewapp.service.integrations;

import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kozubek.apigamereviewapp.entity.User;
import pl.kozubek.apigamereviewapp.enums.AuthenticationMessageEnum;
import pl.kozubek.apigamereviewapp.exception.UserAlreadyExistsInDatabaseException;
import pl.kozubek.apigamereviewapp.exception.UserNotFoundException;
import pl.kozubek.apigamereviewapp.repository.UserRepository;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsDto;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsServiceImpl;

import javax.transaction.Transactional;

import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserDetailsServiceImplIntegrationTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private static final String USER_NICK = "userNick";
    private static final String USER_PASSWORD = "userPass";


    @Test
    void shouldReturnUserWithUserNickAndPasswordFromDatabase() {
        // given
        initDatabaseByPrimeUser();

        // when
        var result = userDetailsService.loadUserByUsername(USER_NICK);

        // then
        assertThat(result.getUsername()).isEqualTo(USER_NICK);
        assertThat(result.getPassword()).isEqualTo(USER_PASSWORD);

    }

    @Test
    void shouldSaveUserInToDatabase() {
        // given
        UserDetailsDto dto = new UserDetailsDto();
        dto.setUsername(USER_NICK);
        dto.setPassword(USER_PASSWORD);
        var bCryptPrefix = "$2a$10$";
        var bCryptRegex = "^[$]2[abxy]?[$](?:0[4-9]|[12][0-9]|3[01])[$][./0-9a-zA-Z]{53}$";

        // when
        var userId = userDetailsService.saveUser(dto);

        // then
        AssertionsForInterfaceTypes.assertThat(userId).isNotNull();
        var userEntityOptional = userRepository.findById(userId);
        var userEntity = userEntityOptional.get();
        assertAll(
                () -> assertThat(userEntity.getNick()).isEqualTo(USER_NICK),
                () -> assertThat(userEntity.getPassword()).contains(bCryptPrefix),
                () -> assertThat(userEntity.getPassword()).matches(Pattern.compile(bCryptRegex))
        );
    }

    @Test
    void shouldThrowExceptionWhenUserIsNotFoundInDatabase() {
        // given
        initDatabaseByPrimeUser();

        // when
        var result = assertThrows(UserNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("fakeUser"));

        // then
        assertThat(result.getMessage()).isEqualTo(AuthenticationMessageEnum.USER_NOT_FOUND.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExistsInDatabase() {
        // given
        initDatabaseByPrimeUser();
        UserDetailsDto dto = new UserDetailsDto();
        dto.setPassword(USER_PASSWORD);
        dto.setUsername(USER_NICK);

        // when
        var result = assertThrows(UserAlreadyExistsInDatabaseException.class,
                () -> userDetailsService.saveUser(dto));

        // then
        assertThat(result.getMessage()).isEqualTo(AuthenticationMessageEnum.USER_ALREADY_EXISTS.getMessage());

    }

    private void initDatabaseByPrimeUser() {
        User entity = new User();
        entity.setPassword(USER_PASSWORD);
        entity.setNick(USER_NICK);

        userRepository.save(entity);
    }
}
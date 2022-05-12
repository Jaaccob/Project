package pl.kozubek.apigamereviewapp.service.mapper;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import pl.kozubek.apigamereviewapp.entity.User;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsDto;

@Component
public class UserMapper {
    public User fromDtoToEntity(UserDetailsDto userDetailsDto) {
        var entity = new User();

        entity.setPassword(encodePassword(userDetailsDto.getPassword()));
        entity.setNick(userDetailsDto.getUsername());

        return entity;
    }

    private String encodePassword(String password) {
        var salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}

package pl.kozubek.apigamereviewapp.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.controller.dto.UserDto;
import pl.kozubek.apigamereviewapp.entity.User;
import pl.kozubek.apigamereviewapp.exception.UserAlreadyExistsInDatabaseException;
import pl.kozubek.apigamereviewapp.exception.UserNotFoundException;
import pl.kozubek.apigamereviewapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(int id) {
        return userRepository.findById((long) id).orElseThrow();
    }

    public User getUser(String nick) {
        return userRepository.findByNick(nick).orElseThrow();
    }

    @Transactional
    public User changeUser(UserDto userDto) {
        User userEdit = null;
        try {
            System.out.println(userDto);
            userEdit = userRepository.findById(userDto.getId()).orElseThrow();
            System.out.println(userEdit);
            if (userRepository.findByNick(userDto.getNick()).isPresent()) {
                System.out.println("Znalazl");
                throw new UserAlreadyExistsInDatabaseException();
            }
        } catch (UserNotFoundException e) {
            System.out.println("Nie znalazłem użytkownika");
        }
        assert userEdit != null;
        userEdit.setNick(userDto.getNick());
        userEdit.setEmail(userDto.getEmail());
        userEdit.setFirstName(userDto.getFirstName());
        userEdit.setLastName(userDto.getLastName());
        return userEdit;
    }
}

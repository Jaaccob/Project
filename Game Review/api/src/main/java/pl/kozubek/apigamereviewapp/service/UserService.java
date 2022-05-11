package pl.kozubek.apigamereviewapp.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.entity.User;
import pl.kozubek.apigamereviewapp.repository.UserRepository;

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
}

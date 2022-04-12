package pl.kozubek.apigamereviewapp.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kozubek.apigamereviewapp.entity.User;
import pl.kozubek.apigamereviewapp.service.UserService;

import java.util.List;

@RestController
@Data
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}

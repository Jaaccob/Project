package pl.kozubek.apigamereviewapp.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kozubek.apigamereviewapp.controller.dto.UserDto;
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
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @GetMapping("/user/findNick/{nick}")
    public User getIdUser(@PathVariable String nick) {
        return userService.getUser(nick);
    }

    @PutMapping("/change/user")
    public User changeUser(@RequestBody UserDto userDto){
        return userService.changeUser(userDto);
    }
}

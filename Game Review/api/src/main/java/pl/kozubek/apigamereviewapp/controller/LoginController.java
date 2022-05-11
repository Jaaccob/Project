package pl.kozubek.apigamereviewapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kozubek.apigamereviewapp.entity.User;

@RestController
public class LoginController {
    @PostMapping("/login")
    public void login(@RequestBody User user){

    }
}

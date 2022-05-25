package pl.kozubek.apigamereviewapp.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kozubek.apigamereviewapp.controller.dto.FollowedUserDto;
import pl.kozubek.apigamereviewapp.controller.dto.UserDto;
import pl.kozubek.apigamereviewapp.entity.FollowedUser;
import pl.kozubek.apigamereviewapp.service.FollowedUserService;

import java.util.List;

@RestController
@Data
public class FollowedUserController {
    private final FollowedUserService followedUserService;

    /**
     * Funkcja zwraca informacje na temat urzytkowników. Funkcja jest efektywna i powinno się ją stosować.
     * Kontrakt:
     * {
     * "id": ?,
     * "": ?,
     * "": ?
     * }
     *
     * @return Zwraca informację na temat obserwowanych urzytkowników
     */
    @GetMapping("/followedUsers/{id}")
    public List<UserDto> getFollowedUsers(@PathVariable Long id) {
        return followedUserService.getFollowedUsers(id);
    }

    @PutMapping("/followUserAdd")
    public FollowedUser addFollowUser(@RequestBody FollowedUserDto followedUserDto) {
        return followedUserService.addFollowUser(followedUserDto);
    }

    @DeleteMapping("/followUserDelete")
    public FollowedUser deleteFollowUser(@RequestBody FollowedUserDto followedUserDto) {
        return followedUserService.deleteFollowGame(followedUserDto);
    }
}

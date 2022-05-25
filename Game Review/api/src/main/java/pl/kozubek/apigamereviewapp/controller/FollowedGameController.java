package pl.kozubek.apigamereviewapp.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kozubek.apigamereviewapp.controller.dto.FollowedGameDto;
import pl.kozubek.apigamereviewapp.entity.FollowedGame;
import pl.kozubek.apigamereviewapp.service.FollowedGameService;
import pl.kozubek.apigamereviewapp.service.dto.GameWithTypeDTO;

import java.util.List;

@RestController
@Data
public class FollowedGameController {

    private final FollowedGameService followedGameService;

    @GetMapping("/followedGame/{id}")
    public List<FollowedGame> getFollowedGame(@PathVariable Long id){
        return followedGameService.getFollowedGame(id);
    }

    /**
     * Funkcja zwraca informacje na temat gier z typów. Stosowany jest mapper, który pozwala efektywniej
     * zadawanie pytania bazie danych. Funkcja jest efektywna i powinno się ją stosować.
     * Kontrakt:
     *{
     *  "id": ?,
     *  "imageURL": "?",
     *  "title": "?",
     *  "description": "?",
     *  "author": "?",
     *  "types": "?"
     * }
     *
     * @return Zwraca informację na temat gier z ich typami
     */
    @GetMapping("/followedGames/{id}")
    public List<GameWithTypeDTO> getFollowedGames(@PathVariable Long id){
        return followedGameService.getFollowedGames(id);
    }

    @PutMapping("/followGameAdd")
    public FollowedGame addFollowGame(@RequestBody FollowedGameDto followedGameDto){
        return followedGameService.addFollowGame(followedGameDto);
    }

    @DeleteMapping("/followGameDelete")
    public FollowedGame deleteFollowGame(@RequestBody FollowedGameDto followedGameDto){
        System.out.println(followedGameDto.getIdGame() + " " + followedGameDto.getIdUser());
        return followedGameService.deleteFollowGame(followedGameDto);
    }
}

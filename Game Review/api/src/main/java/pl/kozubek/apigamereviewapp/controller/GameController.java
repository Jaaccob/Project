package pl.kozubek.apigamereviewapp.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kozubek.apigamereviewapp.entity.Game;
import pl.kozubek.apigamereviewapp.service.GameService;
import pl.kozubek.apigamereviewapp.service.dto.GameWithTypeDTO;
import pl.kozubek.apigamereviewapp.service.dto.GameWithoutTypeDTO;

import java.util.List;


@RestController
@Data
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Funkcja zwraca wszystkie informacje na temat gier i ich powiązań z tabelą <code>Review</code> oraz
     * <code>ConnectGameType</code>. Funkcja jest nieefektywna gdyż wykorzystuje dużo zapytań do bazy danych. Nie
     * powinno się jej stosować. Funkcję powinno sie poprawić.
     * Kontrakt:
     * {
     *     "id": ?,
     *     "title": ?,
     *     "description": ?,
     *     "author": ?,
     *     "review": [{
     *                  "id": ?,
     *                  "idUser": ?,
     *                  "idGame": ?,
     *                  "mark": ?,
     *                  "date": "?",
     *                  "description": "?"
     *              }],
     *     "cgame": [{
     *                  "id": ?,
     *                  "idGame": ?,
     *                 "idType": ?
     *              }]
     * }
     * @return Zwraca encję <code>game</code> z jej powiązanymi encjami.
     */
    @GetMapping("/games")
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

    /**
     * Funkcja zwraca wszystkie informacje na temat wybranej gry i jej powiązania z tabelą <code>Review</code> oraz
     * <code>ConnectGameType</code>. Funkcja jest efektywna i można ją stosować.
     * Kontrakt:
     * {
     *     "id": ?,
     *     "title": "?",
     *     "description": "?",
     *     "author": "?",
     *     "review": [{
     *                  "id": ?,
     *                  "idUser": ?,
     *                  "idGame": ?,
     *                  "mark": ?,
     *                  "date": "?",
     *                  "description": "?"
     *              }],
     *     "cgame": [{
     *                  "id": ?,
     *                  "idGame": ?,
     *                  "idType": ?
     *              }]
     * }
     * @param id jednoznaczny indentyfikator gry
     * @return Zwraca krotkę z tabeli <code>game</code> wraz z jej powiązanymi wierszami.
     */
    @GetMapping("/singleGames/{id}")
    public Game getSingleGame(@PathVariable long id){
        return gameService.getSingleGame(id);
    }

    /**
     * Funkcja zwraca informacje na temat gier z ich typem. Stosowany jest mapper, który pozwala efektywniej zadawać
     * pytania bazie danych. Funkcja jest efektywna i powinno się ją stosować. Kontrakt z aplikacją powinien zostać
     * zmieniony gdyż nie jest efektywny, a przetwarzanie danych zostaje zrzucone na aplikację.
     * Kontrakt:
     * {
     *     "id": ?,
     *     "title": "?",
     *     "description": "?",
     *     "author": "?",
     *     "types": ?
     * }
     * @return Zwraca informację na temat gier z ich typami
     */
    @GetMapping("/gamesWithType")
    public List<GameWithTypeDTO> getGameWithType(){
        return gameService.getGameWithType();
    }

    /**
     * Funkcja zwraca informacje na temat gier bez typów ale z oceną. Stosowany jest mapper, który pozwala efektywniej
     * zadawanie pytania bazie danych. Funkcja jest efektywna i powinno się ją stosować. Kontrakt z aplikacją powinie
     * zostać zmieniony gdyż nie jest efektywny, a przetwarzanie danych zostaje zrzucone na aplikację.
     * Kontrakt:
     * {
     *     "id": ?,
     *     "imageURL": "?",
     *     "title": "?",
     *     "description": "?",
     *     "author": "?",
     *     "mark": ?
     * }
     * @return Zwraca informację na temat gier z ich typami
     */
    @GetMapping("/gamesWithoutType")
    public List<GameWithoutTypeDTO> getGameWithoutType(){
        return gameService.getGameWithoutGame();
    }

    /**
     * Funkcja dodaje nową grę do encji <code>game</code>. Funkcja jest efektywna dla bazy danych. Cała funkcja jest
     * objęta klauzurą <code>Transactional</code> odpowiedzialną za przejście funkcji jako jednej transakcji. Przez
     * tą funkcję mogą powstawać wartości null w encji <code>review</code>.
     * Kontrakt:
     * {
     *   "title": "?",
     *   "description": "?",
     *   "author": "?",
     *   "cgame": [
     *     {
     *       "idType": ?
     *     }
     *   ]
     * }
     * @param game obiekt encji game
     * @return Zwraca dodaną grę z jej wszystkimi zależnościami w bazie
     */
    @PostMapping("/games")
    public Game addGame(@RequestBody Game game){
        return gameService.addGame(game);
    }

    /**
     * Funkcja edytuje istniejącą grę w encji <code>game</code>. Funkcja jest efektywna dla bazy danych. Cała funkcja
     * jest objęta klauzurą <code>Transactional</code> odpowiedzialną za przejście funkcji jako jednej transakcji.
     * Kontrakt:
     * {
     *   "id": ?,
     *   "title": "?",
     *   "description": "?",
     *   "author": "?",
     *   "reviews": null,
     *   "cgame": [
     *     {
     *       "id": ?,
     *       "idGame": ?,
     *       "idType": ?
     *     }
     *   ]
     * }
     * @param game zmodyfikowany rekord encji <code>game</code>
     * @return Zwraca zmodyfikowany rekord z encji <code>game</code>
     */
    @PutMapping("/games")
    public Game editGame(@RequestBody Game game){
        return gameService.editGame(game);
    }
}

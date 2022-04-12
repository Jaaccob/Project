package pl.kozubek.apigamereviewapp.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.kozubek.apigamereviewapp.entity.Game;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {

    @Query("SELECT g FROM Game g")
    List<Game> findAllGames();

}

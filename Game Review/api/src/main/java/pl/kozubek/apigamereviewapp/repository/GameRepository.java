package pl.kozubek.apigamereviewapp.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.kozubek.apigamereviewapp.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {

    @Query("SELECT g FROM Game g")
    List<Game> findAllGames();

    @Query(value = "SELECT g FROM Game g WHERE g.title LIKE :title")
    Game findByTitle(@Param("title") String title);

    @Query(value = "SELECT g FROM Game g WHERE g.id = :id")
    Optional<Game> findById(@Param("id") Long id);
}

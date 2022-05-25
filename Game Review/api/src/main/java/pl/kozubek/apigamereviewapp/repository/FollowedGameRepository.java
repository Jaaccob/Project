package pl.kozubek.apigamereviewapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kozubek.apigamereviewapp.entity.FollowedGame;

import java.util.List;

@Repository
public interface FollowedGameRepository extends JpaRepository<FollowedGame, Long> {

    List<FollowedGame> findByIdUser(Long id);

    FollowedGame findByIdUserAndIdFollowGame(Long idUser, Long idFollowGame);
}

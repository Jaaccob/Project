package pl.kozubek.apigamereviewapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.kozubek.apigamereviewapp.entity.Review;
import pl.kozubek.apigamereviewapp.service.dto.ReviewMarkDto;

import java.util.List;


public interface ReviewRepository extends CrudRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.idGame = ?1")
    List<Review> findAllByIdGame(Long id);

    @Query("SELECT r FROM Review r")
    List<Review> findAllGame();

    @Query(value = "SELECT new pl.kozubek.apigamereviewapp.service.dto.ReviewMarkDto(r.idGame ,avg(r.mark)) FROM Review r group by r.idGame")
    List<ReviewMarkDto> findAllMark();
}

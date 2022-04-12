package pl.kozubek.apigamereviewapp.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kozubek.apigamereviewapp.controller.dto.GameDto;
import pl.kozubek.apigamereviewapp.entity.Review;
import pl.kozubek.apigamereviewapp.service.ReviewService;
import pl.kozubek.apigamereviewapp.service.dto.ReviewDto;

import java.util.List;

import static pl.kozubek.apigamereviewapp.controller.mapper.GameMapper.mapToGame;

@RestController
@Data
public class ReviewController {
    private static final Long EMPTY_ID = null;
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/reviewsForGame")
    public List<ReviewDto> getReviews(GameDto game) {
        return reviewService.getReviews(mapToGame(game));
    }

    @GetMapping("reviews/{id}")
    public List<ReviewDto> getAllReviewsById(@PathVariable int id){
        return reviewService.getAllReviewsById(id);
    }
}

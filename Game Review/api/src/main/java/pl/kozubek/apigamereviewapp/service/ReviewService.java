package pl.kozubek.apigamereviewapp.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.entity.Game;
import pl.kozubek.apigamereviewapp.entity.Review;
import pl.kozubek.apigamereviewapp.entity.User;
import pl.kozubek.apigamereviewapp.repository.GameRepository;
import pl.kozubek.apigamereviewapp.repository.ReviewRepository;
import pl.kozubek.apigamereviewapp.repository.UserRepository;
import pl.kozubek.apigamereviewapp.service.dto.ReviewDto;
import pl.kozubek.apigamereviewapp.service.mapper.ReviewDtoMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;


    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviewRepository.findAll().forEach(reviews::add);
        return reviews;
    }

    public List<ReviewDto> getReviews(Game game) {
        List<Review> review = reviewRepository.findAllByIdGame(game.getId());
        List<User> users = userRepository.findAllUsers();
        return ReviewDtoMapper.mapReviewToDtoList(game, users, review);
    }

    public List<ReviewDto> getAllReviewsById(long id) {
        List<Review> review = reviewRepository.findAllByIdGame(id);
        List<User> users = userRepository.findAllUsers();
        Game game = gameRepository.findById(id).orElseThrow();
        return ReviewDtoMapper.mapReviewToDtoList(game, users, review);
    }
}

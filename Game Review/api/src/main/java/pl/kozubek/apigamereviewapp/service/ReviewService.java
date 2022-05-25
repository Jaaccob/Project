package pl.kozubek.apigamereviewapp.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
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

    public List<ReviewDto> getSingleGame(String title) {
        System.out.println(title);
        List<Game> games = gameRepository.findAllGames();
        Game game = null;
        for (Game g : games) {
            if (g.getTitle().equals(title))
                game = g;
        }
        if (game != null) {
            List<Review> review = reviewRepository.findAllByIdGame(game.getId());
            List<User> users = userRepository.findAllUsers();
            return ReviewDtoMapper.mapReviewToDtoList(game, users, review);
        }
        throw new NotFoundException("Not found");
    }

    public Review getMark(Long id) {
        List<Review> reviews = reviewRepository.findAllByIdGame(id);
        double d = 0;
        for (Review review : reviews) {
            d += review.getMark();
        }
        d = d/reviews.size();
        reviews.get(0).setMark(d);
        return reviews.get(0);
    }
}

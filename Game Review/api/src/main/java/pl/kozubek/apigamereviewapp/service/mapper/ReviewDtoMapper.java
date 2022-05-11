package pl.kozubek.apigamereviewapp.service.mapper;

import pl.kozubek.apigamereviewapp.entity.Game;
import pl.kozubek.apigamereviewapp.entity.Review;
import pl.kozubek.apigamereviewapp.entity.User;
import pl.kozubek.apigamereviewapp.service.dto.ReviewDto;

import java.util.List;

public class ReviewDtoMapper {

    public static List<ReviewDto> mapReviewToDtoList(Game game, List<User> users, List<Review> reviews) {
        return reviews.stream()
                .flatMap(review -> users.stream()
                        .filter(user -> user.getId().equals(review.getIdUser()))
                        .map(user -> mapReviewToDto(game, user, review)))
                .toList();


    }

    private static ReviewDto mapReviewToDto(Game game, User user, Review review) {
        return ReviewDto.builder()
                .title(game.getTitle())
                .nick(user.getNick())
                .mark(review.getMark())
                .date(review.getDate())
                .description(review.getDescription())
                .build();
    }



}

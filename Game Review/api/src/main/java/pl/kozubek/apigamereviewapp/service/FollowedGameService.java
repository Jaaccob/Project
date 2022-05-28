package pl.kozubek.apigamereviewapp.service;

import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.controller.dto.FollowedGameDto;
import pl.kozubek.apigamereviewapp.entity.FollowedGame;
import pl.kozubek.apigamereviewapp.entity.Game;
import pl.kozubek.apigamereviewapp.repository.FollowedGameRepository;
import pl.kozubek.apigamereviewapp.repository.ReviewRepository;
import pl.kozubek.apigamereviewapp.service.dto.GameWithoutTypeDTO;
import pl.kozubek.apigamereviewapp.service.dto.ReviewMarkDto;
import pl.kozubek.apigamereviewapp.service.mapper.FollowedGameMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FollowedGameService {

    private final FollowedGameRepository followedGameRepository;
    private final GameService gameService;
    private ReviewRepository reviewRepository;

    public FollowedGameService(FollowedGameRepository followedGameRepository, GameService gameService, ReviewRepository reviewRepository) {
        this.followedGameRepository = followedGameRepository;
        this.gameService = gameService;
        this.reviewRepository = reviewRepository;
    }

    public List<FollowedGame> getFollowedGame(Long id) {
        return followedGameRepository.findByIdUser(id);
    }

    public List<pl.kozubek.apigamereviewapp.service.dto.FollowedGameDto> getFollowedGames(Long id) {
        List<FollowedGame> allFollowedGame = followedGameRepository.findByIdUser(id);
        List<Game> games = gameService.getAllGames();
        return FollowedGameMapper.mapToGameDtos(allFollowedGame, games);
    }

    public List<GameWithoutTypeDTO> getFollowedGamesWithoutType(Long id) {
        List<FollowedGame> allFollowedGame = followedGameRepository.findByIdUser(id);
        List<Game> games = gameService.getAllGames();
        List<ReviewMarkDto> mark = reviewRepository.findAllMark();
        return FollowedGameMapper.mapToGameWithotTypeDtos(allFollowedGame, games, mark);
    }

    @Transactional
    public FollowedGame addFollowGame(FollowedGameDto followedGameDto) {
        FollowedGame fg = new FollowedGame();
        fg.setIdFollowGame(followedGameDto.getIdGame());
        fg.setIdUser(followedGameDto.getIdUser());
        return followedGameRepository.save(fg);
    }

    @Transactional
    public FollowedGame deleteFollowGame(FollowedGameDto followedGameDto) {
        FollowedGame fg = followedGameRepository.findByIdUserAndIdFollowGame(followedGameDto.getIdUser(), followedGameDto.getIdGame());
        followedGameRepository.delete(fg);
        return fg;
    }

    public FollowedGame getFollowedGames(Long idUser, Long idGame) {
        return followedGameRepository.findByIdUserAndIdFollowGame(idUser, idGame);
    }
}

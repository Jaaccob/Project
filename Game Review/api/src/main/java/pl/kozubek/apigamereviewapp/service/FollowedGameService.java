package pl.kozubek.apigamereviewapp.service;

import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.controller.dto.FollowedGameDto;
import pl.kozubek.apigamereviewapp.entity.FollowedGame;
import pl.kozubek.apigamereviewapp.repository.FollowedGameRepository;
import pl.kozubek.apigamereviewapp.service.dto.GameWithTypeDTO;
import pl.kozubek.apigamereviewapp.service.mapper.FollowedGameMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FollowedGameService {

    private final FollowedGameRepository followedGameRepository;
    private final GameService gameService;

    public FollowedGameService(FollowedGameRepository followedGameRepository, GameService gameService) {
        this.followedGameRepository = followedGameRepository;
        this.gameService = gameService;
    }

    public List<FollowedGame> getFollowedGame(Long id) {
        return followedGameRepository.findByIdUser(id);
    }

    public List<GameWithTypeDTO> getFollowedGames(Long id) {
        List<FollowedGame> allFollowedGame = followedGameRepository.findByIdUser(id);
        List<GameWithTypeDTO> games = gameService.getGameWithType();
        return FollowedGameMapper.mapToGameDtos(allFollowedGame, games);
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
}

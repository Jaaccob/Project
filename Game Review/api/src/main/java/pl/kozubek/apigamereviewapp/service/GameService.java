package pl.kozubek.apigamereviewapp.service;

import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.entity.ConnectGameType;
import pl.kozubek.apigamereviewapp.entity.Game;
import pl.kozubek.apigamereviewapp.entity.Type;
import pl.kozubek.apigamereviewapp.repository.ConGameTypeRepository;
import pl.kozubek.apigamereviewapp.repository.GameRepository;
import pl.kozubek.apigamereviewapp.repository.ReviewRepository;
import pl.kozubek.apigamereviewapp.repository.TypeRepository;
import pl.kozubek.apigamereviewapp.service.dto.GameWithTypeDTO;
import pl.kozubek.apigamereviewapp.service.dto.GameWithoutTypeDTO;
import pl.kozubek.apigamereviewapp.service.mapper.GameWithTypeDTOMapper;
import pl.kozubek.apigamereviewapp.service.mapper.GameWithoutTypMapper;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final TypeRepository typeRepository;
    private final ConGameTypeRepository conGameTypeRepository;
    private ReviewRepository reviewRepository;

    public GameService(GameRepository gameRepository, TypeRepository typeRepository, ConGameTypeRepository conGameTypeRepository
            , ReviewRepository reviewRepository) {
        this.gameRepository = gameRepository;
        this.typeRepository = typeRepository;
        this.conGameTypeRepository = conGameTypeRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAllGames();
    }

    public Game getSingleGame(long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    public List<GameWithTypeDTO> getGameWithType() {
        List<Game> allGame = gameRepository.findAllGames();
        List<Type> allType = typeRepository.findAllType();
        List<ConnectGameType> allCon = conGameTypeRepository.findAll();

        return GameWithTypeDTOMapper.mapToGameDtos(allCon, allGame, allType);
    }

    @Transactional
    public Game addGame(Game game) {
        Game g = gameRepository.save(game);
        for (ConnectGameType cgt : game.getCGame()) {
            cgt.setIdGame(g.getId());
            conGameTypeRepository.save(cgt);
        }
        return g;
    }

    @Transactional
    public Game editGame(Game game) {
        Game gameEdited = gameRepository.findById(game.getId()).orElseThrow();
        gameEdited.setTitle(game.getTitle());
        gameEdited.setDescription(game.getDescription());
        gameEdited.setAuthor(game.getAuthor());
        return gameEdited;
    }

    public List<GameWithoutTypeDTO> getGameWithoutGame() {
        return GameWithoutTypMapper.mapToGameDtos(gameRepository.findAllGames(), reviewRepository.findAllMark());
    }

    public List<GameWithoutTypeDTO> getFourBestGames() {

        List<GameWithoutTypeDTO> gameWithoutTypeDTOS = GameWithoutTypMapper.mapToGameDtos(gameRepository.findAllGames(), reviewRepository.findAllMark());
        return gameWithoutTypeDTOS.stream()
                .sorted(Comparator.comparing(GameWithoutTypeDTO::getMark).reversed())
                .limit(4)
                .toList();
    }

    public GameWithoutTypeDTO getSingleGame(String title) {
        return GameWithoutTypMapper.mapToSingleGameDto(gameRepository.findByTitle(title),reviewRepository.findAllMark());
    }
}

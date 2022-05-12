package pl.kozubek.apigamereviewapp.service.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.exception.UserAlreadyExistsInDatabaseException;
import pl.kozubek.apigamereviewapp.exception.UserNotFoundException;
import pl.kozubek.apigamereviewapp.repository.UserRepository;
import pl.kozubek.apigamereviewapp.service.mapper.UserMapper;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class.getName());

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDetailsServiceImpl(UserRepository userRepository,
                                  UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Searching user = " + username);
        var entity = userRepository
                .findByNick(username)
                .orElseThrow(UserNotFoundException::new);

        return new User(entity.getNick(), entity.getPassword(), Collections.emptyList());
    }

    public Long saveUser(UserDetailsDto userDetailsDto) {
        validateIfUserExists(userDetailsDto);
        var entity = userMapper.fromDtoToEntity(userDetailsDto);
        var savedEntity = userRepository.save(entity);
        LOGGER.info("User saved = " + savedEntity);

        return savedEntity.getId();
    }

    public boolean loginUser(UserDetailsDto userDetailsDto) {
        var entity = userRepository.findByNick(userDetailsDto.getUsername());
        return entity.isPresent();
    }

    public void validateIfUserExists(UserDetailsDto userDetailsDto) {
        var entity = userRepository.findByNick(userDetailsDto.getUsername());

        if (entity.isPresent()) {
            throw new UserAlreadyExistsInDatabaseException();
        }
    }
}

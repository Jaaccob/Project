package pl.kozubek.apigamereviewapp.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.controller.dto.FollowedUserDto;
import pl.kozubek.apigamereviewapp.controller.dto.UserDto;
import pl.kozubek.apigamereviewapp.entity.FollowedUser;
import pl.kozubek.apigamereviewapp.entity.User;
import pl.kozubek.apigamereviewapp.repository.FollowedUserRepository;
import pl.kozubek.apigamereviewapp.repository.UserRepository;
import pl.kozubek.apigamereviewapp.service.mapper.FollowedUserMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Data
public class FollowedUserService {

    private FollowedUserRepository followedUserRepository;
    private UserRepository userRepository;

    public FollowedUserService(FollowedUserRepository followedUserRepository, UserRepository userRepository) {
        this.followedUserRepository = followedUserRepository;
        this.userRepository = userRepository;
    }

    public List<UserDto> getFollowedUsers(Long id) {
        List<FollowedUser> allFollowUser = followedUserRepository.findAllByIdUser(id);
        List<User> users = userRepository.findAllUsers();
        return FollowedUserMapper.mapToUserDtos(allFollowUser, users);
    }

    @Transactional
    public FollowedUser addFollowUser(FollowedUserDto followedUserDto) {
        FollowedUser fu = new FollowedUser();
        fu.setIdUserFollow(followedUserDto.getIdFollowedUser());
        fu.setIdUser(followedUserDto.getIdUser());
        return followedUserRepository.save(fu);
    }

    @Transactional
    public FollowedUser deleteFollowGame(FollowedUserDto followedUserDto) {
        FollowedUser fu = followedUserRepository.findByIdUserAndIdUserFollow(followedUserDto.getIdUser(), followedUserDto.getIdFollowedUser());
        followedUserRepository.delete(fu);
        return fu;
    }
}

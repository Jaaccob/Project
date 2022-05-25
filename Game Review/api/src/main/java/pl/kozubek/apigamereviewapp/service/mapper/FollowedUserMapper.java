package pl.kozubek.apigamereviewapp.service.mapper;

import pl.kozubek.apigamereviewapp.controller.dto.UserDto;
import pl.kozubek.apigamereviewapp.entity.FollowedUser;
import pl.kozubek.apigamereviewapp.entity.User;

import java.util.ArrayList;
import java.util.List;

public class FollowedUserMapper {
    private FollowedUserMapper() {
    }

    public static List<UserDto> mapToUserDtos(List<FollowedUser> followedUsers, List<User> users) {
        List<UserDto> u = new ArrayList<>();
        for (FollowedUser followedUser : followedUsers) {
            for (User user : users) {
                if (followedUser.getIdUserFollow().equals(user.getId())) {
                    u.add(UserDto.builder()
                            .id(user.getId())
                            .nick(user.getNick())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail())
                            .imageURL(null)
                            .build());
                }
            }
        }
        return u;
    }

}

package pl.kozubek.apigamereviewapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kozubek.apigamereviewapp.entity.FollowedUser;

import java.util.List;

@Repository
public interface FollowedUserRepository extends JpaRepository<FollowedUser, Long> {

    List<FollowedUser> findAllByIdUser(Long id);

    FollowedUser findByIdUserAndIdUserFollow(Long idUser, Long idUserFollow);
}

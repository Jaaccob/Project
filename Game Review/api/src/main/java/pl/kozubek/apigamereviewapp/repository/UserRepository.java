package pl.kozubek.apigamereviewapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kozubek.apigamereviewapp.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT u FROM User u")
    List<User> findAllUsers();

    Optional<User> findByNick(String nick);
}

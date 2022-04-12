package pl.kozubek.apigamereviewapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.kozubek.apigamereviewapp.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT u FROM User u")
    List<User> findAllUsers();
}

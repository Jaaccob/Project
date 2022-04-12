package pl.kozubek.apigamereviewapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kozubek.apigamereviewapp.entity.ConnectGameType;

import java.util.List;

@Repository
public interface ConGameTypeRepository extends JpaRepository<ConnectGameType, Long> {

}

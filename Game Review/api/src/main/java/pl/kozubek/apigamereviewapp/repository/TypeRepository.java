package pl.kozubek.apigamereviewapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kozubek.apigamereviewapp.entity.Type;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAllByIdIn(List<Long> ids);

    @Query("SELECT t FROM Type t")
    List<Type> findAllType();
}

package most_valuable_middle.game_service.persistence.jpa.repository;

import most_valuable_middle.game_service.persistence.jpa.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaQuestionRepository extends JpaRepository<Question, Integer> {

    @Query(nativeQuery = true, value = "SELECT id FROM question;")
    List<Integer> getAllIds();
}

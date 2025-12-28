package most_valuable_middle.game_service.persistence.jpa.repository;

import most_valuable_middle.game_service.persistence.jpa.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGameRepository extends JpaRepository<Game, Long> {
}

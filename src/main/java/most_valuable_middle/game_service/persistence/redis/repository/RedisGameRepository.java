package most_valuable_middle.game_service.persistence.redis.repository;

import most_valuable_middle.game_service.persistence.redis.model.GameModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisGameRepository extends CrudRepository<GameModel, Long> {
}

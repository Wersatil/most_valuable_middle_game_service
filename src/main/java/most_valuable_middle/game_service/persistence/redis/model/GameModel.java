package most_valuable_middle.game_service.persistence.redis.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import most_valuable_middle.game_service.persistence.jpa.entity.Player;
import most_valuable_middle.game_service.persistence.jpa.entity.Question;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash("game")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameModel {

    @Id
    private Long id;
    private Integer queuePosition;
    private Question question;
    private List<Player> players;
}

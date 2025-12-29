package most_valuable_middle.game_service.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import most_valuable_middle.game_service.domain.model.Player;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;
    private Integer queuePosition;
    private Integer questionId;
    private String question;
    private String answer;
    private List<Player> players;
}

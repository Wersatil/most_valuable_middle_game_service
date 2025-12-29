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


    //По новой модели теперь ответ не скрывается. Один и тот же всегда висит на экране

    //И имя тоже, почему-то не отображается


    private Long id;
    private Integer queuePosition;
    private Integer questionId;
    private String question;
    private String answer;
    private List<Player> players;
}

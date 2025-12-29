package most_valuable_middle.game_service.application.service.game;

import most_valuable_middle.game_service.web.dto.GameDto;

import java.util.List;

public interface GameService {
    GameDto startGame(List<String> playerNames);

    GameDto moveToNextQuestion(int playerId, int scores, long gameId);
}

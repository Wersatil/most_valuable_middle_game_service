package most_valuable_middle.game_service.application.service.game;

import most_valuable_middle.game_service.persistence.redis.model.GameModel;

import java.util.List;

public interface GameService {
    GameModel startGame(List<String> playerNames);

    GameModel moveToNextQuestion(int playerId, int scores, long gameId);
}

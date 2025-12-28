package most_valuable_middle.game_service.application.service.game;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import most_valuable_middle.game_service.persistence.jpa.entity.Game;
import most_valuable_middle.game_service.persistence.jpa.entity.Player;
import most_valuable_middle.game_service.persistence.jpa.entity.Question;
import most_valuable_middle.game_service.persistence.jpa.repository.JpaGameRepository;
import most_valuable_middle.game_service.persistence.jpa.repository.JpaQuestionRepository;
import most_valuable_middle.game_service.persistence.redis.model.GameModel;
import most_valuable_middle.game_service.persistence.redis.repository.RedisGameRepository;
import most_valuable_middle.game_service.web.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource("file:src/main/resources/properties/random_avatar.properties")
public class GameServiceImpl implements GameService {
    private final int START_QUEUE_POSITION = 0;
    private final JpaQuestionRepository jpaQuestionRepository;
    private final RedisGameRepository redisGameRepository;
    private final JpaGameRepository jpaGameRepository;

    @Value("${avatars}")
    private List<String> avatars;

    @Value("${score.price_in_rubles}")
    private int scoreInRubles;

    private Queue<Integer> questionIds = new ArrayDeque<>();

    @PostConstruct
    public void fillQuestionList() {
        List<Integer> ids = jpaQuestionRepository.getAllIds();
        Collections.shuffle(ids);
        questionIds.addAll(ids);
    }

    @Override
    public GameModel startGame(List<String> playerNames) {
        Game game = jpaGameRepository.save(new Game());
        GameModel gameModel = new GameModel(
                game.getId(),
                START_QUEUE_POSITION,
                getRandomQuestion(),
                createPlayers(playerNames)
                );
        redisGameRepository.save(gameModel);
        return gameModel;
    }

    @Override
    public GameModel moveToNextQuestion(int playerId, int scores, long gameId) {
        GameModel game = redisGameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("К сожалению, время игры истекло."));
        if (scores != 0) {
            addScores(playerId, scores, game);
        }
        game.setQuestion(getRandomQuestion());
        game.setQueuePosition(moveQueuePosition(game.getQueuePosition(), game.getPlayers().size()));
        redisGameRepository.save(game);
        return game;
    }

    private Question getRandomQuestion() {
        Integer nextQuestionId = questionIds.poll();
        if (nextQuestionId == null) {
            Question emptyQuestionEntity = new Question();
            emptyQuestionEntity.setQuestionText(
                    "Поздравляем, вы прошли все вопросы! Для начала новой игры перезапустите сервер");
            emptyQuestionEntity.setAnswer(
                    "Есть вероятность, что вопросы уже закончились, а ответы на них - еще нет? )");
            return emptyQuestionEntity;
        }
        return jpaQuestionRepository.findById(nextQuestionId).get();
    }

    private void addScores(int playerId, int scores, GameModel game) {
        Player player = game.getPlayers().get(playerId);
        player.setOfferSum(scoreInRubles * scores + player.getOfferSum());
    }

    private Integer moveQueuePosition(int currentQueuePosition, int queueSize) {
        currentQueuePosition++;
        if (currentQueuePosition == queueSize) {
            currentQueuePosition = 0;
        }
        return currentQueuePosition;
    }

    private List<Player> createPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        int playerId = 0;
        Random random = new Random();
        for (String playerName : playerNames) {
            if (playerName == null || playerName.isEmpty()) {
                continue;
            }
            Player player = new Player();
            player.setId(playerId++);
            player.setName(playerName);
            int randomElementIndex = random.nextInt(avatars.size());
            player.setAvatar(avatars.get(randomElementIndex));
            players.add(player);
        }
        return players;
    }
}

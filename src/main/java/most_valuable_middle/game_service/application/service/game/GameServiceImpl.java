package most_valuable_middle.game_service.application.service.game;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import most_valuable_middle.game_service.application.mapper.GameMapper;
import most_valuable_middle.game_service.persistence.jpa.entity.Game;
import most_valuable_middle.game_service.domain.model.Player;
import most_valuable_middle.game_service.persistence.jpa.entity.Question;
import most_valuable_middle.game_service.persistence.jpa.repository.JpaGameRepository;
import most_valuable_middle.game_service.persistence.jpa.repository.JpaQuestionRepository;
import most_valuable_middle.game_service.persistence.redis.model.GameModel;
import most_valuable_middle.game_service.persistence.redis.repository.RedisGameRepository;
import most_valuable_middle.game_service.web.dto.GameDto;
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
    private final GameMapper gameMapper;

    @Value("${avatars}")
    private List<String> avatars;

    @Value("${score.price_in_rubles}")
    private int scoreInRubles;

    private Queue<Question> questions = new ArrayDeque<>();

    @PostConstruct
    public void fillQuestionList() {
        List<Question> questionListFromDb = jpaQuestionRepository.findAll();
        Collections.shuffle(questionListFromDb);
        questions.addAll(questionListFromDb);
    }

    @Override
    public GameDto startGame(List<String> playerNames) {
        Game game = jpaGameRepository.save(new Game());
        Question randomQuestion = getRandomQuestion();
        GameModel gameModel = new GameModel(
                game.getId(),
                START_QUEUE_POSITION,
                createPlayers(playerNames)
                );
        redisGameRepository.save(gameModel);
        GameDto gameDto = gameMapper.toGameDto(gameModel);
        gameDto.setQuestionId(randomQuestion.getId());
        gameDto.setQuestion(randomQuestion.getQuestionText());
        gameDto.setAnswer(randomQuestion.getAnswer());
        return gameDto;
    }

    @Override
    public GameDto moveToNextQuestion(int playerId, int scores, long gameId) {
        GameModel game = redisGameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("К сожалению, время игры истекло."));
        if (scores != 0) {
            addScores(playerId, scores, game);
        }
        Question randomQuestion = getRandomQuestion();
        game.setQueuePosition(moveQueuePosition(game.getQueuePosition(), game.getPlayers().size()));
        redisGameRepository.save(game);
        GameDto gameDto = gameMapper.toGameDto(game);
        gameDto.setQuestionId(randomQuestion.getId());
        gameDto.setQuestion(randomQuestion.getQuestionText());
        gameDto.setAnswer(randomQuestion.getAnswer());
        return gameDto;
    }

    private Question getRandomQuestion() {
        Question randomQuestion = questions.poll();
        if (randomQuestion == null) {
            Question emptyQuestionEntity = new Question();
            emptyQuestionEntity.setQuestionText(
                    "Поздравляем, вы прошли все вопросы! Для начала новой игры перезапустите сервер");
            emptyQuestionEntity.setAnswer(
                    "Есть вероятность, что вопросы уже закончились, а ответы на них - еще нет? )");
            return emptyQuestionEntity;
        }
        return randomQuestion;
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

package most_valuable_middle.game_service.web.controller;

import lombok.RequiredArgsConstructor;
import most_valuable_middle.game_service.application.service.game.GameService;
import most_valuable_middle.game_service.web.dto.GameDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/questions")
@RequiredArgsConstructor
public class GameRestController {
    private final GameService gameService;

    @GetMapping("/next/{playerId}/{gameId}/{scores}")
    public GameDto next(
            @PathVariable int playerId,
            @PathVariable long gameId,
            @PathVariable int scores
    ) {
        return gameService.moveToNextQuestion(playerId, scores, gameId);
    }
}

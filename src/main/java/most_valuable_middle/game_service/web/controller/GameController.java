package most_valuable_middle.game_service.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import most_valuable_middle.game_service.application.service.game.GameService;
import most_valuable_middle.game_service.web.dto.GameDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/game-start")
    public String startGame(
            @RequestParam("playerNames") List<String> playerNames, Model model) {
        GameDto game = gameService.startGame(playerNames);
        model.addAttribute("currentPlayer", game.getPlayers().get(game.getQueuePosition()));
        model.addAttribute("game", game);
        return "game";
    }

    @GetMapping("/next/{playerId}/{gameId}/{scores}")
    public String getNextQuestion(
            Model model,
            @ModelAttribute("playerId") int playerId,
            @ModelAttribute("gameId") int gameId,
            @ModelAttribute("scores") int scores
    ) {
        GameDto game = gameService.moveToNextQuestion(playerId, scores, gameId);
        model.addAttribute("currentPlayer", game.getPlayers().get(game.getQueuePosition()));
        model.addAttribute("game", game);
        return "game";
    }
}

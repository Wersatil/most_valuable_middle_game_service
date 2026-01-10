package most_valuable_middle.game_service.integration.web.controller;

import most_valuable_middle.game_service.web.dto.GameDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameRestControllerIt {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void moveToNextQuestion_returnsGameDto() {
        ResponseEntity<GameDto> response =
                restTemplate.getForEntity("/api/v2/questions/next/1/59/1", GameDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

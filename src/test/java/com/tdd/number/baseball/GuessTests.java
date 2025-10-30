package com.tdd.number.baseball;

import com.tdd.number.baseball.domain.Game;
import com.tdd.number.baseball.domain.Score;
import com.tdd.number.baseball.service.BaseballService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest
public class GuessTests {

    private final BaseballService baseballService;

    public GuessTests(@Autowired BaseballService baseballService) {
        this.baseballService = baseballService;
    }

    @Test public void contextLoads() {}

    @Test
    public void test_guess() {
        Game game = baseballService.createGame("123");

        assertScore(game, "568", 0L,0L);
        assertScore(game, "156", 1L,0L);
        assertScore(game, "025", 1L,0L);
        assertScore(game, "453", 1L,0L);
        assertScore(game, "129", 2L,0L);
        assertScore(game, "103", 2L,0L);
        assertScore(game, "023", 2L,0L);
        assertScore(game, "123", 3L,0L);
        assertScore(game, "910", 0L,1L);
        assertScore(game, "901", 0L,1L);
        assertScore(game, "294", 0L,1L);
        assertScore(game, "892", 0L,1L);
        assertScore(game, "389", 0L,1L);
        assertScore(game, "938", 0L,1L);
        assertScore(game, "234", 0L,2L);
        assertScore(game, "315", 0L,2L);
        assertScore(game, "312", 0L,3L);
    }

    private void assertScore(Game game, String number, Long expectedStrikes, Long expectedBalls) {
        Score score = baseballService.guess(game, number);

        assertThat(score.getStrikes())
                .as("strike count should be zero when no digits match")
                .isEqualTo(expectedStrikes);

        assertThat(score.getBalls())
                .as("ball count should be zero when no digits match")
                .isEqualTo(expectedBalls);
    }

    @Test
    public void test_guess_invalid_numbers() {
        Game game = baseballService.createGame("123");

        assertGuessThrows(game, "12");
        assertGuessThrows(game, "12345");
        assertGuessThrows(game, "abc");
        assertGuessThrows(game, "12a");
        assertGuessThrows(game, "112");

        assertThatCode(() -> baseballService.guess(game, "987"))
                .as("should not throw for valid guess")
                .doesNotThrowAnyException();

    }

    private void assertGuessThrows(Game game, String input) {
        assertThatThrownBy(() -> baseballService.guess(game,input))
                .as("guess should throw error for invalid input: %s", input)
                .isInstanceOf(IllegalArgumentException.class);
    }
}

package com.tdd.number.baseball;

import com.tdd.number.baseball.domain.Game;
import com.tdd.number.baseball.service.BaseballService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest
public class GameTests {
    private final BaseballService baseballService;

    public GameTests(@Autowired BaseballService baseballService) {
        this.baseballService = baseballService;
    }

    @Test void should_create_game_with_valid_number() {
        Game game = baseballService.createGame("012");
        assertThat(game).as("game not created").isNotNull();
    }

    @Test
    void invalid_numbers_should_throw_exception() {
        assertInvalidNumber("23");
        assertInvalidNumber("12345");
        assertInvalidNumber("abc");
        assertInvalidNumber("13z");
        assertInvalidNumber("12e3");
        assertInvalidNumber("4499");
        assertInvalidNumber("001");
    }

    @Test
    void valid_numbers_should_not_throw_exception() {
        assertValidNumber("123");
        assertValidNumber("9876");
    }

    private void assertInvalidNumber(String number) {
        assertThatThrownBy(() -> baseballService.createGame(number))
                .as("should throw for invalid number: %s", number)
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void assertValidNumber(String number) {
        assertThatCode(() -> baseballService.createGame(number))
                .as("should not throw for valid number: %s", number)
                .doesNotThrowAnyException();
    }
}

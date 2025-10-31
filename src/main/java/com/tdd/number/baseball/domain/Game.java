package com.tdd.number.baseball.domain;

public class Game {
    private final GameNumber gameNumber;

    public Game(GameNumber gameNumber) {
        this.gameNumber = gameNumber;
    }

    public Score guess(GameNumber guessedNumber) {
        validateSameLength(guessedNumber);
        return gameNumber.compareWith(guessedNumber);
    }

    private void validateSameLength(GameNumber guessedNumber) {
        if (guessedNumber.length() != gameNumber.length()) {
            throw new IllegalArgumentException("길이를 틀렸습니다.");
        }
    }
}
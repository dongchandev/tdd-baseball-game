package com.tdd.number.baseball.domain;

public class Game {
    private final GameNumber gameNumber;

    public Game(GameNumber gameNumber) {
        this.gameNumber = gameNumber;
    }

    public Score guess(GameNumber guessedNumber) {
        if(guessedNumber.length() != gameNumber.length()) {
            throw new IllegalArgumentException("길이를 틀렸습니다.");
        }

        Long strikes = 0L;
        Long balls = 0L;

        for (int i = 0; i < guessedNumber.length(); i++) {
            char c = guessedNumber.charAt(i);
            if (gameNumber.charAt(i) == c) strikes++;
            else if (gameNumber.contains(c)) balls++;
        }

        return new Score(strikes,balls);
    }
}

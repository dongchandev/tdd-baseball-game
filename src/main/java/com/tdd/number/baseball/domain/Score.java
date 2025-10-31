package com.tdd.number.baseball.domain;

public class Score {
    private Long strikes;
    private Long balls;

    public Score(Long strikes, Long balls) {
        this.strikes = strikes;
        this.balls = balls;
    }

    public Long getStrikes() {
        return strikes;
    }

    public Long getBalls() {
        return balls;
    }
}


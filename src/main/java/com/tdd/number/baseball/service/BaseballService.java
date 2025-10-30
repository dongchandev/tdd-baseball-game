package com.tdd.number.baseball.service;

import com.tdd.number.baseball.domain.Game;
import com.tdd.number.baseball.domain.GameNumber;
import com.tdd.number.baseball.domain.Score;
import org.springframework.stereotype.Service;

@Service
public class BaseballService {

    public Game createGame(String number) {
        GameNumber gameNumber = new GameNumber(number);
        Game game = new Game(gameNumber);
        //DB연동 logic
        return game;
    }

    public Score guess(Game game, String number) {
        GameNumber gameNumber = new GameNumber(number);
        Score score = game.guess(gameNumber);
        //DB연동 logic
        return score;
    }
}

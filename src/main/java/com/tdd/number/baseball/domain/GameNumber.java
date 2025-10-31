package com.tdd.number.baseball.domain;

public class GameNumber {
    private final String value;

    public GameNumber(String value) {
        if (value.length() < 3 || value.length() > 4) {
            throw new IllegalArgumentException("길이는 3자리 또는 4자리여야 합니다.");
        }

        if (!value.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("숫자만 입력되야 합니다.");
        }

        if (value.chars().distinct().count() != value.length()) {
            throw new IllegalArgumentException("같은 숫자는 허용되지 않습니다.");
        }

        this.value = value;
    }

    public int length() { return value.length(); }
    public char charAt(int i) { return value.charAt(i); }
    public boolean contains(char c) { return value.indexOf(c) >= 0; }

    public Score compareWith(GameNumber other) {
        Long strikes = 0L;
        Long balls = 0L;

        for (int i = 0; i < other.length(); i++) {
            char c = other.charAt(i);
            if (this.charAt(i) == c) strikes++;
            else if (this.contains(c)) balls++;
        }
        return new Score(strikes, balls);
    }
}

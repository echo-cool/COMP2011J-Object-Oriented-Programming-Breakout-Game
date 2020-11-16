package com.echo.model;

public class Score {
    private final String name;
    private final int Score;

    public Score(String name, int score) {
        this.name = name;
        this.Score = score;
    }

    public String getPlayerName() {
        return name;
    }

    public int getPlayerScore() {
        return Score;
    }

}

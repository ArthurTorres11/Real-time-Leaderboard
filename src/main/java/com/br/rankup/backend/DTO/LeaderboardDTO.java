package com.br.rankup.backend.DTO;

public class LeaderboardDTO {
    private String name;
    private int score;
    private int position;

    public LeaderboardDTO(String name, int score, int position) {
        this.name = name;
        this.score = score;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

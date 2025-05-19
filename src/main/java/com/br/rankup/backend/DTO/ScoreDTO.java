package com.br.rankup.backend.DTO;

import jakarta.validation.constraints.NotNull;

public class ScoreDTO {
    private Integer score;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Integer userId;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

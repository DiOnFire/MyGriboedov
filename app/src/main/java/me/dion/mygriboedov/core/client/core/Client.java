package me.dion.mygriboedov.core.client.core;

import me.dion.mygriboedov.core.client.quiz.Question;

public class Client {
    private int score;
    private final String nickname;

    public Client(String nickname) {
        this.score = 0;
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(Question question) {
        this.score += question.getMaxScore();
    }
}

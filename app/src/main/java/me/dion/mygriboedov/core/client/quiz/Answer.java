package me.dion.mygriboedov.core.client.quiz;

public class Answer {
    private final Question question;
    private final String answer;

    public Answer(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public boolean compare() {
        return this.question.getCorrect().equals(this.answer);
    }

    public Question getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }
}

package me.dion.mygriboedov.quiz;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Question implements Serializable {
    private final List<String> variables;
    private final int maxScore;
    private final String correct, name;

    public Question(String name, int score, String correct, String... variables) {
        this.name = name;
        this.correct = correct;
        this.variables = Arrays.asList(variables);
        this.maxScore = score;
    }

    public List<String> getVariables() {
        return variables;
    }

    public String getCorrect() {
        return correct;
    }

    public String getName() {
        return name;
    }

    public int getMaxScore() {
        return maxScore;
    }
}

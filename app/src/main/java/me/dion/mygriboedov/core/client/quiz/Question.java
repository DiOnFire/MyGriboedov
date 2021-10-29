package me.dion.mygriboedov.core.client.quiz;

import java.util.ArrayList;
import java.util.Arrays;

public class Question {
    private final ArrayList<String> variables;
    private final int maxScore;
    private final String correct, name;

    public Question(String name, int score, String correct, String... variables) {
        this.name = name;
        this.correct = correct;
        this.variables = (ArrayList<String>) Arrays.asList(variables);
        this.maxScore = score;
    }

    public ArrayList<String> getVariables() {
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

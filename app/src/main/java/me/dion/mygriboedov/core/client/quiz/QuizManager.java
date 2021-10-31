package me.dion.mygriboedov.core.client.quiz;

import java.util.ArrayList;

public class QuizManager {
    private static ArrayList<Question> questions = new ArrayList<>();

    public void loadQuestions() {
        // TODO: question loader
    }

    public static Question findQuestionByName(String name) {
        for (Question question : questions) {
            if (question.getName().equals(name)) {
                return question;
            }
        }
        return null;
    }
}

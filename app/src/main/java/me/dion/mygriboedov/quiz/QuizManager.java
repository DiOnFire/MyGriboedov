package me.dion.mygriboedov.quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import me.dion.mygriboedov.util.QuestionConverter;

public class QuizManager implements Serializable {
    private final ArrayList<Question> questions = new ArrayList<>();
    private int totalScore = 0;
    private int count = 0;

    public void loadQuestions() {
        questions.add(QuestionConverter.convertStringToQuestion("bebra?:24:bebrochka:frede:2324:bebrochka"));
        questions.add(QuestionConverter.convertStringToQuestion("frede?:12:freede:frede:fred:freede"));
        questions.add(QuestionConverter.convertStringToQuestion("bebbra?:24:bebrochka:frede:2324:bebrochka"));
        questions.add(QuestionConverter.convertStringToQuestion("freede?:12:freede:frede:fred:freede"));
        questions.add(QuestionConverter.convertStringToQuestion("beebra?:24:bebrochka:frede:2324:bebrochka"));
        Collections.shuffle(questions);
    }

    public Question findQuestionByName(String name) {
        for (Question question : questions) {
            if (question.getName().equals(name)) {
                return question;
            }
        }
        return null;
    }

    public void increaseScore(Question q) {
        totalScore += q.getMaxScore();
    }

    public int getScore() {
        return totalScore;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public Question getNextQuestion() {
        return questions.size() - 1 >= count ? questions.get(count++) : null;
    }

    public void reload() {
        Collections.shuffle(questions);
        totalScore = 0;
        count = 0;
    }
}

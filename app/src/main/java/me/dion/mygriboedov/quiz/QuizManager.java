package me.dion.mygriboedov.quiz;

import android.os.Build;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import me.dion.mygriboedov.util.QuestionConverter;

public class QuizManager implements Serializable {
    private final ArrayList<Question> questions = new ArrayList<>();
    private int totalScore = 0;
    private int count = 0;

    public boolean loadQuestions() {
//        try {
//            URL url = new URL("https://pastebin.com/raw/HBPYUXZx");
//            Scanner scanner = new Scanner(url.openStream());
//            while (scanner.hasNext()) {
//                questions.add(QuestionConverter.convertStringToQuestion(scanner.nextLine()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        questions.add(QuestionConverter.convertStringToQuestion("bebra?:24:bebrochka:frede:2324:bebrochka"));
        questions.add(QuestionConverter.convertStringToQuestion("frede?:12:freede:frede:fred:freede"));
        return questions.size() > 0;
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

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public Question getNextQuestion() {
        System.out.println(questions.size());
        Question q = questions.get(count);
        count++;
        return q;
    }
}

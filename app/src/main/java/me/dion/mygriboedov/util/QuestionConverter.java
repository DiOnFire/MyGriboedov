package me.dion.mygriboedov.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import me.dion.mygriboedov.core.client.quiz.Answer;
import me.dion.mygriboedov.core.client.quiz.Question;

public class QuestionConverter {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertQuestionToString(Question question) {
        String joined = String.join(":", question.getName(), question.getCorrect(), question.getMaxScore() + "");
        joined += String.join(":", question.getVariables());
        return joined;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertAnswerToString(Answer answer) {
        return String.join(":", answer.getQuestion().getName(), answer.getAnswer());
    }
}

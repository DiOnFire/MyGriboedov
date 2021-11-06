package me.dion.mygriboedov.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;

import me.dion.mygriboedov.quiz.Answer;
import me.dion.mygriboedov.quiz.Question;
import me.dion.mygriboedov.quiz.QuizManager;

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

    public static Question convertStringToQuestion(String str) {
        String[] data = str.split(":");
        return new Question(data[0], Integer.parseInt(data[1]), data[2], Arrays.copyOfRange(data, 2, data.length - 1));
    }

    public static Answer convertStringToAnswer(String str) {
        String[] data = str.split(":");
        return new Answer(QuizManager.findQuestionByName(data[0]), data[1]);
    }
}

package me.dion.mygriboedov.util;

import java.util.Arrays;

import me.dion.mygriboedov.quiz.Answer;
import me.dion.mygriboedov.quiz.Question;

public class QuestionConverter {
    public static Question convertStringToQuestion(String str) {
        String[] data = str.split(":");
        return new Question(data[0], Integer.parseInt(data[1]), data[2], Arrays.copyOfRange(data, 2, data.length - 1));
    }

    public static Answer convertStringToAnswer(Question q, String str) {
        return new Answer(q, str);
    }
}

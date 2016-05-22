package com.dangilbert98gmail.pi_thon;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 5/15/2016.
 */
public final class QuestionDatabase {
    private static ArrayList<Question> ORIGIONAL_QUESTIONS = new ArrayList<Question>();
    private static ArrayList<Question> questions = new ArrayList<Question>();

    public static Question getRandomQuestion() {
        if (questions.size() == 0) {
            resetDatabase();
        }
        return getQuestion((int) (Math.random() * ORIGIONAL_QUESTIONS.size()));
    }

    private static Question getQuestion(int n) {
        Question q = questions.remove(n);
        return q;
    }

    public static void resetDatabase() {
        inhabitOriginal();
        for (int i = 0; i < ORIGIONAL_QUESTIONS.size(); i++) {
            questions.add(ORIGIONAL_QUESTIONS.get(i));
        }
    }

    private static void inhabitOriginal() {
        if (questions.size() == 0) {
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_0, R.drawable.question_0_ans_0, R.drawable.question_0_ans_1, R.drawable.question_0_ans_2, R.drawable.question_0_ans_3, R.drawable.question_0_ans_4, 1, R.drawable.consumable));
        }
    }
}

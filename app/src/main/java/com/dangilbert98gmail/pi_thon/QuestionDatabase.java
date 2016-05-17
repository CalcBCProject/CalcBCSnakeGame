package com.dangilbert98gmail.pi_thon;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 5/15/2016.
 */
public final class QuestionDatabase {
    private static final int NUM_QUESTIONS = 40;
    private static ArrayList<Question> ORIGIONAL_QUESTIONS = new ArrayList<Question>();
    private static ArrayList<Question> ORIGIONAL_QUESTIONS_EXAMPLES = new ArrayList<Question>();
    private static ArrayList<Question> questions = new ArrayList<Question>();
    private static ArrayList<Question> questionsExample = new ArrayList<Question>();

    public static Question getRandomQuestion() {
        if (ORIGIONAL_QUESTIONS.size() == 0) {
            inhabitOriginal();
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
        if (ORIGIONAL_QUESTIONS.size() == 0) {
            //fills Orional
        }
    }

    public static void resetDatabaseExample() {
        inhabitOriginalExample();
        for (int i = 0; i < ORIGIONAL_QUESTIONS_EXAMPLES.size(); i++) {
            questionsExample.add(ORIGIONAL_QUESTIONS_EXAMPLES.get(i));
        }
    }

    private static void inhabitOriginalExample() {
        if (ORIGIONAL_QUESTIONS_EXAMPLES.size() == 0) {
            ORIGIONAL_QUESTIONS_EXAMPLES.add(new Question(R.drawable.example_question_1, R.drawable.example_question_1_ans_1, R.drawable.example_question_1_ans_2, R.drawable.example_question_1_ans_3, R.drawable.example_question_1_ans_4, R.drawable.example_question_1_ans_5, 3, -1 /*Temp*/));
            Log.d("A4", "Added " + ORIGIONAL_QUESTIONS_EXAMPLES.toString());
        }
    }

    public static Question getRandomQuestionExample() {
        if (ORIGIONAL_QUESTIONS.size() == 0) {
            Log.d("A3", "Creating examples");
            resetDatabaseExample();
        }
        return getQuestionExample((int) (Math.random() * ORIGIONAL_QUESTIONS_EXAMPLES.size()));
    }

    private static Question getQuestionExample(int n) {
        Log.d("A5", "Getting question at index: " + n + ". Size is: " + questionsExample.size());
        Log.d("A6", "Question at Position " + n + " is " + questionsExample.get(n));
        Question q = questionsExample.remove(n);
        return q;
    }
}

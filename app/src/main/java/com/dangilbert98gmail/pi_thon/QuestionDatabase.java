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
        return getQuestion((int) (Math.random() * questions.size()));
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
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_0, R.drawable.question_0_ans_0, R.drawable.question_0_ans_1, R.drawable.question_0_ans_2, R.drawable.question_0_ans_3, R.drawable.question_0_ans_4, 1, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_1, R.drawable.question_1_ans_0, R.drawable.question_1_ans_1, R.drawable.question_1_ans_2, R.drawable.question_1_ans_3, R.drawable.question_1_ans_4, 4, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_2, R.drawable.question_2_ans_0, R.drawable.question_2_ans_1, R.drawable.question_2_ans_2, R.drawable.question_2_ans_3, R.drawable.question_2_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_3, R.drawable.question_3_ans_0, R.drawable.question_3_ans_1, R.drawable.question_3_ans_2, R.drawable.question_3_ans_3, R.drawable.question_3_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_4, R.drawable.question_4_ans_0, R.drawable.question_4_ans_1, R.drawable.question_4_ans_2, R.drawable.question_4_ans_3, R.drawable.question_4_ans_4, 1, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_5, R.drawable.question_5_ans_0, R.drawable.question_5_ans_1, R.drawable.question_5_ans_2, R.drawable.question_5_ans_3, R.drawable.question_5_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_6, R.drawable.question_6_ans_0, R.drawable.question_6_ans_1, R.drawable.question_6_ans_2, R.drawable.question_6_ans_3, R.drawable.question_6_ans_4, 1, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_7, R.drawable.question_7_ans_0, R.drawable.question_7_ans_1, R.drawable.question_7_ans_2, R.drawable.question_7_ans_3, R.drawable.question_7_ans_4, 0, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_8, R.drawable.question_8_ans_0, R.drawable.question_8_ans_1, R.drawable.question_8_ans_2, R.drawable.question_8_ans_3, R.drawable.question_8_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_9, R.drawable.question_9_ans_0, R.drawable.question_9_ans_1, R.drawable.question_9_ans_2, R.drawable.question_9_ans_3, R.drawable.question_9_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_10, R.drawable.question_10_ans_0, R.drawable.question_10_ans_1, R.drawable.question_10_ans_2, R.drawable.question_10_ans_3, R.drawable.question_10_ans_4, 4, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_11, R.drawable.question_11_ans_0, R.drawable.question_11_ans_1, R.drawable.question_11_ans_2, R.drawable.question_11_ans_3, R.drawable.question_11_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_12, R.drawable.question_12_ans_0, R.drawable.question_12_ans_1, R.drawable.question_12_ans_2, R.drawable.question_12_ans_3, R.drawable.question_12_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_13, R.drawable.question_13_ans_0, R.drawable.question_13_ans_1, R.drawable.question_13_ans_2, R.drawable.question_13_ans_3, R.drawable.question_13_ans_4, 0, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_14, R.drawable.question_14_ans_0, R.drawable.question_14_ans_1, R.drawable.question_14_ans_2, R.drawable.question_14_ans_3, R.drawable.question_14_ans_4, 0, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_15, R.drawable.question_15_ans_0, R.drawable.question_15_ans_1, R.drawable.question_15_ans_2, R.drawable.question_15_ans_3, R.drawable.question_15_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_16, R.drawable.question_16_ans_0, R.drawable.question_16_ans_1, R.drawable.question_16_ans_2, R.drawable.question_16_ans_3, R.drawable.question_16_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_17, R.drawable.question_17_ans_0, R.drawable.question_17_ans_1, R.drawable.question_17_ans_2, R.drawable.question_17_ans_3, R.drawable.question_17_ans_4, 0, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_18, R.drawable.question_18_ans_0, R.drawable.question_18_ans_1, R.drawable.question_18_ans_2, R.drawable.question_18_ans_3, R.drawable.question_18_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_19, R.drawable.question_19_ans_0, R.drawable.question_19_ans_1, R.drawable.question_19_ans_2, R.drawable.question_19_ans_3, R.drawable.question_19_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_20, R.drawable.question_20_ans_0, R.drawable.question_20_ans_1, R.drawable.question_20_ans_2, R.drawable.question_20_ans_3, R.drawable.question_20_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_21, R.drawable.question_21_ans_0, R.drawable.question_21_ans_1, R.drawable.question_21_ans_2, R.drawable.question_21_ans_3, R.drawable.question_21_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_22, R.drawable.question_22_ans_0, R.drawable.question_22_ans_1, R.drawable.question_22_ans_2, R.drawable.question_22_ans_3, R.drawable.question_22_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_23, R.drawable.question_23_ans_0, R.drawable.question_23_ans_1, R.drawable.question_23_ans_2, R.drawable.question_23_ans_3, R.drawable.question_23_ans_4, 0, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_24, R.drawable.question_24_ans_0, R.drawable.question_24_ans_1, R.drawable.question_24_ans_2, R.drawable.question_24_ans_3, R.drawable.question_24_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_25, R.drawable.question_25_ans_0, R.drawable.question_25_ans_1, R.drawable.question_25_ans_2, R.drawable.question_25_ans_3, R.drawable.question_25_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_26, R.drawable.question_26_ans_0, R.drawable.question_26_ans_1, R.drawable.question_26_ans_2, R.drawable.question_26_ans_3, R.drawable.question_26_ans_4, 0, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_27, R.drawable.question_27_ans_0, R.drawable.question_27_ans_1, R.drawable.question_27_ans_2, R.drawable.question_27_ans_3, R.drawable.question_27_ans_4, 4, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_28, R.drawable.question_28_ans_0, R.drawable.question_28_ans_1, R.drawable.question_28_ans_2, R.drawable.question_28_ans_3, R.drawable.question_28_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_29, R.drawable.question_29_ans_0, R.drawable.question_29_ans_1, R.drawable.question_29_ans_2, R.drawable.question_29_ans_3, R.drawable.question_29_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_30, R.drawable.question_30_ans_0, R.drawable.question_30_ans_1, R.drawable.question_30_ans_2, R.drawable.question_30_ans_3, R.drawable.question_30_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_31, R.drawable.question_31_ans_0, R.drawable.question_31_ans_1, R.drawable.question_31_ans_2, R.drawable.question_31_ans_3, R.drawable.question_31_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_32, R.drawable.question_32_ans_0, R.drawable.question_32_ans_1, R.drawable.question_32_ans_2, R.drawable.question_32_ans_3, R.drawable.question_32_ans_4, 2, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_33, R.drawable.question_33_ans_0, R.drawable.question_33_ans_1, R.drawable.question_33_ans_2, R.drawable.question_33_ans_3, R.drawable.question_33_ans_4, 4, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_34, R.drawable.question_34_ans_0, R.drawable.question_34_ans_1, R.drawable.question_34_ans_2, R.drawable.question_34_ans_3, R.drawable.question_34_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_35, R.drawable.question_35_ans_0, R.drawable.question_35_ans_1, R.drawable.question_35_ans_2, R.drawable.question_35_ans_3, R.drawable.question_35_ans_4, 3, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_36, R.drawable.question_36_ans_0, R.drawable.question_36_ans_1, R.drawable.question_36_ans_2, R.drawable.question_36_ans_3, R.drawable.question_36_ans_4, 4, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_37, R.drawable.question_37_ans_0, R.drawable.question_37_ans_1, R.drawable.question_37_ans_2, R.drawable.question_37_ans_3, R.drawable.question_37_ans_4, 4, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_38, R.drawable.question_38_ans_0, R.drawable.question_38_ans_1, R.drawable.question_38_ans_2, R.drawable.question_38_ans_3, R.drawable.question_38_ans_4, 4, R.drawable.consumable));
            ORIGIONAL_QUESTIONS.add(new Question(R.drawable.question_39, R.drawable.question_39_ans_0, R.drawable.question_39_ans_1, R.drawable.question_39_ans_2, R.drawable.question_39_ans_3, R.drawable.question_39_ans_4, 0, R.drawable.consumable));
        }
    }
}

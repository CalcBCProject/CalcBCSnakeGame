package com.dangilbert98gmail.pi_thon;

import java.util.List;

/**
 * Created by Teddy on 5/15/2016.
 */
public class QuestionDatabase {
    private final int NUM_QUESTIONS = 40;

    public static Question getRandomQuestion(){
        return getQuestion((int)(Math.random() * NUM_QUESTIONS));
    }

    public static void resetDatabase(){

    }

    private static Question getQuestion(int n){
        Question q = new Question();

        //set q to random

        return q;
    }
}

package com.dangilbert98gmail.pi_thon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 5/15/2016.
 */
public final class QuestionDatabase {
    private static final int NUM_QUESTIONS = 40;
    private static ArrayList<Question> ORIGIONAL_QUESTIONS = new ArrayList<Question>();
	private static ArrayList<Question> questions = new ArrayList<Question>();

    public static Question getRandomQuestion()
    {
	    if( ORIGIONAL_QUESTIONS.size() == 0 )
	    {
		    inhabitOriginal();
	    }
        return getQuestion((int)(Math.random() * NUM_QUESTIONS));
    }

    public static void resetDatabase()
    {

    }

    private static Question getQuestion(int n){
        Question q = new Question();

        //set q to random

        return q;
    }
	public static void restartDatabase()
	{
		inhabitOriginal();
		for( int i = 0; i < NUM_QUESTIONS; i++ )
		{
			questions.add(ORIGIONAL_QUESTIONS.get(i) );
		}
	}
	private static void inhabitOriginal()
	{
		if( ORIGIONAL_QUESTIONS.size() == 0 )
		{
			//fills Orional
		}
	}
}

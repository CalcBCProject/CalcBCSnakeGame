package com.dangilbert98gmail.pi_thon;

/**
 * Created by Teddy on 5/15/2016.
 */
public class Question
{
    private int questionImageId;
    private String ans0;
    private String ans1;
	private String ans2;
    private String ans3;
    private int correctAns;
    private int solutionId;

	public Question()
	{

	}

    public Question( int questionImageId, String ans0, String ans1, String ans2, String ans3, int correctAns, int solutionId )
    {
        this.questionImageId = questionImageId;
	    this.ans0 = ans0;
	    this.ans1 = ans1;
	    this.ans2 = ans2;
	    this.ans3 = ans3;
	    this.correctAns = correctAns;
	    this.solutionId = solutionId;
    }

	public String getAns0()
	{
		return ans0;
	}

	public String getAns1()
	{
		return ans1;
	}

	public String getAns2()
	{
		return ans2;
	}

	public String getAns3()
	{
		return ans3;
	}

	public int getCorrectAns()
	{
		return correctAns;
	}

	public int getQuestionImageId()
	{
		return questionImageId;
	}

	public int getSolutionId()
	{
		return solutionId;
	}

	@Override
	public boolean equals(Object o)
	{
		if( this == o )
		{
			return true;
		}
		if( ! ( o instanceof Question ) )
		{
			return false;
		}

		Question question = (Question) o;

		if( getQuestionImageId() != question.getQuestionImageId() )
		{
			return false;
		}
		if( getCorrectAns() != question.getCorrectAns() )
		{
			return false;
		}
		if( getSolutionId() != question.getSolutionId() )
		{
			return false;
		}
		if( ! getAns0().equals( question.getAns0() ) )
		{
			return false;
		}
		if( ! getAns1().equals( question.getAns1() ) )
		{
			return false;
		}
		if( ! getAns2().equals( question.getAns2() ) )
		{
			return false;
		}
		return getAns3().equals( question.getAns3() );

	}

	@Override
	public int hashCode()
	{
		int result = getQuestionImageId();
		result = 31 * result + getAns0().hashCode();
		result = 31 * result + getAns1().hashCode();
		result = 31 * result + getAns2().hashCode();
		result = 31 * result + getAns3().hashCode();
		result = 31 * result + getCorrectAns();
		result = 31 * result + getSolutionId();
		return result;
	}

	@Override
	public String toString()
	{
		return "Question #" + getQuestionImageId();
	}
}

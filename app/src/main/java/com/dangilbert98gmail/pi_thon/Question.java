package com.dangilbert98gmail.pi_thon;

/**
 * Created by Teddy on 5/15/2016.
 */
public class Question {
    private int questionImageId;
    private int ans0;
    private int ans1;
	private int ans2;
    private int ans3;
	private int ans4;
    private int correctAns;
    private int solutionId;

    public Question() {

    }
    public Question(int questionImageId, int ans0, int ans1, int ans2, int ans3, int ans4, int correctAns, int solutionId) {
        this.questionImageId = questionImageId;
        this.ans0 = ans0;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans3;
        this.correctAns = correctAns;
        this.solutionId = solutionId;
    }

    public int getAns0() {
        return ans0;
    }

    public int getAns1() {
        return ans1;
    }

    public int getAns2() {
        return ans2;
    }

    public int getAns3() {
        return ans3;
    }

    public int getAns4() {
        return ans4;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public int getQuestionImageId() {
        return questionImageId;
    }

    public int getSolutionId() {
        return solutionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }

        Question question = (Question) o;

        if (getQuestionImageId() != question.getQuestionImageId()) {
            return false;
        }
        if (getCorrectAns() != question.getCorrectAns()) {
            return false;
        }
        if (getSolutionId() != question.getSolutionId()) {
            return false;
        }
        if (!("" + getAns0()).equals("" + question.getAns0())) {
            return false;
        }
        if (!("" + getAns1()).equals("" + question.getAns1())) {
            return false;
        }
        if (!("" + getAns2()).equals("" + question.getAns2())) {
            return false;
        }
        return ("" + getAns3()).equals("" + question.getAns3());

    }

    @Override
    public int hashCode() {
        int result = getQuestionImageId();
        result = 31 * result + ("" + getAns0()).hashCode();
        result = 31 * result + ("" + getAns1()).hashCode();
        result = 31 * result + ("" + getAns2()).hashCode();
        result = 31 * result + ("" + getAns3()).hashCode();
        result = 31 * result + ("" + getAns4()).hashCode();
        result = 31 * result + getCorrectAns();
        result = 31 * result + getSolutionId();
        return result;
    }

    @Override
    public String toString() {
        return "Question #" + getQuestionImageId();
    }
}

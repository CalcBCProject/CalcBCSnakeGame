package com.dangilbert98gmail.pi_thon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import java.util.List;

public class PlayScreen extends AppCompatActivity implements ControlSection.ControlSectionListener, PauseSection.PauseSectionListener, GameSection.GameSectionListener, QuestionSection.QuestionSectionListener, SolutionSection.SolutionSectionListener, EndGameSection.EndGameSectionListener{
    private static ControlSection controlFrag;
    private static GameSection gameFrag;
    private static PauseSection pauseFrag;
    private static QuestionSection questionFrag;
    private static SolutionSection solutionFrag;
    private static EndGameSection endGameFrag;
    private static Question q;
    private boolean isPaused;
    private WindowManager wm;
    protected static boolean questionsEnabled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        setVariables();
    }

    private void setVariables() {
        controlFrag = (ControlSection) (getSupportFragmentManager().findFragmentById(R.id.ControlSection));
        gameFrag = (GameSection) (getSupportFragmentManager().findFragmentById(R.id.GameSection));
        wm = (WindowManager) (getSystemService(Context.WINDOW_SERVICE));
        questionsEnabled = true;
    }

    public void setSnakeDirection(SnakeDirection d) {
        gameFrag.setDirection(d);
    }

    public void disableQuestions() {
        questionsEnabled = false;
    }

    public void enableQuestions() {
        questionsEnabled = true;
    }

    public boolean areQuestionsEnabled() {
        return questionsEnabled;
    }

    public void pauseGame(PauseType p) {
        controlFrag.setParentInfo(findViewById(R.id.PlayScreen));
        controlFrag.disableButtons();
        gameFrag.pause();

        isPaused = true;

        if (p == PauseType.PAUSE) {
            displayPauseScreen();
        } if ( p == PauseType.QUESTION ){
            displayQuestionScreen();
        } if ( p == PauseType.DEATH){
            displayDeathScreen();
        }
    }

    private void displayPauseScreen() {
        FragmentManager fm = getSupportFragmentManager();
        pauseFrag = new PauseSection();
        pauseFrag.show(fm, "dialog_fragment_pause");
    }

    public void displayQuestionScreen() {
        controlFrag.disableButtons();
        FragmentManager fm = getSupportFragmentManager();
        questionFrag = new QuestionSection();
        questionFrag.show(fm, "dialog_fragment_questions");
    }

    public Question selectQuestion() {
        q = QuestionDatabase.getRandomQuestion();
        if (q != null) {
            return q;
        } else {
            QuestionDatabase.resetDatabase();
            return selectQuestion();
        }
    }

    public void resumeGame() {
        controlFrag.enableButtons();
        gameFrag.resume();

        isPaused = false;
    }

    public void buildSolutionSection(){
        FragmentManager fm = getSupportFragmentManager();
        solutionFrag = new SolutionSection();
        solutionFrag.show(fm, "dialog_fragment_solution");
    }

    public void restartGame() {
        gameFrag.restart();
    }

    public int getSolutionID(){
        return q.getSolutionId();
    }

    public int getQuestionID(){ return q.getQuestionImageId(); }

    public void goToMainMenu() {
        Intent i = new Intent( this, MenuScreen.class );
        startActivity( i );
		finish();
    }
    public void modifyTail( boolean add )
    {
	    gameFrag.addTail( add );
    }

    public boolean isPaused(){
        return isPaused;
    }

    public void displayDeathScreen(){
        FragmentManager fm = getSupportFragmentManager();
        endGameFrag = new EndGameSection();
        endGameFrag.show(fm, "dialog_fragment_solution");
    }

    public int getScore(){
        return gameFrag.getScore();
    }
}

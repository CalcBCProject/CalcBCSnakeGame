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

public class PlayScreen extends AppCompatActivity implements ControlSection.ControlSectionListener, PauseSection.PauseSectionListener, GameSection.GameSectionListener, QuestionSection.QuestionSectionListener, SolutionSection.SolutionSectionListener{
    private static ControlSection controlFrag;
    private static GameSection gameFrag;
    private static PauseSection pauseFrag;
    private static QuestionSection questionFrag;
    private static SolutionSection solutionFrag;
    private static Question q;
    private WindowManager wm;
    protected static boolean questionsEnabled = true;


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

        if (p == PauseType.PAUSE) {
            displayPauseScreen();
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
        q = QuestionDatabase.getRandomQuestionExample();
        if (q != null) {
            return q;
        } else {
            QuestionDatabase.resetDatabaseExample();
            return selectQuestion();
        }
    }

    public void resumeGame() {
        controlFrag.enableButtons();
        gameFrag.resume();
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

    public void goToMainMenu() {
        Intent i = new Intent( PlayScreen.this, MenuScreen.class );
        startActivity( i );
		finish();
    }
}

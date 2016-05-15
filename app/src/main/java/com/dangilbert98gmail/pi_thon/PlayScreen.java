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

public class PlayScreen extends AppCompatActivity implements ControlSection.ControlSectionListener, PauseSection.PauseSectionListener
{
    private static ControlSection controlFrag;
    private static GameSection gameFrag;
    private static PauseSection pauseFrag;
    private WindowManager wm;
    protected static boolean questionsEnabled = true;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_play_screen );

        setVariables();
	}

    private void setVariables(){
        controlFrag = (ControlSection)(getSupportFragmentManager().findFragmentById(R.id.ControlSection));
        gameFrag = (GameSection) (getSupportFragmentManager().findFragmentById(R.id.GameSection));
        wm = (WindowManager) (getSystemService(Context.WINDOW_SERVICE));
    }

    public void setSnakeDirection(SnakeDirection d){
        gameFrag.setDirection(d);
    }

    public void disableQuestions(){
        questionsEnabled = false;
    }

    public void enableQuestions(){
        questionsEnabled = true;
    }

    public boolean questionsEnabled(){
        return questionsEnabled;
    }

    public void pauseGame(){
        controlFrag.setParentInfo(findViewById(R.id.PlayScreen));
        controlFrag.disableButtons();
        gameFrag.pause();

        FragmentManager fm = getSupportFragmentManager();
        pauseFrag = new PauseSection();
        pauseFrag.show(fm, "fragment_pause_screen");
    }

    public void resumeGame(){
        controlFrag.enableButtons();
        gameFrag.resume();
    }
    public void restartGame(){
        
    }
    public void goToMainMenu(){

    }
}

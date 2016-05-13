package com.dangilbert98gmail.pi_thon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Enumeration;

public class PlayScreen extends AppCompatActivity
{
	private static PlayScreen instance = null;
    private static ControlSection controlFrag;
    private static GameSection gameFrag;

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
        instance = this;
    }

	static PlayScreen getInstance(){
		return instance;
	}

    public void setSnakeDirection(SnakeDirection d){
        gameFrag.setDirection(d);
    }

}

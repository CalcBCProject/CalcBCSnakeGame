package com.dangilbert98gmail.pi_thon;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuScreen extends AppCompatActivity
{

	private Button startGame, about;
	private ImageButton help;
	private Handler handler;
	private Runnable runnable;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_menu );

		startGame = (Button) findViewById( R.id.startButton );
		startGame.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent( MenuScreen.this, PlayScreen.class );

				startActivity( i );
				finish();
			}
		} );

		about = (Button) findViewById( R.id.aboutButton );
		about.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//about stuff
			}
		} );

		help = (ImageButton) findViewById( R.id.helpButton );
		help.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				/*
				handler = new Handler(  );
				runnable = new Runnable()
				{
					@Override
					public void run()
					{
						setRandomOrientation();
						handler.postDelayed( runnable, 250 );
					}
				};
				handler.postDelayed( runnable, 1000 ); */ }
		} );
	}
	public void setRandomOrientation()
	{
		int r = (int)(Math.random() * 4 );
		int o;
		switch( r )
		{
			case 0: o = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE; break;
			case 1: o = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE; break;
			case 2: o = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT; break;
			default: o = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
		}
		setRequestedOrientation( o );
	}

}

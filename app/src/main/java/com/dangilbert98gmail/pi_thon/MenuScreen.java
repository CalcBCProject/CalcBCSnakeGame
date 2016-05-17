package com.dangilbert98gmail.pi_thon;

import android.content.Intent;
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
				//help stuff
			}
		} );
	}

}

package com.dangilbert98gmail.pi_thon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HelpScreen extends AppCompatActivity
{

	private Button close;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_help_screen );

		close = (Button)findViewById( R.id.helpClose );
		close.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent( HelpScreen.this, MenuScreen.class );

				startActivity( i );
				finish();
			}
		} );
	}


}

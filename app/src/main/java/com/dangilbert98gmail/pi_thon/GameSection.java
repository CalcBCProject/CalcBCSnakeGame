package com.dangilbert98gmail.pi_thon;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class GameSection extends Fragment
{
	private SnakeDirection direction;
	private Handler handler = new Handler();
	private Runnable runnable;
	private GridView grid;
	private final int GRID_WIDTH = 9;
	private final int GRID_HEIGHT = 11;
	private final int tail = R.drawable.red_tile;
	private final int head = R.drawable.head;
	private final int empty = R.drawable.background;
	private final int consumable = R.drawable.red_tile;
	private int currLoc;
	private int [] images;
	private final int delay = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	    View v = inflater.inflate(R.layout.fragment_game_section, container, false);

	    grid = (GridView) v.findViewById(R.id.gameGrid);

		initialize();

	    runnable = new Runnable() {
		    @Override
		    public void run()
		    {
			    move( direction );
			    handler.postDelayed(this, delay);
		    }
	    };
	    handler.postDelayed( runnable, delay );

        return v;
    }

	public boolean setDirection( SnakeDirection d )
	{
		if( d == SnakeDirection.DOWN )
		{
			if( direction == SnakeDirection.DOWN )
			{
				return false;
			}
			else
			{
				direction = SnakeDirection.DOWN;
				return true;
			}
		}
		if( d == SnakeDirection.UP )
		{
			if( direction == SnakeDirection.UP )
			{
				return false;
			}
			else
			{
				direction = SnakeDirection.UP;
				return true;
			}
		}
		if( d == SnakeDirection.LEFT )
		{
			if( direction == SnakeDirection.LEFT )
			{
				return false;
			}
			else
			{
				direction = SnakeDirection.LEFT;
				return true;
			}
		}
		if( d == SnakeDirection.RIGHT )
		{
			if( direction == SnakeDirection.RIGHT )
			{
				return false;
			}
			else
			{
				direction = SnakeDirection.RIGHT;
				return true;
			}
		}
		return false;
	}
	public void move( SnakeDirection d )
	{
		//Log.d("direction", d.toString() + "    " + getCurrLoc() );
		setImages( getCurrLoc(), empty );
		switch( d )
		{
			    case UP:    if( (getCurrLoc() - GRID_WIDTH) < 0 )  die( d );
				            else currLoc = getCurrLoc() - GRID_WIDTH; break;
			    case DOWN:  if( (getCurrLoc() + GRID_WIDTH) >= (GRID_HEIGHT * GRID_WIDTH) )  die(d);
			                else currLoc = getCurrLoc() + GRID_WIDTH; break;
			    case RIGHT: if( (getCurrLoc() + 1) % GRID_WIDTH == 0 )  die(d);
			                else currLoc = getCurrLoc() + 1; break;
			    case LEFT:  if( getCurrLoc() % GRID_WIDTH == 0 )  die(d);
			                else currLoc = getCurrLoc() - 1; break;
		}
		setImages( currLoc, head );
		grid.setAdapter( new TileAdapter( getGameContext(), images ) );
	}
	public void die(SnakeDirection d)
	{
		//Log.d("direction", "died--------------"+d.toString() + "    " + getCurrLoc() );
		//may death consume us all #trump2016
	}
	public int [] getImages()
	{
		return images;
	}
	public void setImages( int pos, int val )
	{
		images[ pos ] = val;
	}
	public void pause()
	{
		handler.removeCallbacks( runnable );
	}
	public void resume()
	{
		handler.postDelayed( runnable, delay );
	}
	public void restart()
	{
		initialize();
		pause();
	}
	public void initialize()
	{
		direction = SnakeDirection.UP;

		images = new int [GRID_WIDTH * GRID_HEIGHT];
		for( int r = 0; r < GRID_HEIGHT; r++ )
		{
			for( int c = 0; c < GRID_WIDTH; c++ )
			{
				images[ (r*GRID_WIDTH) + c ] = empty;
			}
		}
		currLoc = ((GRID_HEIGHT/2) * GRID_WIDTH) + (GRID_WIDTH/2);
		images[ currLoc ] = head;
		grid.setAdapter( new TileAdapter( this.getContext(), images ) );


	}
	public int getCurrLoc()
	{
		return currLoc;
	}
	public Context getGameContext()
	{
		return this.getContext();
	}

}

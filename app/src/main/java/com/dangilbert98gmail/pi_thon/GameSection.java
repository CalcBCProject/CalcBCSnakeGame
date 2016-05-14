package com.dangilbert98gmail.pi_thon;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
	private final int GRID_WIDTH = 8;
	private final int GRID_HEIGHT = 10;
	private final int tail = R.drawable.red_tile;
	private final int head = R.drawable.red_tile;
	private final int empty = R.drawable.red_tile;
	private final int consumable = R.drawable.red_tile;
	private int currLoc;
	private int [] images;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	    View v = inflater.inflate(R.layout.fragment_game_section, container, false);

	    direction = SnakeDirection.UP;

	    images = new int [GRID_WIDTH * GRID_HEIGHT];
	    for( int x = 0; x < GRID_WIDTH; x++ )
	    {
		    for( int y = 0; y < GRID_HEIGHT; y++ )
		    {
			    images[ (x*GRID_HEIGHT) + y ] = empty;
		    }
	    }
	    images[ ( (GRID_WIDTH/2) * GRID_HEIGHT) + (GRID_HEIGHT/2) ] = head;
	    grid = (GridView) v.findViewById(R.id.gameGrid);
	    grid.setAdapter( new TileAdapter( this.getContext(), images ) );

	    runnable = new Runnable() {
		    @Override
		    public void run()
		    {
			    //move
			    switch( direction )
			    {
				    case UP: setImages( getCurrLoc() - 1, head );;
				    case DOWN: setImages( getCurrLoc() + 1, head );
				    case RIGHT: setImages( getCurrLoc() + GRID_HEIGHT, head );
				    case LEFT: setImages( getCurrLoc() - GRID_HEIGHT, head );
			    }
			    grid.setAdapter( new TileAdapter( getGameContext(), images ) );
			    handler.postDelayed(this, 100);
		    }
	    };

	    handler.postDelayed(runnable, 100);



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
	public int [] getImages()
	{
		return images;
	}
	public void setImages( int pos, int val )
	{
		images[ pos ] = val;
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

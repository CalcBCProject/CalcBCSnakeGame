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
	private GameSectionListener playActivity;
	private static QuestionDatabase questionDB;
	private final int GRID_WIDTH = 9;
	private final int GRID_HEIGHT = 11;
	private final int TAIL = R.drawable.red_tile;
	private final int HEAD = R.drawable.head;
	private final int EMPTY = R.drawable.background;
	private final int CONSUMABLE = R.drawable.consumable;
	private int currLoc;
	private int[] images;
	private int delay;
	private Queue queue;
	private int maxQueueSize;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate( R.layout.fragment_game_section, container, false );

		grid = (GridView) v.findViewById( R.id.gameGrid );

		questionDB = new QuestionDatabase();

		initialize();

		runnable = new Runnable()
		{
			@Override
			public void run()
			{
				move( direction );
				handler.postDelayed( this, delay );
			}
		};
		handler.postDelayed( runnable, delay );

		return v;
	}

	@Override
	public void onAttach(Context context){
		super.onAttach(context);
		try{
			playActivity = (GameSectionListener)context;
		} catch (Exception e){
			throw new ClassCastException(playActivity.toString());
		}
	}

	public boolean setDirection(SnakeDirection d)
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

	public void move(SnakeDirection d)
	{
		//Log.d("direction", d.toString() + "    " + getCurrLoc() );
		setImages( getCurrLoc(), EMPTY );
		int prevLoc = getCurrLoc();
		boolean shouldDie = false;
		switch( d )
		{
			case UP:
				if( ( getCurrLoc() - GRID_WIDTH ) < 0 )
				{
					shouldDie = true;
				}
				else
				{
					currLoc = getCurrLoc() - GRID_WIDTH;
				}
				break;
			case DOWN:
				if( ( getCurrLoc() + GRID_WIDTH ) >= ( GRID_HEIGHT * GRID_WIDTH ) )
				{
					shouldDie = true;
				}
				else
				{
					currLoc = getCurrLoc() + GRID_WIDTH;
				}
				break;
			case RIGHT:
				if( ( getCurrLoc() + 1 ) % GRID_WIDTH == 0 )
				{
					shouldDie = true;
				}
				else
				{
					currLoc = getCurrLoc() + 1;
				}
				break;
			case LEFT:
				if( getCurrLoc() % GRID_WIDTH == 0 )
				{
					shouldDie = true;
				}
				else
				{
					currLoc = getCurrLoc() - 1;
				}
				break;
		}
		if( shouldDie )
		{
			die();
		}
		else
		{
			if( images[currLoc] == CONSUMABLE )
			{
				eatConsumable();
				spawnConsumable();
			}
			setImages( currLoc, HEAD );
			setImages( prevLoc, TAIL );
			queue.enqueue( prevLoc );
			if( queue.size() > maxQueueSize )
			{
				setImages( (int)queue.dequeue(), EMPTY );
			}
			grid.setAdapter( new TileAdapter( getGameContext(), images ) );
		}
	}

	public void eatConsumable(){
		playActivity.displayQuestion(QuestionDatabase.getRandomQuestion());

		maxQueueSize++;
	}

	public void die()
	{
		//Log.d("direction", "died--------------"+d.toString() + "    " + getCurrLoc() );
		//may death consume us all #trump2016
	}

	public int[] getImages()
	{
		return images;
	}

	public void setImages(int pos, int val)
	{
		images[pos] = val;
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
	public boolean spawnConsumable()
	{
		boolean full = true;
		for( int i = 0; i < GRID_WIDTH * GRID_HEIGHT; i++ )
		{
			if( images[i] == EMPTY )
			{
				full = false;
			}
		}
		if( full )
		{
			return false;
		}
		boolean spotFound = false;
		while( !spotFound )
		{
			int r = (int)(Math.random() * (GRID_WIDTH*GRID_HEIGHT));
			if( images[r] == EMPTY )
			{
				spotFound = true;
				images[r] = CONSUMABLE;
				delay = (int)( Math.pow( Math.E, 6.0 - ( maxQueueSize / 2.0 ) ) + 250 );
			}
		}
		return true;
	}
	public void initialize()
	{
		queue = new Queue();
		maxQueueSize = 0;

		direction = SnakeDirection.UP;

		images = new int[GRID_WIDTH * GRID_HEIGHT];
		for( int r = 0; r < GRID_HEIGHT; r++ )
		{
			for( int c = 0; c < GRID_WIDTH; c++ )
			{
				images[( r * GRID_WIDTH ) + c] = EMPTY;
			}
		}
		currLoc = ( ( GRID_HEIGHT / 2 ) * GRID_WIDTH ) + ( GRID_WIDTH / 2 );
		images[currLoc] = HEAD;
		spawnConsumable();
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
	interface GameSectionListener {
		public void displayQuestion(Question q);
	}

}

package com.dangilbert98gmail.pi_thon;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GameSection extends Fragment
{
	private SnakeDirection direction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        direction = SnakeDirection.UP;

        return inflater.inflate(R.layout.fragment_game_section, container, false);
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
}

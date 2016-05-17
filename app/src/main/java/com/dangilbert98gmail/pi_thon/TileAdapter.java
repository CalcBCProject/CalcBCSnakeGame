package com.dangilbert98gmail.pi_thon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Samsung on 5/14/2016.
 */
public class TileAdapter extends BaseAdapter
{
	private Context mContext;
	// Keep all Images in array
	private int [] mTiles;
	public TileAdapter( Context c, int [] t )
	{
		mContext=c;
		mTiles =  t;
	}
	public int getCount()
	{
		return mTiles.length;
	}
	public Object getItem(int position)
	{
		return null;
	}
	public long getItemId(int position)
	{
		return 0;
	}
	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent)
	{
		pl.droidsonroids.gif.GifTextView imageView;

		if (convertView == null) {
			imageView = new pl.droidsonroids.gif.GifTextView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setPadding(8, 8, 8, 8);
		}
		else
		{
			imageView = (pl.droidsonroids.gif.GifTextView) convertView;
		}
		imageView.setBackgroundResource(mTiles[position]);
		return imageView;
	}
}

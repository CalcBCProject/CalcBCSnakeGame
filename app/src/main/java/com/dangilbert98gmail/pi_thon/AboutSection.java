package com.dangilbert98gmail.pi_thon;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Samsung on 5/19/2016.
 */
public class AboutSection extends DialogFragment
{
	private View myView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView = inflater.inflate(R.layout.dialog_fragment_about, container, false);
		getDialog().setCanceledOnTouchOutside( false );

		getDialog().getWindow().setLayout(3000, 3000);
		return myView;
	}
}

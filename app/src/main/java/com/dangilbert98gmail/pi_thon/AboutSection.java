package com.dangilbert98gmail.pi_thon;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * Created by Samsung on 5/19/2016.
 */
public class AboutSection extends DialogFragment
{
	private View myView;
	private ImageButton close;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView = inflater.inflate(R.layout.dialog_fragment_about, container, false);
		getDialog().setCanceledOnTouchOutside( false );

		close = (ImageButton) myView.findViewById( R.id.ResumeButtonAbout);
		close.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dismiss();
			}
		} );
		return myView;
	}
	@Override
	public void onStart(){
		super.onStart();
		Dialog dialog = getDialog();
		if (dialog != null) {
			dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new Dialog(getActivity(), getTheme()){
			@Override
			public void onBackPressed()
			{
				dismiss();
			}
		};
	}
}

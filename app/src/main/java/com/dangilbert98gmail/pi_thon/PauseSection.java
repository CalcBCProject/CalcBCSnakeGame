package com.dangilbert98gmail.pi_thon;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

/**
 * Created by user on 5/14/2016.
 */
public class PauseSection extends DialogFragment{
    private ImageButton resumeButton;
    private CheckBox toggleQuestions;
    private PauseSectionListener playActivity;

    private View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.dialog_fragment_pause, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        return myView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            playActivity = (PauseSectionListener)context;
        } catch (Exception e){
            throw new ClassCastException(playActivity.toString());
        }
    }

    public class ImageButtonListeners implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            resumeGame();
        }
    }

    public class CheckBoxListeners implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.d("A1", "Clicked!" + 2);
        }
    }

    private void setButtons(){
        resumeButton = (ImageButton) (myView.findViewById(R.id.ResumeButton));
        toggleQuestions = (CheckBox) (myView.findViewById(R.id.CheckBox));
    }

    private void setButtonListeners(){
        resumeButton.setOnClickListener(new ImageButtonListeners());
        toggleQuestions.setOnClickListener(new CheckBoxListeners());
    }

    public interface PauseSectionListener{
        public void resumeGame();
    }

    public void resumeGame(){
        playActivity.resumeGame();
    }
}

package com.dangilbert98gmail.pi_thon;



import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

        setButtons();
        setButtonListeners();

        getDialog().getWindow().setLayout(3000, 3000);
        return myView;
    }

    @Override
    public void onStart(){
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()){
            @Override
            public void onBackPressed() {
                resumeGame();
                super.onBackPressed();
            }
        };
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
            dismiss();
        }
    }

    public class CheckBoxListeners implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (toggleQuestions.isChecked()){
                playActivity.disableQuestions();
            } else{
                playActivity.enableQuestions();
            }
        }
    }

    private void setButtons(){
        resumeButton = (ImageButton) (myView.findViewById(R.id.ResumeButton));
        toggleQuestions = (CheckBox) (myView.findViewById(R.id.CheckBox));
        if(!playActivity.questionsEnabled()){
            toggleQuestions.setChecked(true);
        }else{
            toggleQuestions.setChecked(false);
        }
    }

    private void setButtonListeners(){
        resumeButton.setOnClickListener(new ImageButtonListeners());
        toggleQuestions.setOnClickListener(new CheckBoxListeners());
    }

    public interface PauseSectionListener{
        public void resumeGame();
        public void enableQuestions();
        public void disableQuestions();
        public boolean questionsEnabled();
    }

    public void resumeGame(){
        playActivity.resumeGame();
    }
}

package com.dangilbert98gmail.pi_thon;



import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by user on 5/14/2016.
 */
public class PauseSection extends DialogFragment{
    private ImageButton resumeButton;
    private CheckBox toggleQuestions;
    private Button menuButton, restartButton;
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

    public class ButtonListeners implements View.OnClickListener{
        private int buttonID;

        public ButtonListeners(int id){
            buttonID = id;
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            TextView t = new TextView(getContext());

            if(buttonID == R.id.MainMenu){
                t.setText("Return to Main Menu?");
            }else if (buttonID == R.id.RestartGame){
                t.setText("Start a new game?");
            }
            t.setTypeface(Typeface.DEFAULT_BOLD);
            t.setTextSize(25);
            t.setGravity(Gravity.CENTER_HORIZONTAL);
            t.setPadding(0,50,0,0);
            builder.setView(t).setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener);

            AlertDialog alert = builder.create();
            alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            alert.show();
        }

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        if(buttonID == R.id.RestartGame){
                            playActivity.restartGame();
                            resumeButton.performClick();
                        } else if (buttonID == R.id.MainMenu){
                            playActivity.goToMainMenu();
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
    }


    private void setButtons(){
        resumeButton = (ImageButton) (myView.findViewById(R.id.ResumeButton));
        toggleQuestions = (CheckBox) (myView.findViewById(R.id.CheckBox));
        menuButton = (Button) (myView.findViewById(R.id.MainMenu));
        restartButton = (Button) (myView.findViewById(R.id.RestartGame));

        if(!playActivity.questionsEnabled()){
            toggleQuestions.setChecked(true);
        }else{
            toggleQuestions.setChecked(false);
        }
    }

    private void setButtonListeners(){
        resumeButton.setOnClickListener(new ImageButtonListeners());
        toggleQuestions.setOnClickListener(new CheckBoxListeners());
        menuButton.setOnClickListener(new ButtonListeners(menuButton.getId()));
        restartButton.setOnClickListener(new ButtonListeners(restartButton.getId()));
    }

    public interface PauseSectionListener{
        public void resumeGame();
        public void enableQuestions();
        public void disableQuestions();
        public void restartGame();
        public void goToMainMenu();
        public boolean questionsEnabled();
    }

    public void resumeGame(){
        playActivity.resumeGame();
    }
}

package com.dangilbert98gmail.pi_thon;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Teddy on 5/22/2016.
 */
public class EndGameSection extends DialogFragment {

    private EndGameSectionListener playActivity;
    private View myView;
    private Button menuButton, restartButton;
    private TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.dialog_fragment_end, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        setButtons();
        setButtonListeners();

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
                playActivity.goToMainMenu();
                super.onBackPressed();
            }
        };
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            playActivity = (EndGameSectionListener)context;
        } catch (Exception e){
            throw new ClassCastException(playActivity.toString());
        }
    }

    public void setButtons(){
        menuButton = (Button) (myView.findViewById(R.id.Menu));
        restartButton = (Button)(myView.findViewById(R.id.Restart));
        text = (TextView) (myView.findViewById(R.id.ScoreText));
    }

    public void setButtonListeners(){
        menuButton.setOnClickListener(new ButtonListener(1));
        restartButton.setOnClickListener(new ButtonListener(2));
        text.setTypeface(Typeface.DEFAULT_BOLD);
        text.setGravity(Gravity.CENTER);
        if(playActivity.getScore() == 0){
            text.setText("You scored " + playActivity.getScore() + " points! \nBruh... that's pathetic...");
        } else {
            text.setText("You scored " + playActivity.getScore() + " point" + (playActivity.getScore() > 1 ? "s" : "")  + "! \nWell played!");
        }
    }

    public class ButtonListener implements View.OnClickListener{
        int type;

        public ButtonListener(int type){
            this.type = type;
        }

        @Override
        public void onClick(View v) {
            if(type == 1) {
                playActivity.goToMainMenu();
            } else if (type == 2){
                playActivity.restartGame();
                playActivity.resumeGame();
            }
            dismiss();
        }
    }

    public interface EndGameSectionListener{
        public void goToMainMenu();
        public void restartGame();
        public int getScore();
        public void resumeGame();
    }
}

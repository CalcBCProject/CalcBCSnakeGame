package com.dangilbert98gmail.pi_thon;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Teddy on 5/15/2016.
 */
public class QuestionSection extends DialogFragment {
    private QuestionSectionListener playActivity;
    private ImageView pic;
    private RadioGroup choiceGroup;
    private RadioButton choice1, choice2, choice3, choice4;
    private Button submitButton;
    private View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.dialog_fragment_questions, container, false);
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
                super.onBackPressed();
                resumeGame();
            }
        };
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            playActivity = (QuestionSectionListener)context;
        } catch (Exception e){
            throw new ClassCastException(playActivity.toString());
        }
    }

    public void setup(Question q){

    }

    public void resumeGame(){
        playActivity.resumeGame();
    }

    public void setButtons(){
        choiceGroup = (RadioGroup) (myView.findViewById(R.id.AnswerChoices));
        choice1 = (RadioButton)(myView.findViewById(R.id.Choice1));
        choice2 = (RadioButton)(myView.findViewById(R.id.Choice2));
        choice3 = (RadioButton)(myView.findViewById(R.id.Choice3));
        choice4 = (RadioButton)(myView.findViewById(R.id.Choice4));
        submitButton = (Button) (myView.findViewById(R.id.SubmitAnswerButton));
        pic = (ImageView)(myView.findViewById(R.id.Question));
    }

    public void setButtonListeners(){

    }
    interface QuestionSectionListener{
        public void resumeGame();
    }
}

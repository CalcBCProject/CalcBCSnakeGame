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
    private RadioButton choice0, choice1, choice2, choice3, choice4;
    private Button submitButton;
    private View myView;
    private int selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.dialog_fragment_questions, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        setButtons();
        setButtonListeners();

        setup(playActivity.selectQuestion());

        getDialog().getWindow().setLayout(3000, 3000);
        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                resumeGame();
                super.onBackPressed();
            }
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            playActivity = (QuestionSectionListener) context;
        } catch (Exception e) {
            throw new ClassCastException(playActivity.toString());
        }
    }

    public void setup(Question q) {
        pic.setImageDrawable(getContext().getResources().getDrawable(q.getQuestionImageId()));
        choice0.setCompoundDrawablesWithIntrinsicBounds(q.getAns0(), 0, 0, 0);
        choice1.setCompoundDrawablesWithIntrinsicBounds(q.getAns1(), 0, 0, 0);
        choice2.setCompoundDrawablesWithIntrinsicBounds(q.getAns2(), 0, 0, 0);
        choice3.setCompoundDrawablesWithIntrinsicBounds(q.getAns3(), 0, 0, 0);
        choice4.setCompoundDrawablesWithIntrinsicBounds(q.getAns4(), 0, 0, 0);
        switch (q.getCorrectAns()) {
            case 0:
                submitButton.setOnClickListener(new ButtonListener(choice0));
                break;
            case 1:
                submitButton.setOnClickListener(new ButtonListener(choice1));
                break;
            case 2:
                submitButton.setOnClickListener(new ButtonListener(choice2));
                break;
            case 3:
                submitButton.setOnClickListener(new ButtonListener(choice3));
                break;
            case 4:
                submitButton.setOnClickListener(new ButtonListener(choice4));
                break;
        }
    }

    public void resumeGame() {
        playActivity.resumeGame();
    }

    public void setButtons() {
        Log.d("A21", "Setting buttons");
        choiceGroup = (RadioGroup) (myView.findViewById(R.id.AnswerChoices));
        choice0 = (RadioButton) (myView.findViewById(R.id.Choice1));
        choice1 = (RadioButton) (myView.findViewById(R.id.Choice2));
        choice2 = (RadioButton) (myView.findViewById(R.id.Choice3));
        choice3 = (RadioButton) (myView.findViewById(R.id.Choice4));
        choice4 = (RadioButton) (myView.findViewById(R.id.Choice5));
        submitButton = (Button) (myView.findViewById(R.id.SubmitAnswerButton));
        pic = (ImageView) (myView.findViewById(R.id.Question));
    }

    public void setButtonListeners() {
        choice0.setOnClickListener(new RadioButtonListener(1));
        choice1.setOnClickListener(new RadioButtonListener(2));
        choice2.setOnClickListener(new RadioButtonListener(3));
        choice3.setOnClickListener(new RadioButtonListener(4));
        choice4.setOnClickListener(new RadioButtonListener(5));
    }

    private class ButtonListener implements View.OnClickListener {
        RadioButton correctRadioButton;

        public ButtonListener(RadioButton r) {
            correctRadioButton = r;
        }

        @Override
        public void onClick(View v) {
            if (correctRadioButton == myView.findViewById(choiceGroup.getCheckedRadioButtonId())) {
                Log.d("A1", "Correct!");
            } else {
                Log.d("A2", "Incorrect!");
            }
        }
    }

    private class RadioButtonListener implements View.OnClickListener {
        private int s;

        public RadioButtonListener(int choiceNum) {
            s = choiceNum;
        }

        @Override
        public void onClick(View v) {
            selected = s;
        }
    }

    interface QuestionSectionListener {
        public void resumeGame();
        public Question selectQuestion();
    }
}

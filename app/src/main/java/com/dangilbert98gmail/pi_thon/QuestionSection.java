package com.dangilbert98gmail.pi_thon;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Teddy on 5/15/2016.
 */
public class QuestionSection extends DialogFragment implements SolutionSection.SolutionSectionListener {
    private QuestionSectionListener playActivity;
    private DialogFragment solutionFrag;
    private Question myQuestion;
    private ImageView pic;
    private RadioGroup choiceGroup;
    private RadioButton choice0, choice1, choice2, choice3, choice4;
    private Button submitButton;
    private View myView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.dialog_fragment_questions, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        setButtons();

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
        myQuestion = q;
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
        choiceGroup = (RadioGroup) (myView.findViewById(R.id.AnswerChoices));
        choice0 = (RadioButton) (myView.findViewById(R.id.Choice1));
        choice1 = (RadioButton) (myView.findViewById(R.id.Choice2));
        choice2 = (RadioButton) (myView.findViewById(R.id.Choice3));
        choice3 = (RadioButton) (myView.findViewById(R.id.Choice4));
        choice4 = (RadioButton) (myView.findViewById(R.id.Choice5));
        submitButton = (Button) (myView.findViewById(R.id.SubmitAnswerButton));
        pic = (ImageView) (myView.findViewById(R.id.Question));
    }

    private class ButtonListener implements View.OnClickListener {
        RadioButton correctRadioButton;

        public ButtonListener(RadioButton r) {
            correctRadioButton = r;
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            TextView t = new TextView(getContext());

            if (correctRadioButton == myView.findViewById(choiceGroup.getCheckedRadioButtonId())) {
                t.setText("Correct!");
            } else {
                t.setText("Incorrect!");
            }

            t.setTypeface(Typeface.DEFAULT_BOLD);
            t.setTextSize(25);
            t.setGravity(Gravity.CENTER_HORIZONTAL);
            t.setPadding(0,50,0,0);
            builder.setView(t).setPositiveButton("See Solution", dialogClickListener)
                    .setNegativeButton("Return to Game", dialogClickListener);

            AlertDialog alert = builder.create();
            alert.setCanceledOnTouchOutside(false);
            alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            alert.show();
        }

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        solutionFrag = new SolutionSection();
                        solutionFrag.show(fm, "dialog_fragment_solution");
                        dismiss();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dismiss();
                        resumeGame();
                        break;
                }
            }
        };
    }


    public int getSolutionID(){
        return myQuestion.getSolutionId();
    }

    interface QuestionSectionListener {
        public void resumeGame();
        public Question selectQuestion();
    }
}

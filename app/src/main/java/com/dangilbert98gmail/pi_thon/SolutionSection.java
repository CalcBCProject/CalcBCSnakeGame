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

/**
 * Created by Teddy on 5/17/2016.
 */
public class SolutionSection extends DialogFragment {
    private Button returnButton;
    private ImageView solution, question;
    private SolutionSectionListener playActivity;
    private View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.dialog_fragment_solution, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        setButtons();
        setup(playActivity.getSolutionID(), playActivity.getQuestionID());

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
            playActivity = (SolutionSectionListener) context;
        } catch (Exception e) {
            throw new ClassCastException(playActivity.toString());
        }
    }

    public void setButtons(){
        solution = (ImageView) (myView.findViewById(R.id.Solution));
        question = (ImageView) (myView.findViewById(R.id.Question));
        returnButton = (Button)(myView.findViewById(R.id.ReturnButton));
    }

    public void setup(int solutionID, int questionID){
        solution.setImageDrawable(getContext().getResources().getDrawable(solutionID));
        question.setImageDrawable(getContext().getResources().getDrawable(questionID));
        returnButton.setOnClickListener(new ButtonListener());
    }

    public class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            dismiss();
            playActivity.resumeGame();
        }
    }

    interface SolutionSectionListener{
        public int getSolutionID();
        public int getQuestionID();
        public void resumeGame();
    }
}

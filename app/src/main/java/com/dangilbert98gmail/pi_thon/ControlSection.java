package com.dangilbert98gmail.pi_thon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class ControlSection extends Fragment {
    private final double PAUSE_WIDTH_RATIO = .8, PAUSE_HEIGHT_RATIO = .6, BUTTON_WIDTH_RATIO = .15, BUTTON_HEIGHT_RATIO = .15;

    private Button leftButton, rightButton, upButton, downButton, anchorButton;
    private ArrayList<Button> myButtons;
    private ImageButton pauseButton;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private View popupView, parent, myView;
    private DisplayMetrics dm;

    private ControlSectionListener playActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_control_section, container, false);

        layoutInflater = (LayoutInflater)(getActivity().getBaseContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        popupView = layoutInflater.inflate(R.layout.activity_pause_screen, null);

        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        myButtons = new ArrayList<Button>();
        setupButtons();
        setupButtonListeners();

        return myView;
    }

    public class ButtonListener implements View.OnClickListener{
        private SnakeDirection direction;

        public ButtonListener(SnakeDirection d){
            direction = d;
        }

        @Override
        public void onClick(View v) {
            if(playActivity != null) {
                playActivity.setSnakeDirection(direction);
            }
        }
    }

    public class ImageButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(playActivity != null) {
                popupWindow = new PopupWindow(popupView, (int)(dm.widthPixels * PAUSE_WIDTH_RATIO), (int)(dm.heightPixels * PAUSE_HEIGHT_RATIO));
                playActivity.pauseGame();
                popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

                /* Attempts at blurring background */
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.dimAmount = 0.4f;
                getActivity().getWindow().setAttributes(lp);
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            playActivity = (ControlSectionListener)context;
        } catch (Exception e){
            throw new ClassCastException(playActivity.toString());
        }
    }

    public void setParentInfo(View p){
        parent = p;
    }

    public void disableButtons(){
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
        upButton.setEnabled(false);
        downButton.setEnabled(false);
        pauseButton.setEnabled(false);
    }

    public void enableButtons(){
        leftButton.setEnabled(true);
        rightButton.setEnabled(true);
        upButton.setEnabled(true);
        downButton.setEnabled(true);
        pauseButton.setEnabled(true);

        //Also closes Pause Menu, if it's active
        if(popupWindow != null){
            popupWindow.dismiss();
        }
    }

    private void setupButtons(){

        leftButton = (Button)(myView.findViewById(R.id.LeftButton));
        rightButton = (Button)(myView.findViewById(R.id.RightButton));
        upButton = (Button)(myView.findViewById(R.id.UpButton));
        downButton = (Button)(myView.findViewById(R.id.DownButton));
        anchorButton = (Button)(myView.findViewById(R.id.AnchorButton));
        pauseButton = (ImageButton)(myView.findViewById(R.id.PauseButton));

        myButtons.add(leftButton);
        myButtons.add(rightButton);
        myButtons.add(upButton);
        myButtons.add(downButton);
        myButtons.add(anchorButton);

        for(Button b : myButtons){
            if (b.getId() == R.id.LeftButton || b.getId() == R.id.RightButton){
                RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams)b.getLayoutParams();
                p.width = (int)(dm.widthPixels * BUTTON_WIDTH_RATIO);
                p.height = (int)(dm.heightPixels * BUTTON_HEIGHT_RATIO);
                b.setLayoutParams(p);
            }
            if (b.getId() == R.id.DownButton || b.getId() == R.id.UpButton ){
                RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams)b.getLayoutParams();
                p.width = (int)(dm.heightPixels * BUTTON_HEIGHT_RATIO);
                p.height = (int)(dm.widthPixels * BUTTON_WIDTH_RATIO);
                b.setLayoutParams(p);
            }
            if (b.getId() == R.id.AnchorButton){
                RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams)b.getLayoutParams();
                p.width = (int)(dm.heightPixels * BUTTON_HEIGHT_RATIO) + 20;
                p.height = (int)(dm.widthPixels * BUTTON_WIDTH_RATIO);
                b.setLayoutParams(p);
            }
            b.setMaxHeight(400);
            b.setMaxWidth(400);
            b.setMinHeight(0);
            b.setMinWidth(0);
        }
    }

    private void setupButtonListeners(){
        leftButton.setOnClickListener( new ButtonListener(SnakeDirection.LEFT));
        rightButton.setOnClickListener( new ButtonListener(SnakeDirection.RIGHT));
        upButton.setOnClickListener( new ButtonListener(SnakeDirection.UP));
        downButton.setOnClickListener( new ButtonListener(SnakeDirection.DOWN));
        pauseButton.setOnClickListener( new ImageButtonListener());
    }

    interface ControlSectionListener{
        public void setSnakeDirection(SnakeDirection direction);
        public void pauseGame();
    }
}

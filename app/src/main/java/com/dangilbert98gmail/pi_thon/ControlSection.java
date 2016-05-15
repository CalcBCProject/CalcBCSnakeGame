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


public class ControlSection extends Fragment {
    private final double WIDTH_SCREEN_RATIO = .8, HEIGHT_SCREEN_RATIO = .6;

    private Button leftButton, rightButton, upButton, downButton;
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

        setButtons();
        setButtonListeners();

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
                popupWindow = new PopupWindow(popupView, (int)(dm.widthPixels * WIDTH_SCREEN_RATIO), (int)(dm.heightPixels * HEIGHT_SCREEN_RATIO));
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

    private void setButtons(){
        leftButton = (Button)(myView.findViewById(R.id.LeftButton));
        rightButton = (Button)(myView.findViewById(R.id.RightButton));
        upButton = (Button)(myView.findViewById(R.id.UpButton));
        downButton = (Button)(myView.findViewById(R.id.DownButton));
        pauseButton = (ImageButton)(myView.findViewById(R.id.PauseButton));

    }

    private void setButtonListeners(){
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

package com.dangilbert98gmail.pi_thon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ControlSection extends Fragment {
    private Button leftButton, rightButton, upButton, downButton;

    ControlSectionListener playActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_control_section, container, false);

        leftButton = (Button)(v.findViewById(R.id.LeftButton));
        rightButton = (Button)(v.findViewById(R.id.RightButton));
        upButton = (Button)(v.findViewById(R.id.UpButton));
        downButton = (Button)(v.findViewById(R.id.DownButton));

        leftButton.setOnClickListener( new buttonListener(SnakeDirection.LEFT));
        rightButton.setOnClickListener( new buttonListener(SnakeDirection.RIGHT));
        upButton.setOnClickListener( new buttonListener(SnakeDirection.UP));
        downButton.setOnClickListener( new buttonListener(SnakeDirection.DOWN));

        return v;
    }

    public class buttonListener implements View.OnClickListener{
        private SnakeDirection direction;

        public buttonListener(SnakeDirection d){
            direction = d;
        }

        @Override
        public void onClick(View v) {
            if(playActivity != null)
            playActivity.setSnakeDirection(direction);
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

    public interface ControlSectionListener{
        public void setSnakeDirection(SnakeDirection direction);
    }
}

package com.dangilbert98gmail.pi_thon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlSection.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlSection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlSection extends Fragment {
    private PlayScreen playActivity = PlayScreen.getInstance();
    private Button leftButton, rightButton, upButton, downButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_control_section, container, false);

        leftButton = (Button)(v.findViewById(R.id.LeftButton));
        rightButton = (Button)(v.findViewById(R.id.RightButton));
        upButton = (Button)(v.findViewById(R.id.UpButton));
        downButton = (Button)(v.findViewById(R.id.DownButton));

        leftButton.setOnClickListener( new buttonListener(SnakeDirection.Left));
        rightButton.setOnClickListener( new buttonListener(SnakeDirection.Right));
        upButton.setOnClickListener( new buttonListener(SnakeDirection.Up));
        downButton.setOnClickListener( new buttonListener(SnakeDirection.Down));

        return v;
    }

    public class buttonListener implements View.OnClickListener{
        private SnakeDirection direction;

        public buttonListener(SnakeDirection d){
            direction = d;
        }

        @Override
        public void onClick(View v) {
            playActivity.setSnakeDirection(direction);
        }
    }
}

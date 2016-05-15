package com.dangilbert98gmail.pi_thon;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

/**
 * Created by user on 5/14/2016.
 */
public class PauseSection extends AppCompatActivity {
    private ImageButton resumeButton;
    private CheckBox toggleQuestions;
    private PauseSectionListener playActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pause_screen );

        setButtons();
        setButtonListeners();
    }

    public class ImageButtonListeners implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            resumeGame();
        }
    }

    public class CheckBoxListeners implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.d("A1", "Clicked!" + 2);
        }
    }

    private void setButtons(){
        resumeButton = (ImageButton) (findViewById(R.id.ResumeButton));
        toggleQuestions = (CheckBox) (findViewById(R.id.CheckBox));
    }

    private void setButtonListeners(){
        resumeButton.setOnClickListener(new ImageButtonListeners());
        toggleQuestions.setOnClickListener(new CheckBoxListeners());
    }

    public interface PauseSectionListener{
        public void resumeGame();
    }

    public void resumeGame(){
        Intent intent = new Intent(this, PlayScreen.class);
        startActivity(intent);
        finish();
    }
}

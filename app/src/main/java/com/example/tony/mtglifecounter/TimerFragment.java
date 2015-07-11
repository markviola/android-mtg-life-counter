package com.example.tony.mtglifecounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.util.Log;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.RelativeLayout;

public class TimerFragment extends Fragment {

    private static final String TAG = "Tony message";
    TextView countdownText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        countdownText = (TextView) view.findViewById(R.id.countdownText);

        new CountDownTimer(3000000, 1000) {

            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished/1000/60;
                long seconds = millisUntilFinished/1000%60;

                if(seconds < 10){
                    countdownText.setText("Time remaining: " + minutes + ":0" + String.valueOf(seconds));
                } else {
                    countdownText.setText("Time remaining: " + minutes + ":" + seconds);
                }
            }
            public void onFinish() {
                countdownText.setText("THE GAME HAS ENDED!");
            }
        }.start();

        return view;
    }

    public String getNewName(){
        return countdownText.getText().toString();
    }

    public void setPlayerName(String currentName){ countdownText.setText("Change " + currentName + "'s Name:"); }

    public void hideTimer(){
        countdownText.setVisibility(View.GONE);
    }
}

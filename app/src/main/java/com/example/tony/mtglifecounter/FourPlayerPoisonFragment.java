package com.example.tony.mtglifecounter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.util.Log;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

public class FourPlayerPoisonFragment extends Fragment {

    private static final String TAG = "Tony message";
    Button add_life_total, sub_life_total;
    TextView player_name, player_life;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.four_player_poison_fragment, container, false);
        add_life_total = (Button) view.findViewById(R.id.add_life_total);
        sub_life_total = (Button) view.findViewById(R.id.sub_life_total);
        player_name = (TextView) view.findViewById(R.id.player_name);
        player_life = (TextView) view.findViewById(R.id.player_life);

        add_life_total.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        add_action();
                    }
                }
        );

        sub_life_total.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        sub_action();
                    }
                }
        );

        return view;
    }

    public void add_action(){
        int current_life = Integer.parseInt(player_life.getText().toString());
        if (current_life < 10) {
            current_life++;
            player_life.setText(String.valueOf(current_life));
        }
    }

    public void sub_action(){
        int current_life = Integer.parseInt(player_life.getText().toString());
        if (current_life > 0) {
            current_life--;
            player_life.setText(String.valueOf(current_life));
        }
    }

    public void resetPoison(){

        player_life.setText("0");
    }

    public String getPoison(){
        return player_life.getText().toString();
    }

    public void setPoison(String poisonValue){
        player_life.setText(poisonValue);
    }
}

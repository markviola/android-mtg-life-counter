package com.example.tony.mtglifecounter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class ThreePlayerLifeFragment extends Fragment{

    private static final String TAG = "Tony message";
    Button add_life_total, sub_life_total, add_life_total_5, sub_life_total_5;
    TextView player_name, player_life;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.three_player_life_fragment, container, false);
        add_life_total = (Button) view.findViewById(R.id.add_life_total);
        sub_life_total = (Button) view.findViewById(R.id.sub_life_total);
        add_life_total_5 = (Button) view.findViewById(R.id.add_life_total_5);
        sub_life_total_5 = (Button) view.findViewById(R.id.sub_life_total_5);
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

        add_life_total_5.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        add_5_action();
                    }
                }
        );

        sub_life_total_5.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        sub_5_action();
                    }
                }
        );

        return view;
    }

    public void add_action(){
        int current_life = Integer.parseInt(player_life.getText().toString());
        current_life++;
        player_life.setText(String.valueOf(current_life));
    }

    public void sub_action(){
        int current_life = Integer.parseInt(player_life.getText().toString());
        if (current_life > 0) {
            current_life--;
            player_life.setText(String.valueOf(current_life));
        }
    }

    public void add_5_action(){
        int current_life = Integer.parseInt(player_life.getText().toString());
        current_life += 5;
        player_life.setText(String.valueOf(current_life));
    }

    public void sub_5_action(){
        int current_life = Integer.parseInt(player_life.getText().toString());
        if (current_life > 4) {
            current_life -= 5;
            player_life.setText(String.valueOf(current_life));
        } else {
            player_life.setText("0");
        }
    }

    public void resetLife(){
        player_life.setText("20");
    }

    public String getLife(){
        return player_life.getText().toString();
    }

    public void setLife(String lifeValue){
        player_life.setText(lifeValue);
    }

    public void setName(String newName){
        player_name.setText(newName);
    }
}

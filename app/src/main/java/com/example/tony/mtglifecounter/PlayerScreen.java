package com.example.tony.mtglifecounter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

public class PlayerScreen extends Fragment {

    Button add_life_total, sub_life_total;
    TextView player_name, player_life;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_screen, container, false);
        add_life_total = (Button) view.findViewById(R.id.add_life_total);
        sub_life_total = (Button) view.findViewById(R.id.sub_life_total);
        player_name = (TextView) view.findViewById(R.id.player_name);
        player_life = (TextView) view.findViewById(R.id.player_life);

        return view;
    }

    public void add_action(){
        int current_life = Integer.parseInt(player_life.getText().toString());
        current_life++;
        player_life.setText(String.valueOf(current_life));
    }

    public void sub_action(){
        int current_life = Integer.parseInt(player_life.getText().toString());
        current_life--;
        player_life.setText(String.valueOf(current_life));
    }
}

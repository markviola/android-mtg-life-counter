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
import android.widget.RelativeLayout;


public class TwoPlayerLifeFragment extends Fragment {

    private static final String TAG = "Tony message";
    Button add_life_total, sub_life_total, add_life_total_5, sub_life_total_5;
    TextView player_name, player_life;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.two_player_life_fragment, container, false);
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
                new View.OnClickListener() {
                    public void onClick(View v) {
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

    public String getLife(){
        return player_life.getText().toString();
    }

    public void setLife(String lifeValue){
        player_life.setText(lifeValue);
    }

    public void setName(String newName){
        player_name.setText(newName);
    }

    public void invertPlayerScreen(){
        RelativeLayout.LayoutParams player_name_layout=(RelativeLayout.LayoutParams)player_name.getLayoutParams();
        player_name_layout.setMargins(0, 184*2, 0, 0);
        player_name.setRotation(180);

        RelativeLayout.LayoutParams player_life_layout=(RelativeLayout.LayoutParams)player_life.getLayoutParams();
        player_life_layout.setMargins(0, 102*2, 0, 0);
        player_life.setRotation(180);

        RelativeLayout.LayoutParams add_1_layout=(RelativeLayout.LayoutParams)add_life_total.getLayoutParams();
        add_1_layout.addRule(RelativeLayout.RIGHT_OF, 0);
        add_1_layout.addRule(RelativeLayout.LEFT_OF, player_life.getId());
        add_1_layout.setMargins(0, 95*2, 25*2, 0);
        add_1_layout.alignWithParent = true;
        add_life_total.setRotation(180);

        RelativeLayout.LayoutParams sub_1_layout=(RelativeLayout.LayoutParams)sub_life_total.getLayoutParams();
        sub_1_layout.addRule(RelativeLayout.LEFT_OF, 0);
        sub_1_layout.addRule(RelativeLayout.RIGHT_OF, player_life.getId());
        sub_1_layout.setMargins(25*2, 95*2, 0, 0);
        sub_1_layout.alignWithParent = true;
        sub_life_total.setRotation(180);

        RelativeLayout.LayoutParams add_5_layout=(RelativeLayout.LayoutParams)add_life_total_5.getLayoutParams();
        add_5_layout.addRule(RelativeLayout.RIGHT_OF, 0);
        add_5_layout.addRule(RelativeLayout.LEFT_OF, player_life.getId());
        add_5_layout.setMargins(0, 95*2, 90*2, 0);
        add_5_layout.alignWithParent = true;
        add_life_total_5.setRotation(180);

        RelativeLayout.LayoutParams sub_5_layout=(RelativeLayout.LayoutParams)sub_life_total_5.getLayoutParams();
        sub_5_layout.addRule(RelativeLayout.LEFT_OF, 0);
        sub_5_layout.addRule(RelativeLayout.RIGHT_OF, player_life.getId());
        sub_5_layout.setMargins(90*2, 95*2, 0, 0);
        sub_5_layout.alignWithParent = true;
        sub_life_total_5.setRotation(180);
    }
}
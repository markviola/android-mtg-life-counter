package com.example.tony.mtglifecounter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class FourPlayerFragment extends Fragment{

    private static final String TAG = "Tony message";
    Button add_life_total, sub_life_total, add_life_total_5, sub_life_total_5;
    TextView player_name, player_life;
    ImageView poisonCounters[] = new ImageView[10];
    ImageButton incPoison, decPoison;
    int currentPoison;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four_player, container, false);
        add_life_total = (Button) view.findViewById(R.id.add_life_total);
        sub_life_total = (Button) view.findViewById(R.id.sub_life_total);
        add_life_total_5 = (Button) view.findViewById(R.id.add_life_total_5);
        sub_life_total_5 = (Button) view.findViewById(R.id.sub_life_total_5);
        player_name = (TextView) view.findViewById(R.id.player_name);
        player_life = (TextView) view.findViewById(R.id.player_life);
        incPoison = (ImageButton) view.findViewById(R.id.incPoison);
        decPoison = (ImageButton) view.findViewById(R.id.decPoison);
        currentPoison = 0;

        //Initialize the poison counter array
        setPoisonCounters(view);

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

        incPoison.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        increment_poison(currentPoison);
                    }
                }
        );

        decPoison.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        decrement_poison(currentPoison);
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

    public void setPoisonCounterView(int currentPoisonValue){
        for (int i = 0; i < currentPoisonValue; i++){
            poisonCounters[i].setVisibility(View.VISIBLE);
        }
        for (int j = currentPoisonValue; j < 10; j++){
            poisonCounters[j].setVisibility(View.GONE);
        }
        //change global poison counter value to the inputted value from ThreePlayerScreen
        currentPoison = currentPoisonValue;
    }

    public void increment_poison(int currentPoisonValue){
        if(currentPoison < 10){
            poisonCounters[currentPoisonValue].setVisibility(View.VISIBLE);
            currentPoison++;
        }
    }

    public void decrement_poison(int currentPoisonValue){
        if(currentPoison > 0){
            poisonCounters[currentPoisonValue-1].setVisibility(View.GONE);
            currentPoison--;
        }
    }

    public void setPoisonCounters(View view){
        poisonCounters[0] = (ImageView) view.findViewById(R.id.poisonCounterStart);
        poisonCounters[1] = (ImageView) view.findViewById(R.id.poisonCounter2);
        poisonCounters[2] = (ImageView) view.findViewById(R.id.poisonCounter3);
        poisonCounters[3] = (ImageView) view.findViewById(R.id.poisonCounter4);
        poisonCounters[4] = (ImageView) view.findViewById(R.id.poisonCounter5);
        poisonCounters[5] = (ImageView) view.findViewById(R.id.poisonCounter6);
        poisonCounters[6] = (ImageView) view.findViewById(R.id.poisonCounter7);
        poisonCounters[7] = (ImageView) view.findViewById(R.id.poisonCounter8);
        poisonCounters[8] = (ImageView) view.findViewById(R.id.poisonCounter9);
        poisonCounters[9] = (ImageView) view.findViewById(R.id.poisonCounter10);

        for (int i = 0; i < 10; i++) {
            poisonCounters[i].setVisibility(View.GONE);
        }
    }

    public String getLife(){
        return player_life.getText().toString();
    }

    public int getPoisonCounterValue(){
        return currentPoison;
    }

    public void setLife(String lifeValue){
        player_life.setText(lifeValue);
    }

    public void setName(String newName){
        player_name.setText(newName);
    }
}

package com.example.tony.mtglifecounter;

import android.annotation.TargetApi;
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


public class TwoPlayerFragment extends Fragment {

    private static final String TAG = "Tony message";
    Button add_life_total, sub_life_total, add_life_total_5, sub_life_total_5;
    TextView player_name, player_life;
    ImageView poisonCounters[] = new ImageView[10];
    ImageButton incPoison, decPoison;
    int currentPoison;
    boolean isInverted = false;

    DisplayMetrics displayMetrics;
    DBManager dbManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_player, container, false);
        add_life_total = (Button) view.findViewById(R.id.add_life_total);
        sub_life_total = (Button) view.findViewById(R.id.sub_life_total);
        add_life_total_5 = (Button) view.findViewById(R.id.add_life_total_5);
        sub_life_total_5 = (Button) view.findViewById(R.id.sub_life_total_5);
        player_name = (TextView) view.findViewById(R.id.player_name);
        player_life = (TextView) view.findViewById(R.id.player_life);
        incPoison = (ImageButton) view.findViewById(R.id.incPoison);
        decPoison = (ImageButton) view.findViewById(R.id.decPoison);
        currentPoison = 0; //Keeps track of which element in the posionCounters array it currently is

        //Allows the use of the database containing all the player information
        dbManager = new DBManager(getActivity(), null, null, 1);

        //Initialize the poison counter array
        setPoisonCounters(view);

        //Get phone screen data
        displayMetrics = getActivity().getResources().getDisplayMetrics();

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
        currentPoison = currentPoisonValue;
    }

    public void increment_poison(int currentPoisonValue){
        if(currentPoison < 10 && !isInverted){
            poisonCounters[currentPoisonValue].setVisibility(View.VISIBLE);
            currentPoison++;
        } else if (currentPoison > 0 && isInverted){
            poisonCounters[currentPoisonValue-1].setVisibility(View.GONE);
            currentPoison--;
        }
    }

    public void decrement_poison(int currentPoisonValue){
        if(currentPoison > 0 && !isInverted){
            poisonCounters[currentPoisonValue-1].setVisibility(View.GONE);
            currentPoison--;
        } else if (currentPoison < 10 && isInverted){
            poisonCounters[currentPoisonValue].setVisibility(View.VISIBLE);
            currentPoison++;
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

    public void setInverted(boolean inverted){
        isInverted = inverted;
    }

    @TargetApi(11)
    public void invertPlayerScreen(){
        int screenDensity = Math.round(displayMetrics.density);

        //Characteristics change for player name
        RelativeLayout.LayoutParams player_name_layout=(RelativeLayout.LayoutParams)player_name.getLayoutParams();
        player_name_layout.setMargins(0, 235*screenDensity, 0, 0);
        player_name.setRotation(180);

        //Characteristics change for player life
        RelativeLayout.LayoutParams player_life_layout=(RelativeLayout.LayoutParams)player_life.getLayoutParams();
        player_life_layout.setMargins(0, 50*screenDensity, 0, 0);
        player_life.setRotation(180);

        //Characteristics change for add 1 button
        RelativeLayout.LayoutParams add_1_layout=(RelativeLayout.LayoutParams)add_life_total.getLayoutParams();
        add_1_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
        add_1_layout.setMargins(10*screenDensity, 140*screenDensity, 0, 0);
        add_life_total.setRotation(180);

        //Characteristics change for subtract 1 button
        RelativeLayout.LayoutParams sub_1_layout=(RelativeLayout.LayoutParams)sub_life_total.getLayoutParams();
        sub_1_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        sub_1_layout.setMargins(0, 140*screenDensity, 10*screenDensity, 0);
        sub_life_total.setRotation(180);

        //Characteristics change for add 5 button
        RelativeLayout.LayoutParams add_5_layout=(RelativeLayout.LayoutParams)add_life_total_5.getLayoutParams();
        add_5_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
        add_5_layout.setMargins(10*screenDensity, 95*screenDensity, 0, 0);
        add_life_total_5.setRotation(180);

        //Characteristics change for subtract 5 button
        RelativeLayout.LayoutParams sub_5_layout=(RelativeLayout.LayoutParams) sub_life_total_5.getLayoutParams();
        sub_5_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        sub_5_layout.setMargins(0, 95 * screenDensity, 10 * screenDensity, 0);
        sub_life_total_5.setRotation(180);

        RelativeLayout.LayoutParams poisonItems[] = new RelativeLayout.LayoutParams[12];

        poisonItems[0] = (RelativeLayout.LayoutParams)decPoison.getLayoutParams();
        poisonItems[1] = (RelativeLayout.LayoutParams)poisonCounters[0].getLayoutParams();
        poisonItems[2] = (RelativeLayout.LayoutParams)poisonCounters[1].getLayoutParams();
        poisonItems[3] = (RelativeLayout.LayoutParams)poisonCounters[2].getLayoutParams();
        poisonItems[4] = (RelativeLayout.LayoutParams)poisonCounters[3].getLayoutParams();
        poisonItems[5] = (RelativeLayout.LayoutParams)poisonCounters[4].getLayoutParams();
        poisonItems[6] = (RelativeLayout.LayoutParams)poisonCounters[5].getLayoutParams();
        poisonItems[7] = (RelativeLayout.LayoutParams)poisonCounters[6].getLayoutParams();
        poisonItems[8] = (RelativeLayout.LayoutParams)poisonCounters[7].getLayoutParams();
        poisonItems[9] = (RelativeLayout.LayoutParams)poisonCounters[8].getLayoutParams();
        poisonItems[10] = (RelativeLayout.LayoutParams)poisonCounters[9].getLayoutParams();
        poisonItems[11] = (RelativeLayout.LayoutParams)incPoison.getLayoutParams();

        for(int i = 0; i < 12; i++){
            poisonItems[i].topMargin = 25 * screenDensity;
        }

        poisonItems[1].addRule(RelativeLayout.RIGHT_OF, 0);
        poisonItems[1].addRule(RelativeLayout.LEFT_OF, incPoison.getId());

        for(int i = 2; i < 11; i++){
            poisonItems[i].addRule(RelativeLayout.RIGHT_OF, 0);
            poisonItems[i].addRule(RelativeLayout.END_OF, 0);
            poisonItems[i].addRule(RelativeLayout.LEFT_OF, poisonCounters[i-2].getId());
            poisonItems[i].rightMargin = 5*screenDensity;
            poisonItems[i].leftMargin = 0;
        }
    }
}
package com.example.tony.mtglifecounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class MainActivity extends ActionBarActivity implements DialogInterface.OnClickListener{

    private static final String TAG = "Tony message";
    TextView num_players, title;
    CheckBox gameTimer, invertSecondPlayer, commanderMode, twoHeadedGiantMode;
    DBManager dbManager;
    AlertDialog popUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        title = (TextView)findViewById(R.id.title);
        num_players = (TextView)findViewById(R.id.num_players);
        gameTimer = (CheckBox) findViewById(R.id.addTimerBox);
        invertSecondPlayer = (CheckBox) findViewById(R.id.invertSecondPlayerBox);
        commanderMode = (CheckBox) findViewById(R.id.commanderMode);
        twoHeadedGiantMode = (CheckBox) findViewById(R.id.twoHeadedGiantMode);

        //Allows the use of the database containing all the player information
        dbManager = new DBManager(this, null, null, 1);

        //Only allow four rows to be created for each player; prevents duplicates
        if (dbManager.isEmpty()){
            setup();
        } else {
            resetTotals();
        }
        twoHeadedGiantMode.setVisibility(View.GONE); //Player number defaults at two players
    }

    //Add the four players and game states into the database
    public void setup(){
        Log.i(TAG, "In setup");
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        Player playerThree = new Player("Player Three");
        Player playerFour = new Player("Player Four");

        //The only time information is added to the database is on setup
        dbManager.addPlayer(playerOne);
        dbManager.addPlayer(playerTwo);
        dbManager.addPlayer(playerThree);
        dbManager.addPlayer(playerFour);
        dbManager.addState("invertPlayerTwo", "false");
        dbManager.addState("commanderMode", "false");
        dbManager.addState("twoHeadedGiantMode", "false");
    }

    //Update database data to base settings
    public void resetTotals(){
        Log.i(TAG, "In resetTotals");

        for(int i = 0; i < 4; i++){
            dbManager.updateLife(i + 1, "20");
            dbManager.updatePoison(i + 1, "0");
        }

        dbManager.updatePlayerName(1, "Player One");
        dbManager.updatePlayerName(2, "Player Two");
        dbManager.updatePlayerName(3, "Player Three");
        dbManager.updatePlayerName(4, "Player Four");

        dbManager.updateState("invertPlayerTwo", "false");
        dbManager.updateState("commanderMode", "false");
        dbManager.updateState("twoHeadedGiantMode", "false");
    }

    public void confirm_button(View v){
        int current_players = Integer.parseInt(num_players.getText().toString());

        Intent two_player = new Intent(getApplicationContext(), TwoPlayerScreen.class);
        Intent three_player = new Intent(getApplicationContext(), ThreePlayerScreen.class);
        Intent four_player = new Intent(getApplicationContext(), FourPlayerScreen.class);

        //Check if the "Invert second players screen" was selected
        if (invertSecondPlayer.isChecked()){
            dbManager.updateState("invertPlayerTwo", "true");
        }

        if (commanderMode.isChecked()){
            dbManager.updateState("commanderMode", "true");
            changeStartingLife("40");
        }

        if (current_players == 2) {
            startActivity(two_player);
        } else if (current_players == 3) {
            startActivity(three_player);
        } else if (current_players == 4){
            if(commanderMode.isChecked() && twoHeadedGiantMode.isChecked()) {
                popUp = new AlertDialog.Builder(this)
                        .setTitle("Choose One Mode")
                        .setPositiveButton("Two-Headed Giant", this)
                        .setNegativeButton("Commander", this)
                        .setCancelable(false)
                        .create();
                popUp.show();
            } else if (twoHeadedGiantMode.isChecked()) {
                dbManager.updateState("twoHeadedGiantMode", "true");
                dbManager.updatePlayerName(1, "Team One");
                dbManager.updatePlayerName(2, "Team Two");
                changeStartingLife("30");
                twoHeadedGiantInverted();
            } else {
                startActivity(four_player);
            }
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent two_player = new Intent(getApplicationContext(), TwoPlayerScreen.class);
        Intent four_player = new Intent(getApplicationContext(), FourPlayerScreen.class);
        Log.i(TAG, "Before text stuff");
        TextView text = ((TextView) popUp.findViewById(getResources().getIdentifier(
                "alertTitle", "id", "android")));
        String popUpTitle = text.getText().toString();
        Log.i(TAG, popUpTitle);

        switch(which){
            case DialogInterface.BUTTON_POSITIVE:
                //Second popup, "Yes" selected
                if(popUpTitle.equals("Invert Second Team's Screen")){
                    dbManager.updateState("invertPlayerTwo", "true");
                    dbManager.updateState("twoHeadedGiantMode", "true");
                    dbManager.updatePlayerName(1, "Team One");
                    dbManager.updatePlayerName(2, "Team Two");
                    changeStartingLife("30");
                    startActivity(two_player);
                }
                //First popup, "Two-Headed Giant" selected
                else {
                    dbManager.updateState("twoHeadedGiantMode", "true");
                    dbManager.updatePlayerName(1, "Team One");
                    dbManager.updatePlayerName(2, "Team Two");
                    changeStartingLife("30");
                    twoHeadedGiantInverted();
                }
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                //Second popup, "No" selected
                if(popUpTitle.equals("Invert Second Team's Screen")){
                    dbManager.updateState("twoHeadedGiantMode", "true");
                    dbManager.updatePlayerName(1, "Team One");
                    dbManager.updatePlayerName(2, "Team Two");
                    changeStartingLife("30");
                    startActivity(two_player);
                }
                //First pop up, "Commander" selected
                else {
                    dbManager.updateState("commanderMode", "true");
                    changeStartingLife("40");
                    startActivity(four_player);
                }
                break;
            default:
        }
    }

    public void twoHeadedGiantInverted(){
        popUp = new AlertDialog.Builder(this)
                .setTitle("Invert Second Team's Screen")
                .setPositiveButton("Yes", this)
                .setNegativeButton("No", this)
                .setCancelable(false)
                .create();
        popUp.show();
    }

    public void changeStartingLife(String newValue){
        for(int i = 0; i < 4; i++){
            dbManager.updateLife(i + 1, newValue);
        }
    }

    public void plus_button(View v){
        int current_players = Integer.parseInt(num_players.getText().toString());
        if (current_players < 4) {
            current_players++;
            num_players.setText(String.valueOf(current_players));

            //Only show the Two-Headed Giant mode option when four players is the current choice
            if (current_players == 4){
                twoHeadedGiantMode.setVisibility(View.VISIBLE);
            }
        }

        //Any player increment leads to a player number greater than two, so hide checkbox
        invertSecondPlayer.setVisibility(View.GONE);
    }

    public void sub_button(View v){
        int current_players = Integer.parseInt(num_players.getText().toString());
        if (current_players > 2) {
            current_players--;
            num_players.setText(String.valueOf(current_players));
            twoHeadedGiantMode.setVisibility(View.GONE);

            //Only show the inverted second player option when two players is the current choice
            if (current_players == 2){
                invertSecondPlayer.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

        //Hides Action Bar
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

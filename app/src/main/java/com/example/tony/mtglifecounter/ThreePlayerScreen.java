package com.example.tony.mtglifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;


public class ThreePlayerScreen extends ActionBarActivity implements ResetAndSettingsFragment.resetListener,
        ResetAndSettingsFragment.settingsListener{

    private static final String TAG = "Tony message";

    String startingLife;
    ThreePlayerFragment players[] = new ThreePlayerFragment[3];
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_player_screen);
        Log.i(TAG, "onCreate ThreePlayerScreen");
        getSupportActionBar().hide();

        //Allows the use of the database containing all the player information
        dbManager = new DBManager(this, null, null, 1);

        //References to Player One and Player Two fragments
        players[0] = (ThreePlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        players[1] = (ThreePlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment4);
        players[2] = (ThreePlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment5);

        //Use data from the MTGDatabase
        for(int i =0; i < 3; i++){
            players[i].setLife(dbManager.dbGetLife(i + 1));
            players[i].setName(dbManager.dbGetName(i + 1));
            players[i].setPoisonCounterView(Integer.parseInt(dbManager.dbGetPoison(i + 1)));
        }

        //Determine if game mode is normal, Commander, or Two-Headed Giant
        if(dbManager.dbGetState("commanderMode").equals("true")){
            startingLife = "40";
        } else if(dbManager.dbGetState("twoHeadedGiantMode").equals("true")){
            startingLife = "30";
        } else {
            startingLife = "20";
        }
    }

    //This gets called by the ResetAndSettingsFragment when "Reset" is clicked
    @Override
    public void resetTotal() {
        for(int i = 0; i < 3; i++){
            dbManager.updateLife(i + 1, startingLife);
            dbManager.updatePoison(i + 1, "0");
            players[i].setLife(dbManager.dbGetLife(i + 1));
            players[i].setPoisonCounterView(Integer.parseInt(dbManager.dbGetPoison(i + 1)));
        }
    }

    //This gets called by the ResetAndSettingsFragment when "Settings" is clicked
    @Override
    public void toSettings() {
        for(int i = 0; i < 3; i++){
            dbManager.updateLife(i + 1, players[i].getLife());
            dbManager.updatePoison(i + 1, Integer.toString(players[i].getPoisonCounterValue()));
        }
        Intent settings = new Intent(getApplicationContext(), Settings.class);
        settings.putExtra("returnTo", "ThreePlayerScreen");
        startActivity(settings);
    }

    //Gets rid of the issue with pressing the back button when too many life/poison activities
    @Override
    public void onBackPressed() {
        Intent back_to_main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(back_to_main);
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_three_player_screen, menu);
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

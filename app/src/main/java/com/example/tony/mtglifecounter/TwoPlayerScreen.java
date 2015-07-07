package com.example.tony.mtglifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;


public class TwoPlayerScreen extends ActionBarActivity implements ResetAndSettingsFragment.resetListener,
        ResetAndSettingsFragment.settingsListener{

    private static final String TAG = "Tony message";

    TwoPlayerFragment playerOne, playerTwo;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_screen);
        Log.i(TAG, "onCreate TwoPlayerScreen");
        getSupportActionBar().hide();   //Gets rid of the action bar

        //Allows the use of the database containing all the player information
        dbManager = new DBManager(this, null, null, 1);

        //References to Player One and Player Two fragments
        if (dbManager.dbGetState("invertPlayerTwo").equals("false")){
            //References to Player One and Player Two fragments
            playerOne = (TwoPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
            playerTwo = (TwoPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        } else {
            playerOne = (TwoPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
            playerTwo = (TwoPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
            playerTwo.invertPlayerScreen();
        }

        //Use data from the MTGDatabase
        playerOne.setLife(dbManager.dbGetLife(1));
        playerTwo.setLife(dbManager.dbGetLife(2));
        playerOne.setName(dbManager.dbGetName(1));
        playerTwo.setName(dbManager.dbGetName(2));
        playerOne.setPoisonCounterView(Integer.parseInt(dbManager.dbGetPoison(1)));
        playerTwo.setPoisonCounterView(Integer.parseInt(dbManager.dbGetPoison(2)));
    }

    //This gets called by the ResetAndSettingsFragment when "Reset" is clicked
    @Override
    public void resetTotal() {
        dbManager.updateLife(1, "20");
        dbManager.updateLife(2, "20");
        dbManager.updatePoison(1, "0");
        dbManager.updatePoison(2, "0");

        playerOne.setLife(dbManager.dbGetLife(1));
        playerTwo.setLife(dbManager.dbGetLife(2));
        playerOne.setPoisonCounterView(Integer.parseInt(dbManager.dbGetPoison(1)));
        playerTwo.setPoisonCounterView(Integer.parseInt(dbManager.dbGetPoison(2)));
    }

    //This gets called by the ResetAndSettingsFragment when "Settings" is clicked
    @Override
    public void toSettings() {
        Intent settings = new Intent(getApplicationContext(), Settings.class);
        dbManager.updateLife(1, playerOne.getLife());
        dbManager.updateLife(2, playerTwo.getLife());
        dbManager.updatePoison(1, Integer.toString(playerOne.getPoisonCounterValue()));
        dbManager.updatePoison(2, Integer.toString(playerTwo.getPoisonCounterValue()));
        settings.putExtra("returnTo", "TwoPlayerScreen");
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
        getMenuInflater().inflate(R.menu.menu_two_player_screen, menu);
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

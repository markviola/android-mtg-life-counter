package com.example.tony.mtglifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.view.View;


public class Settings extends ActionBarActivity {

    private static final String TAG = "Tony message";

    SettingsPlayerFragment playerOne, playerTwo, playerThree, playerFour;
    Bundle dataBundle;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();
        Log.i(TAG, "onCreate Settings");

        playerOne = (SettingsPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment25);
        playerTwo = (SettingsPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment26);
        playerThree = (SettingsPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment27);
        playerFour = (SettingsPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment28);

        //Allows the use of the database containing all the player information
        dbManager = new DBManager(this, null, null, 1);

        //Set player numbers
        playerOne.setPlayerName(dbManager.dbGetName(1));
        playerTwo.setPlayerName(dbManager.dbGetName(2));
        playerThree.setPlayerName(dbManager.dbGetName(3));
        playerFour.setPlayerName(dbManager.dbGetName(4));

        dataBundle = getIntent().getExtras();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    //Update the MTGDatabase with any updated names then go back to the previous activity
    public void confirmButton(View v){
        String returnTo = dataBundle.getString("returnTo");
        Intent returnToIntent;

        if(playerOne.getNewName().length() != 0){
            dbManager.updatePlayerName(1, playerOne.getNewName());
        }
        if(playerTwo.getNewName().length() != 0){
            dbManager.updatePlayerName(2, playerTwo.getNewName());
        }
        if(playerThree.getNewName().length() != 0){
            dbManager.updatePlayerName(3, playerThree.getNewName());
        }
        if(playerFour.getNewName().length() != 0){
            dbManager.updatePlayerName(4, playerFour.getNewName());
        }

        //Determine the correct activity to exit back to
        switch(returnTo){
            case "TwoPlayerScreen":
                returnToIntent = new Intent(getApplicationContext(), TwoPlayerScreen.class);
                break;
            case "TwoPlayerScreenAlt":
                returnToIntent = new Intent(getApplicationContext(), TwoPlayerScreenAlt.class);
                break;
            case "ThreePlayerScreen":
                returnToIntent = new Intent(getApplicationContext(), ThreePlayerScreen.class);
                break;
            case "ThreePlayerScreenAlt":
                returnToIntent = new Intent(getApplicationContext(), ThreePlayerScreenAlt.class);
                break;
            case "FourPlayerScreen":
                returnToIntent = new Intent(getApplicationContext(), FourPlayerScreen.class);
                break;
            case "FourPlayerScreenAlt":
                returnToIntent = new Intent(getApplicationContext(), FourPlayerScreenAlt.class);
                break;
            default:
                returnToIntent = new Intent(getApplicationContext(), MainActivity.class);
        }

        startActivity(returnToIntent);
    }
}
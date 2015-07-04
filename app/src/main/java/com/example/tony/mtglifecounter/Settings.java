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
    String playerOneLife, playerTwoLife, playerThreeLife, playerFourLife,
            playerOnePoison, playerTwoPoison, playerThreePoison, playerFourPoison;
    Bundle dataBundle;
    int numPlayers;

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

        //Set player numbers
        playerOne.setPlayerNumber("1");
        playerTwo.setPlayerNumber("2");
        playerThree.setPlayerNumber("3");
        playerFour.setPlayerNumber("4");

        dataBundle = getIntent().getExtras();
        numPlayers = dataBundle.getInt("numPlayers");

        switch(numPlayers){
            case 4:
                playerFourLife = dataBundle.getString("playerFour Life");
                playerFourPoison = dataBundle.getString("playerFour Poison");
            case 3:
                playerThreeLife = dataBundle.getString("playerThree Life");
                playerThreePoison = dataBundle.getString("playerThree Poison");
            case 2:
                playerOneLife = dataBundle.getString("playerOne Life");
                playerOnePoison = dataBundle.getString("playerOne Poison");
                playerTwoLife = dataBundle.getString("playerTwo Life");
                playerTwoPoison = dataBundle.getString("playerTwo Poison");
                break;
            default:
                Log.i(TAG, "ERROR in Settings");
        }

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

    public void confirmButton(View v){
        String returnTo = dataBundle.getString("returnTo");
        Intent returnToIntent;

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

        //Add all of the necessary information to send back to te returned activity
        switch(returnTo){
            case "FourPlayerScreen":
            case "FourPlayerScreenAlt":
                returnToIntent.putExtra("newPlayerFourName", playerFour.getNewName());
                returnToIntent.putExtra("playerFour Life", playerFourLife);
                returnToIntent.putExtra("playerFour Poison", playerFourPoison);
            case "ThreePlayerScreen":
            case "ThreePlayerScreenAlt":
                returnToIntent.putExtra("newPlayerThreeName", playerThree.getNewName());
                returnToIntent.putExtra("playerThree Life", playerThreeLife);
                returnToIntent.putExtra("playerThree Poison", playerThreePoison);
            case "TwoPlayerScreen":
            case "TwoPlayerScreenAlt":
                returnToIntent.putExtra("newPlayerOneName", playerOne.getNewName());
                returnToIntent.putExtra("newPlayerTwoName", playerTwo.getNewName());
                returnToIntent.putExtra("playerOne Life", playerOneLife);
                returnToIntent.putExtra("playerTwo Life", playerTwoLife);
                returnToIntent.putExtra("playerOne Poison", playerOnePoison);
                returnToIntent.putExtra("playerTwo Poison", playerTwoPoison);
                break;
            default:
                startActivity(returnToIntent);
        }

        startActivity(returnToIntent);
    }
}
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
    Intent mainScreen, twoPlayerLifeScreen, threePlayerLifeScreen, fourPlayerLifeScreen,
            twoPlayerPoisonScreen, threePlayerPoisonScreen, fourPlayerPoisonScreen;
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

        mainScreen = new Intent(getApplicationContext(), MainActivity.class);
        twoPlayerLifeScreen = new Intent(getApplicationContext(), TwoPlayerScreen.class);
        twoPlayerPoisonScreen = new Intent(getApplicationContext(), TwoPlayerScreenAlt.class);
        threePlayerLifeScreen = new Intent(getApplicationContext(), ThreePlayerScreen.class);
        threePlayerPoisonScreen = new Intent(getApplicationContext(), ThreePlayerScreenAlt.class);
        fourPlayerLifeScreen = new Intent(getApplicationContext(), FourPlayerScreen.class);
        fourPlayerPoisonScreen = new Intent(getApplicationContext(), FourPlayerScreenAlt.class);

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
        Intent returnToIntent = new Intent(getApplicationContext(), TwoPlayerScreen.class);

        switch(returnTo){
            case "TwoPlayerScreen":
                returnToIntent.putExtra("newPlayerOneName", playerOne.getNewName());
                returnToIntent.putExtra("newPlayerTwoName", playerTwo.getNewName());
                returnToIntent.putExtra("playerOne Life", playerOneLife);
                returnToIntent.putExtra("playerTwo Life", playerTwoLife);
                returnToIntent.putExtra("playerOne Poison", playerOnePoison);
                returnToIntent.putExtra("playerTwo Poison", playerTwoPoison);
            case "TwoPlayerScreenAlt":
                startActivity(twoPlayerPoisonScreen);
            case "ThreePlayerScreen":
                startActivity(threePlayerLifeScreen);
            case "ThreePlayerScreenAlt":
                startActivity(threePlayerPoisonScreen);
            case "FourPlayerScreen":
                startActivity(fourPlayerLifeScreen);
            case "FourPlayerScreenAlt":
                startActivity(fourPlayerPoisonScreen);
            default:
                startActivity(mainScreen);
        }

        startActivity(returnToIntent);

    }
}
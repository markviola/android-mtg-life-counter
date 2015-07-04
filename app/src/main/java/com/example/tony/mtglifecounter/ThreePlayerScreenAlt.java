package com.example.tony.mtglifecounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;


public class ThreePlayerScreenAlt extends ActionBarActivity implements ResetAndSettingsFragment.resetListener,
        ResetAndSettingsFragment.settingsListener, GestureDetector.OnGestureListener{

    private static final String TAG = "Tony message";
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    ThreePlayerPoisonFragment playerOne, playerTwo, playerThree;
    String playerOneLife, playerTwoLife, playerThreeLife,
            playerOnePoison, playerTwoPoison, playerThreePoison;

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_player_screen_alt);
        Log.i(TAG, "onCreate FourPlayerScreenAlt");
        getSupportActionBar().hide();

        //Dectector for swiping gesture
        this.gestureDetector = new GestureDetectorCompat(this,this);

        //References to Player One and Player Two poison counter fragments
        playerOne = (ThreePlayerPoisonFragment) getSupportFragmentManager().findFragmentById(R.id.fragment16);
        playerTwo = (ThreePlayerPoisonFragment) getSupportFragmentManager().findFragmentById(R.id.fragment17);
        playerThree = (ThreePlayerPoisonFragment) getSupportFragmentManager().findFragmentById(R.id.fragment18);

        //Get the pushed data
        Bundle dataBundle = getIntent().getExtras();
        playerOneLife = dataBundle.getString("playerOne Life");
        playerTwoLife = dataBundle.getString("playerTwo Life");
        playerThreeLife = dataBundle.getString("playerThree Life");
        playerOnePoison = dataBundle.getString("playerOne Poison");
        playerTwoPoison = dataBundle.getString("playerTwo Poison");
        playerThreePoison = dataBundle.getString("playerThree Poison");

        //Set values of poison counter
        playerOne.setPoison(playerOnePoison);
        playerTwo.setPoison(playerTwoPoison);
        playerThree.setPoison(playerThreePoison);
    }

    /***************************************************************
     *                  Gesture Overrides Start                    *
     ***************************************************************/

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    Intent three_player = new Intent(getApplicationContext(), ThreePlayerScreen.class);
                    three_player.putExtra("playerOne Life", playerOneLife);
                    three_player.putExtra("playerTwo Life", playerTwoLife);
                    three_player.putExtra("playerThree Life", playerThreeLife);
                    three_player.putExtra("playerOne Poison", playerOne.getPoison());
                    three_player.putExtra("playerTwo Poison", playerTwo.getPoison());
                    three_player.putExtra("playerThree Poison", playerThree.getPoison());
                    startActivity(three_player);
                }
            }
            result = true;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /***************************************************************
     *                   Gesture Overrides End                     *
     ***************************************************************/

    @Override
    public void resetTotal() {
        playerOne.resetPoison();
        playerTwo.resetPoison();
        playerThree.resetPoison();
        playerOneLife = "20";
        playerTwoLife = "20";
        playerThreeLife = "20";
    }

    @Override
    public void toSettings() {
        Intent settings = new Intent(getApplicationContext(), Settings.class);

        settings.putExtra("playerOne Life", playerOneLife);
        settings.putExtra("playerTwo Life", playerTwoLife);
        settings.putExtra("playerThree Life", playerThreeLife);
        settings.putExtra("playerOne Poison", playerOne.getPoison());
        settings.putExtra("playerTwo Poison", playerTwo.getPoison());
        settings.putExtra("playerThree Poison", playerThree.getPoison());
        settings.putExtra("returnTo", "ThreePlayerScreenAlt");
        settings.putExtra("numPlayers", 3);

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
        getMenuInflater().inflate(R.menu.menu_three_player_screen_alt, menu);
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

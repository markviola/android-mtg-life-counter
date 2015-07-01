package com.example.tony.mtglifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;


public class ThreePlayerScreen extends ActionBarActivity implements ResetAndSettingsFragment.resetListener,
        ResetAndSettingsFragment.settingsListener, GestureDetector.OnGestureListener{

    private static final String TAG = "Tony message";
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    ThreePlayerLifeFragment playerOne, playerTwo, playerThree;
    String playerOneLife, playerTwoLife, playerThreeLife,
            playerOnePoison, playerTwoPoison, playerThreePoison;

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_player_screen);
        Log.i(TAG, "onCreate ThreePlayerScreen");
        getSupportActionBar().hide();

        this.gestureDetector = new GestureDetectorCompat(this,this);

        //References to Player One and Player Two fragments
        playerOne = (ThreePlayerLifeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        playerTwo = (ThreePlayerLifeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment4);
        playerThree = (ThreePlayerLifeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment5);

        //At the start of the game each player has zero poison counters
        Bundle dataBundle = getIntent().getExtras();
        if (dataBundle == null){
            playerOnePoison = "0";
            playerTwoPoison = "0";
            playerThreePoison = "0";
        } else {
            playerOneLife = dataBundle.getString("playerOne Life");
            playerTwoLife = dataBundle.getString("playerTwo Life");
            playerThreeLife = dataBundle.getString("playerThree Life");
            playerOnePoison = dataBundle.getString("playerOne Poison");
            playerTwoPoison = dataBundle.getString("playerTwo Poison");
            playerThreePoison = dataBundle.getString("playerThree Poison");

            playerOne.setLife(playerOneLife);
            playerTwo.setLife(playerTwoLife);
            playerThree.setLife(playerThreeLife);
        }
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
                    Intent three_player = new Intent(getApplicationContext(), ThreePlayerScreenAlt.class);
                    three_player.putExtra("playerOne Life", playerOne.getLife());
                    three_player.putExtra("playerTwo Life", playerTwo.getLife());
                    three_player.putExtra("playerThree Life", playerThree.getLife());
                    three_player.putExtra("playerOne Poison", playerOnePoison);
                    three_player.putExtra("playerTwo Poison", playerTwoPoison);
                    three_player.putExtra("playerThree Poison", playerThreePoison);
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

    //This gets called by the ResetAndSettingsFragment when "Reset" is clicked
    @Override
    public void resetTotal() {
        playerOne.resetLife();
        playerTwo.resetLife();
        playerThree.resetLife();
        playerOnePoison = "0";
        playerTwoPoison = "0";
        playerThreePoison = "0";
    }

    @Override
    public void toSettings() {

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

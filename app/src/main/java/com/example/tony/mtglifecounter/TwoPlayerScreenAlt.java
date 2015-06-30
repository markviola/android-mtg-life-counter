package com.example.tony.mtglifecounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;


public class TwoPlayerScreenAlt extends ActionBarActivity implements ResetAndSettingsFragment.resetListener,
        GestureDetector.OnGestureListener{

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    TwoPlayerPoisonFragment playerOne, playerTwo;
    String playerOneLife, playerTwoLife, playerOnePoison, playerTwoPoison;

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_screen_alt);
        getSupportActionBar().hide();

        //Dectector for swiping gesture
        this.gestureDetector = new GestureDetectorCompat(this,this);

        //References to Player One and Player Two poison counter fragments
        playerOne = (TwoPlayerPoisonFragment) getSupportFragmentManager().findFragmentById(R.id.fragment13);
        playerTwo = (TwoPlayerPoisonFragment) getSupportFragmentManager().findFragmentById(R.id.fragment14);

        //Get the pushed data
        Bundle dataBundle = getIntent().getExtras();
        playerOneLife = dataBundle.getString("playerOne Life");
        playerTwoLife = dataBundle.getString("playerTwo Life");
        playerOnePoison = dataBundle.getString("playerOne Poison");
        playerTwoPoison = dataBundle.getString("playerTwo Poison");

        //Set values of poison counter
        playerOne.setPoison(playerOnePoison);
        playerTwo.setPoison(playerTwoPoison);
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
                    Intent two_player = new Intent(getApplicationContext(), TwoPlayerScreen.class);
                    two_player.putExtra("playerOne Life", playerOneLife);
                    two_player.putExtra("playerTwo Life", playerTwoLife);
                    two_player.putExtra("playerOne Poison", playerOne.getPoison());
                    two_player.putExtra("playerTwo Poison", playerTwo.getPoison());
                    startActivity(two_player);
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

    public void resetTotal() {
        playerOne.resetPoison();
        playerTwo.resetPoison();
        playerOneLife = "20";
        playerTwoLife = "20";
    }

    //Gets rid of the issue with pressing the back button when too many life/poison activities
    @Override
    public void onBackPressed() {
        //Include the code here
        Intent back_to_main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(back_to_main);
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_two_player_screen_alt, menu);
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

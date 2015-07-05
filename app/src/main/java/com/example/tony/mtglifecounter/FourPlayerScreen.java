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


public class FourPlayerScreen extends ActionBarActivity implements ResetAndSettingsFragment.resetListener,
        ResetAndSettingsFragment.settingsListener, GestureDetector.OnGestureListener{

    private static final String TAG = "Tony message";
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    FourPlayerLifeFragment playerOne, playerTwo, playerThree, playerFour;
    DBManager dbManager;

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_player_screen);
        Log.i(TAG, "onCreate FourPlayerScreen");
        getSupportActionBar().hide();

        this.gestureDetector = new GestureDetectorCompat(this,this);

        //Allows the use of the database containing all the player information
        dbManager = new DBManager(this, null, null, 1);

        //References to Player One and Player Two fragments
        playerOne = (FourPlayerLifeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment8);
        playerTwo = (FourPlayerLifeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment9);
        playerThree = (FourPlayerLifeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment10);
        playerFour = (FourPlayerLifeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment11);

        //Use data from the MTGDatabase
        playerOne.setLife(dbManager.dbGetLife(1));
        playerTwo.setLife(dbManager.dbGetLife(2));
        playerThree.setLife(dbManager.dbGetLife(3));
        playerFour.setLife(dbManager.dbGetLife(4));
        playerOne.setName(dbManager.dbGetName(1));
        playerTwo.setName(dbManager.dbGetName(2));
        playerThree.setName(dbManager.dbGetName(3));
        playerFour.setName(dbManager.dbGetName(4));
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
                    Intent four_player = new Intent(getApplicationContext(), FourPlayerScreenAlt.class);
                    dbManager.updateLife(1, playerOne.getLife());
                    dbManager.updateLife(2, playerTwo.getLife());
                    dbManager.updateLife(3, playerThree.getLife());
                    dbManager.updateLife(4, playerFour.getLife());
                    startActivity(four_player);
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
        dbManager.updateLife(1, "20");
        dbManager.updateLife(2, "20");
        dbManager.updateLife(3, "20");
        dbManager.updateLife(4, "20");

        dbManager.updatePoison(1, "0");
        dbManager.updatePoison(2, "0");
        dbManager.updatePoison(3, "0");
        dbManager.updatePoison(4, "0");

        playerOne.setLife(dbManager.dbGetLife(1));
        playerTwo.setLife(dbManager.dbGetLife(2));
        playerThree.setLife(dbManager.dbGetLife(3));
        playerFour.setLife(dbManager.dbGetLife(4));
    }

    //This gets called by the ResetAndSettingsFragment when "Settings" is clicked
    @Override
    public void toSettings() {
        Intent settings = new Intent(getApplicationContext(), Settings.class);
        dbManager.updateLife(1, playerOne.getLife());
        dbManager.updateLife(2, playerTwo.getLife());
        dbManager.updateLife(3, playerThree.getLife());
        dbManager.updateLife(4, playerFour.getLife());
        settings.putExtra("returnTo", "FourPlayerScreen");
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
        getMenuInflater().inflate(R.menu.menu_four_player_screen, menu);
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

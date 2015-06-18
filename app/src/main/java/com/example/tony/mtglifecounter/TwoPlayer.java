package com.example.tony.mtglifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.widget.TextView;

public class TwoPlayer extends ActionBarActivity {

    private static final String TAG = "Tony message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);
        Log.i(TAG, "TwoPlayer onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "TwoPlayer onStart");
        //Hides Action Bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_two_player);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "TwoPlayer onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "TwoPlayer onPause");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_two_player, menu);
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

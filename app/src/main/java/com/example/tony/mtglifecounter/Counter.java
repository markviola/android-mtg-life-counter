package com.example.tony.mtglifecounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.widget.TextView;

public class Counter extends ActionBarActivity {

    private static final String TAG = "Tony message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String num_of_people = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
//        Log.i(TAG, "NEW onCreate");
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            num_of_people = extras.getString("number_of_people");
//        }
//        TextView the_input = (TextView)findViewById(R.id.the_input);
//        the_input.setText("QQQQ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "NEW onStop");
    }

    @Override
    protected void onStart() {
        String num_of_people = "";
        super.onStart();
        Log.i(TAG, "NEW onStart");
        //Hides Action Bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_counter);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            num_of_people = extras.getString("number_of_people");
        }
        TextView the_input = (TextView)findViewById(R.id.the_input);
        the_input.setText(num_of_people);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "NEW onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "NEW onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "NEW onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "NEW onRestoreInstanceState");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_counter, menu);
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

package com.example.tony.mtglifecounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "Tony message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
    }

    public void confirm_button(View v){
        TextView title = (TextView)findViewById(R.id.title);
        TextView num_players = (TextView)findViewById(R.id.num_players);
        String people = (String)num_players.getText();
        int int_people = (int)people.charAt(0) - 48;

        Intent intent_p2 = new Intent(getApplicationContext(),Counter.class);
        Intent intent_two = new Intent(getApplicationContext(), TwoPlayer.class);

        if (int_people == 2) {
            startActivity(intent_two);
        } else {
            intent_p2.putExtra("number_of_people", int_people);
            startActivity(intent_p2);
        }
    }

    public void plus_button(View v){
        String number = "";
        TextView num_players = (TextView)findViewById(R.id.num_players);
        int x = (int)((String)num_players.getText()).charAt(0);
        if (x < 52) {
            number += (char) (x + 1);
            num_players.setText(number);
        }
    }

    public void sub_button(View v){
        String number = "";
        TextView num_players = (TextView)findViewById(R.id.num_players);
        int x = (int)((String)num_players.getText()).charAt(0);
        if (x > 50){
            number += (char)(x - 1);
            num_players.setText(number);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

        //Hides Action Bar
        getSupportActionBar().hide();
        //setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

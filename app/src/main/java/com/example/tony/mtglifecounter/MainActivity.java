package com.example.tony.mtglifecounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends ActionBarActivity{

    private static final String TAG = "Tony message";
    TextView num_players, title;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        title = (TextView)findViewById(R.id.title);
        num_players = (TextView)findViewById(R.id.num_players);
        dbManager = new DBManager(this, null, null, 1);
        setup();
    }

    public void setup(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        Player playerThree = new Player("Player Three");
        Player playerFour = new Player("Player Four");

        dbManager.addPlayer(playerOne);
        dbManager.addPlayer(playerTwo);
        dbManager.addPlayer(playerThree);
        dbManager.addPlayer(playerFour);
    }

    public void confirm_button(View v){
        int current_players = Integer.parseInt(num_players.getText().toString());

        Intent two_player = new Intent(getApplicationContext(), TwoPlayerScreen.class);
        Intent three_player = new Intent(getApplicationContext(), ThreePlayerScreen.class);
        Intent four_player = new Intent(getApplicationContext(), FourPlayerScreen.class);

        if (current_players == 2) {
            startActivity(two_player);
        } else if (current_players == 3) {
            startActivity(three_player);
        } else if (current_players ==4){
            startActivity(four_player);
        }
    }

    public void plus_button(View v){
        int current_players = Integer.parseInt(num_players.getText().toString());
        if (current_players < 4) {
            current_players++;
            num_players.setText(String.valueOf(current_players));
        }
    }

    public void sub_button(View v){
        int current_players = Integer.parseInt(num_players.getText().toString());
        if (current_players > 2) {
            current_players--;
            num_players.setText(String.valueOf(current_players));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

        //Hides Action Bar
        getSupportActionBar().hide();
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

package com.example.tony.mtglifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.widget.TextView;


public class DefeatScreen extends ActionBarActivity {

    private static final String TAG = "Tony message";

    TextView defeatMessage;
    String[] messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defeat_screen);
        getSupportActionBar().hide();

        defeatMessage = (TextView) findViewById(R.id.defeatMessage);
        getMessages("Tony");
    }

    public void getMessages(String name) {
        messages = new String[20];

        messages[0] = "Sorry " + name + ", but you got rekt bruh";
        messages[1] = "Maybe you'll like something like Yu-gi-oh instead";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_defeat_screen, menu);
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

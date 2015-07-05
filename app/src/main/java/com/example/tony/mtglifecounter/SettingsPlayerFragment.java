package com.example.tony.mtglifecounter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsPlayerFragment extends Fragment {

    private static final String TAG = "Tony message";
    TextView player_name_text;
    EditText player_name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_player_fragment, container, false);
        player_name_text = (TextView) view.findViewById(R.id.player_name_text);
        player_name = (EditText) view.findViewById(R.id.player_name);

        return view;
    }

    public String getNewName(){
        return player_name.getText().toString();
    }

    public void setPlayerNumber(String number){ player_name_text.setText("Enter Player " + number + "'s Name:"); }
}

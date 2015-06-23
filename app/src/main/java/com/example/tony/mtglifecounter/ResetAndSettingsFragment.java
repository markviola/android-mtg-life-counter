package com.example.tony.mtglifecounter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.util.Log;
import android.app.Activity;
import android.widget.Button;


public class ResetAndSettingsFragment extends Fragment{
    private static final String TAG = "Tony message";
    Button reset, settings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reset_and_settings_fragment, container, false);
        reset = (Button) view.findViewById(R.id.reset_button);
        settings = (Button) view.findViewById(R.id.settings_button);

        reset.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        //add_action();
                    }
                }
        );

        settings.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        //sub_action();
                    }
                }
        );

        return view;
    }
}

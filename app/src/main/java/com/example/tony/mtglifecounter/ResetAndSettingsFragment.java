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

    resetListener activityCommander;

    public interface resetListener{
        public void resetTotal();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            activityCommander = (resetListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reset_and_settings_fragment, container, false);
        reset = (Button) view.findViewById(R.id.reset_button);
        settings = (Button) view.findViewById(R.id.settings_button);

        reset.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        resetClicked(v);
                    }
                }
        );

        settings.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        //some_action();
                    }
                }
        );

        return view;
    }

    //Settings button has been clicked
    public void resetClicked(View view){
        activityCommander.resetTotal();
    }
}

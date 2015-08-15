package com.example.tony.mtglifecounter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;
import android.util.Log;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class TimeService extends Service {

    private static final String TAG = "Tony message";
    private final IBinder timeBinder = new MyLocalBinder();
    boolean buttonPressed;

    public TimeService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return timeBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand method called");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i++){
                    long futureTime = System.currentTimeMillis() + 5000;
                    while (System.currentTimeMillis() < futureTime){
                        synchronized (this){
                            try {
                                wait(futureTime - System.currentTimeMillis());
                                Log.i(TAG, "Service is doing something");
                            }catch (Exception e){

                            }
                        }
                    }
                }
            }
        };

        Thread timeThread = new Thread(r);
        timeThread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy method called");
    }

    public String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("MM:mm:ss", Locale.CANADA);
        return (df.format(new Date()));
    }

    public void buttonPressed(){
        Log.i(TAG, "buttonPressed method called");
        buttonPressed = true;
    }

    public class MyLocalBinder extends Binder{
        TimeService getService(){
            return TimeService.this;
        }
    }
}

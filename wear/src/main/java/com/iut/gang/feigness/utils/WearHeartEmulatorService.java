package com.iutbm.example.iutbm.couchot.healthybody.utils;

import android.content.Intent;
import android.util.Log;

import java.util.Timer;

/**
 * Created by Victor on 10/01/2018.
 */

public class WearHeartEmulatorService extends heartService {
    public static final String TAG = "HeartEmulatorService";
    private static Timer mTimer =null ;
    @Override
    public void onCreate() {
        Log.d(TAG,"Woooooo");
        super.onCreate();
        Log.d(TAG, "onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"Woooooo");
        if (mTimer ==null) {
            mTimer = new Timer();
            long delay = 1000;
            long period = 1000;
            mTimer.scheduleAtFixedRate(new RandomHeartCounterTimerTask(this.getApplicationContext()), delay, period);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mTimer.cancel();
        mTimer = null;
        super.onDestroy();
    }
}

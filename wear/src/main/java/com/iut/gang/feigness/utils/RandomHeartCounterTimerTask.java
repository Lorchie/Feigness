package com.iutbm.example.iutbm.couchot.healthybody.utils;

import android.content.Context;
import android.content.Intent;

import java.util.TimerTask;

/**
 * Created by Victor on 10/01/2018.
 */

public class RandomHeartCounterTimerTask extends TimerTask {
    public static final String TAG="RandomStepCounterTimerTask";
    private Context ctxt =null;
    private static int heartCount;


    public RandomHeartCounterTimerTask(Context ctxt){
        this.ctxt = ctxt.getApplicationContext();
        DailyHeart.updateSteps(heartCount);
    }
    @Override
    public void run() {
        int lower = 60;
        int higher = 80;

        heartCount = (int)(Math.random() * (higher-lower)) + lower;
        DailyHeart.updateSteps(heartCount);
        Intent intent = new Intent();
        intent.setAction(stepService.STEP_COUNT_MESSAGE);
        intent.putExtra(stepService.STEP_COUNT_VALUE,DailyHeart.getHeart());
        ctxt.sendBroadcast(intent);
    }
}


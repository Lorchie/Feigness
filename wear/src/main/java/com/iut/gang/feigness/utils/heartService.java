package com.iut.gang.feigness.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import static com.google.android.gms.wearable.DataMap.TAG;

public class heartService extends Service implements SensorEventListener{
    public static final String HEART_COUT_MESSAGE = "dd";
    public static final String HEART_COUNT_VALUE = "ff";

    private SensorManager sensorManager;
     private Sensor countSensor;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStart");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_BEAT);
        sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
        return super.onStartCommand(intent, flags, startId);
    }


    public heartService(){
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged");
        if (event.sensor.getType() == Sensor.TYPE_HEART_BEAT) {
            DailyHeart.updateSteps((int)event.values[0]);
            sendStepCountUpdate();
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged");
        sendStepCountUpdate();
    }
    private void sendStepCountUpdate() {
        Intent intent = new Intent();
        intent.setAction(HEART_COUT_MESSAGE);
        intent.putExtra(HEART_COUNT_VALUE,DailyHeart.getHeart());
        sendBroadcast(intent);
    }

}

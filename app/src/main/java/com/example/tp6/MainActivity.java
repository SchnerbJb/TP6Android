package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private MyView mMyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyView = new MyView(this);
        setContentView(mMyView);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) getAccelerometer(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void getAccelerometer(SensorEvent event) {
        float x = event.values[0]; float y = event.values[1]; float z = event.values[2];
    }


    @Override
    protected void onPause() {
        super.onPause();
        mMyView.pause();
//        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyView.resume();
//        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
}
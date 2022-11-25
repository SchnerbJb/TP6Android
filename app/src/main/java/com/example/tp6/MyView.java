package com.example.tp6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyView extends SurfaceView implements Runnable, SensorEventListener {
    Paint paint;
    Thread thread= null;
    SurfaceHolder sh;
    boolean paused= true;
    Context mContext;
    private SensorManager sensorManager;


    List<Ball> balls = Collections.synchronizedList(new ArrayList<>());

    public MyView(Context context)
    {
        super(context);
        sh = getHolder();
        init(context);
    }

    public MyView(Context context, AttributeSet attrSet)
    {
        super(context, attrSet);
        sh = getHolder();
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void resume() {
        paused= false;
        thread = new Thread(this);
        thread.start();
    }
    public void pause() {
        paused= true;
        while(true) {
            try{ thread.join(); break; }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        thread = null;
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
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Random rand = new Random();
        int r = rand.nextInt(200 - 30 + 1) + 30;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: balls.add(new Ball(r, motionEvent.getX(), motionEvent.getY()));
            case MotionEvent.ACTION_UP:;
            case MotionEvent.ACTION_MOVE:;
        }
        return true;
    }

    public void run() {
        while(!paused) {
            if (!sh.getSurface().isValid()) continue;
            Canvas c = sh.lockCanvas();
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            c.drawPaint(paint);
            balls.forEach(ball -> ball.draw(c));
            sh.unlockCanvasAndPost(c);
        }
    }
}



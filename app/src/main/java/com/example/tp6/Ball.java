package com.example.tp6;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.SensorManager;

import java.util.Random;

public class Ball {

    float mRadius;
    int mColor;
    float mX, mY;
    final float gravity = 20;


    public Ball(float radius, float x, float y) {
        mRadius = radius;
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        mColor =  Color.argb(255, r, g, b);
        mX = x;
        mY = y;
    }

    public void computeGravity(Canvas c) {
        Rect r = new Rect((int) (mX - mRadius), (int) (mY - mRadius), (int) (mX + mRadius), (int) (mY + mRadius));
        if (c.getClipBounds().contains(r));
    }

    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(mColor);
        c.drawCircle(mX, mY, mRadius, paint);

    }

}

package com.example.tp6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Draw2D extends View {

    Paint paint;
    public Draw2D(Context context) {
        super(context);
        paint = new Paint();
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        c.drawPaint(paint);
        paint.setColor(Color.BLUE);
        c.drawCircle(c.getWidth() / 2, c.getHeight()/2, 50, paint);
    }
}

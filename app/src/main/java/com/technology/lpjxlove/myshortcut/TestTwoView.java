package com.technology.lpjxlove.myshortcut;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by LPJXLOVE on 2017/3/22.
 */

public class TestTwoView extends ImageView {
    private Paint mPaint;
    private int padding;
    Bitmap bitmap;
    private float x, y;
    private int directionX = 1;
    private float speech;
    private int duration;
    private double distance;

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    private int directionY = 1;

    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    private ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);

    public TestTwoView(Context context) {
        super(context);
        init();
    }

    public TestTwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        padding = (int) getResources().getDimension(R.dimen.activity_vertical_margin);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float p = animator.getAnimatedFraction();

        canvas.translate(x, y);
        canvas.translate((float) (-p * distance* Math.cos(directionX)), (float) (-p * distance * Math.sin(directionY)));
       //Toast.makeText(getContext(), ""+Math.cos(directionX), Toast.LENGTH_SHORT).show();
        canvas.rotate(p * 360);
        canvas.drawCircle(0,0,x/4,mPaint);
      //  canvas.drawBitmap(bitmap, -x / 4, -y / 4, mPaint);


        invalidate();


    }

    public void start() {



        float x =(float) Math.cos(directionX);
        float y = Math.round(Math.sin(directionY)*distance);
        Log.i("test", "start: "+x);

        animator.setDuration(500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x = w / 2;
        y = h / 2;
        bitmap = Bitmap.createScaledBitmap(bitmap, h / 4, w / 4, true);
        distance=h-w;
        Log.i("test", "onSizeChanged: "+distance);

    }
}

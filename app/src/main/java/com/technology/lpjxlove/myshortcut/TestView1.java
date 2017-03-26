package com.technology.lpjxlove.myshortcut;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pro on 17/3/24.
 */

public class TestView1 extends View {


    private Paint mPaint;
    private Paint mPaint2;
    private long mStartTime;
    private Bitmap bitmap;


    public TestView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.game_boost_anim_bg);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.WHITE);
        mStartTime = System.currentTimeMillis();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap=Bitmap.createScaledBitmap(bitmap,w,w,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long time = System.currentTimeMillis() - mStartTime;
        mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        float percent = (1 - time % 2000f / 2000f) ;
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth()/2*percent, mPaint2);
        canvas.drawBitmap(bitmap,0,0,mPaint2);
        mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        canvas.drawRect(0,0,getWidth(),getWidth(),mPaint2);
       // canvas.drawCircle(getWidth()/2,getWidth()/2,getWidth()/8,mPaint);
        invalidate();

    }
}

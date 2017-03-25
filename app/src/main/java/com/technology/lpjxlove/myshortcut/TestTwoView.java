package com.technology.lpjxlove.myshortcut;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by LPJXLOVE on 2017/3/22.
 */

public class TestTwoView extends ImageView {
    private Paint mPaint;
    private int padding;
    Bitmap bitmap;
    private float x, y;
    private int angle = 1;
    private float speech;
    private int duration;
    private float distance;
    private TestThird parent;
    private CanStartListener l;
    boolean isRunning;

    public CanStartListener getL() {
        return l;
    }

    public void setL(CanStartListener l) {
        this.l = l;
    }

    public TestThird getParentView() {
        return parent;
    }

    public void setParent(TestThird parent) {
        this.parent = parent;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    private int directionY = 1;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    private ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
    private ValueAnimator animator2 = ValueAnimator.ofFloat(0f, 1f);

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
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(getResources().getDimensionPixelOffset(R.dimen.strokwidth));
        padding = (int) getResources().getDimension(R.dimen.activity_vertical_margin);

    }








    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float p = animator.getAnimatedFraction();
        canvas.save();
        canvas.translate(x, y);
        canvas.translate((float) (p * distance* Math.cos(angle)), (float) (p * distance * Math.sin(-angle)));
        canvas.rotate((1-p) * 360);
       // canvas.drawBitmap(bitmap, -x / 16, -x / 16, mPaint);
        canvas.drawCircle(0,0,x/8,mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(x, y);
        canvas.translate((float) (p * distance* Math.cos(-angle)), (float) (p * distance * Math.sin(angle)));
        canvas.rotate(p * 360);
        canvas.drawCircle(0,0,x/4,mPaint);
      //  canvas.drawBitmap(bitmap, -x / 16, -x / 16, mPaint);
        canvas.restore();
       // canvas.drawBitmap(bitmap, -x / 16, -y / 16, mPaint);

        invalidate();


    }

    public void start(final  int position) {

        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setDuration(800);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                Random r=new Random();
                TestTwoView.this.setAngle(r.nextInt());
            }

            @Override
            public void onAnimationStart(Animator animation) {
                Random r=new Random();
                TestTwoView.this.setAngle(r.nextInt(360));
                TestTwoView.this.setDirectionY(r.nextInt(360));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
              /*  animation.start();
                isRunning=false;*/
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                /*if (animation.getAnimatedFraction()>0.5&&!isRunning){
                    l.onStart(position+1);
                    isRunning=true;
                }*/


                Log.i("test", "onAnimationUpdate: "+animation.getCurrentPlayTime());
                Log.i("test", "onAnimation: "+animation.getAnimatedFraction());
            }
        });

        ;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x = w / 2;
        y = h / 2;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bitmap = Bitmap.createScaledBitmap(bitmap, w / 8, w / 8, true);
        distance=h;
        Log.i("test", "onSizeChanged: "+distance);

    }


    public interface CanStartListener{
        void onStart(int  next);
    }


}

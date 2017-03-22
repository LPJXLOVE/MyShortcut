package com.technology.lpjxlove.myshortcut;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by LPJXLOVE on 2017/3/22.
 */

public class TestView extends ImageView {
    private Paint mPaint;
    private Paint paint;
    private float radius;
    private float padding;
    private Bitmap b;
    private float scale;
    private Bitmap bit;
    private RectF rectF;

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        //mPaint.setStrokeWidth(getResources().getDimension(R.dimen.strokwidth));
        mPaint.setColor(Color.BLUE);
        paint.setColor(Color.BLUE);
        padding=getResources().getDimension(R.dimen.activity_vertical_margin);
        bit = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        rectF=new RectF();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.set(0,0,getWidth(),getHeight());
        scale=bit.getWidth()/getWidth();
        b=Bitmap.createScaledBitmap(bit,getWidth(),getHeight(),true);
        canvas.drawBitmap(bit,null,rectF,mPaint);
        radius= getWidth()/2-padding;
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,mPaint);
        canvas.drawRect(getWidth()/4,getHeight()/4,3*getWidth()/4,3*getWidth()/4,paint);

    }
}

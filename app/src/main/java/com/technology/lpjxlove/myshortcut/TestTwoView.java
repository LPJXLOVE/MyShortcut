package com.technology.lpjxlove.myshortcut;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by LPJXLOVE on 2017/3/22.
 */

public class TestTwoView extends FrameLayout {
    private Paint mPaint;
    private int padding;
    private ViewGroup.LayoutParams l;
    public TestTwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        padding= (int) getResources().getDimension(R.dimen.activity_vertical_margin);
        ImageView iv=new ImageView(getContext());
        iv.setImageResource(R.drawable.circle_);
        ImageView i=new ImageView(getContext());
        i.setBackgroundColor(Color.GREEN);
        addView(iv);
        addView(i);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        l=getChildAt(0).getLayoutParams();
        l.height=getHeight();
        l.width=getWidth();
        View v= getChildAt(1);
        l=v.getLayoutParams();
        l.width=getWidth()/2;
        l.height=getHeight()/2;
        v.setLayoutParams(l);
        v.setLeft(getLeft()/4);
        v.setTop(getHeight()/4);
        v.setRight(getWidth()*3/4);
        v.setBottom(getWidth()*3/4);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        l=getChildAt(1).getLayoutParams();

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,mPaint);
    }
}

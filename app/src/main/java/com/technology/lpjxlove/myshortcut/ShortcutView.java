package com.technology.lpjxlove.myshortcut;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by LPJXLOVE on 2017/3/17.
 */

public class ShortcutView extends FrameLayout {
    private View v;
    private Bitmap b;
    public ShortcutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
         v = LayoutInflater.from(getContext()).inflate(R.layout.shortcut_item,this);

    }
    public Bitmap getB() {
        return b;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
       // b=convertViewToBitmap(v);
    }

    public Bitmap convertViewToBitmap(View view){

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);

        //把view中的内容绘制在画布上
        view.draw(canvas);

        return bitmap;
    }
}

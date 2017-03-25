package com.technology.lpjxlove.myshortcut;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LPJXLOVE on 2017/3/23.
 */

public class TestThird extends FrameLayout {
    private Random r;
    private List<TestTwoView> data;

    public TestThird(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        data=new ArrayList<>();
        r=new Random();
        for (int i=0;i<3;i++){
            TestTwoView t=new TestTwoView(getContext());
            t.setDirectionX(r.nextInt(360));
            t.setDirectionY(r.nextInt(360));
            addView(t);
        } for (int i=0;i<3;i++){
            TestTwoView t=new TestTwoView(getContext());
            t.setDirectionX(-(r.nextInt(360)));
            t.setDirectionY(-(r.nextInt(360)));
            addView(t);
        }
        for (int i=0;i<3;i++){
            TestTwoView t=new TestTwoView(getContext());
            t.setDirectionX((r.nextInt(360)));
            t.setDirectionY(-(r.nextInt(360)));
            addView(t);
        }
        for (int i=0;i<3;i++){
            TestTwoView t=new TestTwoView(getContext());
            t.setDirectionX(-(r.nextInt(360)));
            t.setDirectionY((r.nextInt(360)));
            addView(t);
        }






    }

    public void startAnim(){
      for (int i=0;i<12;i++){
          TestTwoView v = (TestTwoView) getChildAt(i);
          v.start();

      }

    }

}

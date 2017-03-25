package com.technology.lpjxlove.myshortcut;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LPJXLOVE on 2017/3/23.
 */

public class TestThird extends FrameLayout implements TestTwoView.CanStartListener {
    private Random r;
    private List<TestTwoView> data;
    private Handler mHandler;
    private boolean stop;
    private Runnable runnable;
    private int count=1;

    public TestThird(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        data = new ArrayList<>();
        r = new Random();
        final TestTwoView testTwoView = new TestTwoView(getContext());
        addView(testTwoView);
        for (int i = 0; i < 6; i++) {
            TestTwoView t = new TestTwoView(getContext());
            t.setAngle(r.nextInt(360));
            testTwoView.setL(this);
            data.add(t);
            addView(t);
        } /*for (int i=0;i<3;i++){
            TestTwoView t=new TestTwoView(getContext());
            t.setAngle(-(r.nextInt(360)));
            t.setDirectionY(-(r.nextInt(360)));
            addView(t);
        }
        for (int i=0;i<3;i++){
            TestTwoView t=new TestTwoView(getContext());
            t.setAngle((r.nextInt(360)));
            t.setDirectionY(-(r.nextInt(360)));
            addView(t);
        }
        for (int i=0;i<3;i++){
            TestTwoView t=new TestTwoView(getContext());
            t.setAngle(-(r.nextInt(360)));
            t.setDirectionY((r.nextInt(360)));
            addView(t);
        }*/
        mHandler = new Handler();
        runnable = new Runnable() {

            private TestTwoView circleView, circleView2, circleView3;
            private ArrayList<TestTwoView> data = new ArrayList<>();

            @Override
            public void run() {
               /* for (int i = 0; i < getChildCount(); i++) {
                    TestTwoView testTwoView1= (TestTwoView) getChildAt(i);
                    if (!testTwoView1.isRunning)
                    testTwoView1.start(1);
                }*/

                count++;
                if (count>=getChildCount()){
                    return;
                }
                TestTwoView t= (TestTwoView) getChildAt(count);

                t.start(0);
                mHandler.postDelayed(runnable, 300);
            /*    for (int i = 0; i < 3; i++) {
                    circleView = new TestTwoView(getContext());
                    circleView.setAngle(r.nextInt(360));
                    circleView.setDirectionY(r.nextInt(360));
                    addView(circleView);
                    circleView.start();
                    data.add(circleView);
                }
*/



/*
                circleView = new TestTwoView(getContext());
                circleView2 = new TestTwoView(getContext());
                circleView3 = new TestTwoView(getContext());

                circleView.setAngle(r.nextInt(360));
                circleView.setDirectionY(r.nextInt(360));
                addView(circleView);
                data.add(circleView);
                circleView.start();
                circleView2.setAngle(r.nextInt(360));
                circleView2.setDirectionY(r.nextInt(360));
                addView(circleView2);
                data.add(circleView2);
                circleView2.start();
                circleView3.setAngle(r.nextInt(360));
                circleView3.setDirectionY(r.nextInt(360));
                addView(circleView3);
                data.add(circleView3);
                circleView3.start();
                mHandler.postDelayed(runnable, 300);*/

            }
        };
    }


    public void startAnim() {
         mHandler.post(runnable);





       /* for (int i=1;i<getChildCount();i++){

            TestTwoView t = (TestTwoView) getChildAt(i);
            t.setL(this);
            data.remove(t);
            t.start(1);
        }*/


    }

    public void setStop() {
        mHandler.removeCallbacks(runnable);
    }


    @Override
    public void onStart(int next) {
        if (next == getChildCount()) {//重置为1
            next = 1;
        }
        Log.i("test", "onStart: "+next);
        TestTwoView testTwoView = (TestTwoView) getChildAt(next);
        testTwoView.start(next);

    }
}

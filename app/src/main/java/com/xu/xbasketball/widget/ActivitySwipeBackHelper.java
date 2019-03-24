package com.xu.xbasketball.widget;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by xu on 2019/03/24.
 * <p>
 * 滑动返回上一页 帮助类
 *
 * @author xu
 */
public class ActivitySwipeBackHelper {

    private AppCompatActivity mActivity;
    private float mLastPointX;
    private int mTouchSlop;
    private SwipeBackCallback callback;

    public ActivitySwipeBackHelper(AppCompatActivity activity, SwipeBackCallback callback) {
        this.mActivity = activity;
        this.callback = callback;
        mTouchSlop = ViewConfiguration.get(mActivity).getScaledTouchSlop();
    }

    public boolean processTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            mLastPointX = ev.getRawX();
        }
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                final float curPointX = ev.getRawX();
                if (Math.abs(curPointX - mLastPointX) < mTouchSlop) {
                    return false;
                } else {
//                    Log.i("Activity", "curPointX: " + curPointX);
//                    Log.i("Activity", "mLastPointX: " + mLastPointX);
                    callback.onSwipeBack();
                    return true;
                }

        }
        return false;
    }

    public interface SwipeBackCallback {
        void onSwipeBack();
    }

}

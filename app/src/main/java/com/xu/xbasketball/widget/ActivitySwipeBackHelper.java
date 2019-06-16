package com.xu.xbasketball.widget;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.xu.xbasketball.utils.SystemUtil;

/**
 * Created by xu on 2019/03/24.
 * <p>
 * 滑动返回上一页 帮助类
 *
 * @author xu
 */
public class ActivitySwipeBackHelper {

    private static final int SWIPE_STATE_ACTION_DOWN = 1;

    private AppCompatActivity mActivity;
    private float mLastPointX;
    private int mTouchSlop;
    private SwipeBackCallback callback;

    private boolean isSwipeBack = false;

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
            case MotionEvent.ACTION_DOWN:
                processSwipeBackEvent(SWIPE_STATE_ACTION_DOWN);
                break;
            case MotionEvent.ACTION_MOVE:
                final float curPointX = ev.getRawX();
                if (Math.abs(curPointX - mLastPointX) < mTouchSlop) {
                    return false;
                } else {
//                    Log.i("Activity", "curPointX: " + curPointX);
//                    Log.i("Activity", "mLastPointX: " + mLastPointX);
                    isSwipeBack = true;
                    callback.onSwipeBack();
                    return true;
                }
            default:
                isSwipeBack = false;
                break;
        }
        return false;
    }

    private void processSwipeBackEvent(int eventType) {
        switch (eventType) {
            case SWIPE_STATE_ACTION_DOWN:
                // 预处理
                SystemUtil.hideInputMethod(mActivity, mActivity.getCurrentFocus());
                break;
        }
    }

    public interface SwipeBackCallback {
        void onSwipeBack();
    }

}

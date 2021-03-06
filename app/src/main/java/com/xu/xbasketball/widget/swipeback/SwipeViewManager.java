package com.xu.xbasketball.widget.swipeback;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by xu on 2019/06/23.
 *
 * @author xu
 */
public class SwipeViewManager {

    private AppCompatActivity mCurrentActivity;
    private WeakReference<AppCompatActivity> mPreviousActivity;
    private SwipeBackActivityCallback mSwipeBackActivityCallback;

    private ViewGroup mCurrentContentView;
    private View mDisplayView;
    private View mPreviousDisplayView;

    public SwipeViewManager(AppCompatActivity currentActivity, SwipeBackActivityCallback swipeBackActivityCallback) {
        this.mCurrentActivity = currentActivity;
        this.mSwipeBackActivityCallback = swipeBackActivityCallback;
    }

    protected boolean init() {
        mCurrentContentView = mCurrentActivity.findViewById(Window.ID_ANDROID_CONTENT);
        AppCompatActivity previousActivity = mSwipeBackActivityCallback.getPreviousActivity();
        mPreviousActivity = new WeakReference<>(previousActivity);
        if (previousActivity == null) {
            return false;
        }
        mDisplayView = mCurrentContentView.getChildAt(0);

        ViewGroup previousContentView = previousActivity.findViewById(Window.ID_ANDROID_CONTENT);
        mPreviousDisplayView = previousContentView.getChildAt(0);
        if (mPreviousDisplayView == null) {
            return false;
        }

        previousContentView.removeView(mPreviousDisplayView);
        mCurrentContentView.addView(mPreviousDisplayView, 0);
        return true;
    }

    /**
     * 开始滑动
     *
     * @param x
     * @param screenWidth
     */
    protected void translate(float x, int screenWidth) {
        if (mCurrentContentView == null) {
            return;
        }
        if (mDisplayView != null) {
            mDisplayView.setX(x);
        }
    }

    /**
     * 复原原始状态
     */
    protected void recover() {
        if (mCurrentContentView == null) {
            return;
        }
        mPreviousDisplayView.setX(0);
        ViewGroup previousContentView = null;
        if (mPreviousActivity != null && mPreviousActivity.get() != null && !mPreviousActivity.get().isFinishing()) {
            previousContentView = mPreviousActivity.get().findViewById(Window.ID_ANDROID_CONTENT);
            mCurrentContentView.removeView(mPreviousDisplayView);
            previousContentView.addView(mPreviousDisplayView, 0);
        }
        mPreviousDisplayView = null;
        mCurrentContentView = null;
        mDisplayView = null;
    }

}

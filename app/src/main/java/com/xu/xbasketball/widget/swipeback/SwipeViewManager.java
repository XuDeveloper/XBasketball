package com.xu.xbasketball.widget.swipeback;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

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

    public boolean init() {
        mCurrentContentView = mCurrentActivity.findViewById(Window.ID_ANDROID_CONTENT);
        AppCompatActivity previousActivity = mSwipeBackActivityCallback.getPreviousActivity();
        if (previousActivity == null) {
            return false;
        }
        mDisplayView = mCurrentContentView.getChildAt(0);
        mPreviousDisplayView = ((ViewGroup) previousActivity.findViewById(Window.ID_ANDROID_CONTENT)).getChildAt(0);
        if (mPreviousDisplayView == null) {
            return false;
        }
        mPreviousActivity = new WeakReference<>(previousActivity);
        return false;
    }

}

package com.xu.xbasketball.widget.swipeback;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.xu.xbasketball.app.App;
import com.xu.xbasketball.utils.SystemUtil;

/**
 * Created by xu on 2019/03/24.
 * <p>
 * 滑动返回上一页 帮助类
 *
 * @author xu
 */
public class ActivitySwipeBackHelper {

    private static final String TAG = "ActivitySwipeBackHelper";

    // 点击开始
    private static final int SWIPE_STATE_ACTION_DOWN = 1;
    // 点击结束
    private static final int SWIPE_STATE_ACTION_UP = 2;
    // 开始滑动，不返回前一页面
    private static final int SWIPE_STATE_ACTION_SWIPE_START = 3;
    // 结束滑动，不返回前一页面
    private static final int SWIPE_STATE_ACTION_SWIPE_END = 4;
    // 开始滑动，返回前一页面
    private static final int SWIPE_STATE_ACTION_SWIPEBACK_START = 5;
    // 结束滑动，返回前一页面
    private static final int SWIPE_STATE_ACTION_SWIPEBACK_END = 6;

    // 滑动阈值
    private static final int SWIPE_THRESHOLD_ONE = App.SCREEN_WIDTH / 4;

    private AppCompatActivity mActivity;
    private float mLastPointX;
    private float mDistanceX; // 当前滑动距离
    private int mTouchSlop;
    private SwipeBackCallback callback;
    private SwipeViewManager swipeViewManager;
    private int screenWidth;
    private ValueAnimator mValueAnimator;

    // 默认开启滑动返回
    private boolean mEnableSwipeBack = true;
    private boolean mIsSwipeBack = false;
    // 滑动动画是否正在播放
    private boolean mSwipeAnimPlaying = false;

    public ActivitySwipeBackHelper(AppCompatActivity activity, SwipeBackCallback swipeBackCallback, SwipeBackActivityCallback swipeBackActivityCallback) {
        this.mActivity = activity;
        this.callback = swipeBackCallback;
        mTouchSlop = ViewConfiguration.get(mActivity).getScaledTouchSlop();
        swipeViewManager = new SwipeViewManager(activity, swipeBackActivityCallback);

        screenWidth = App.SCREEN_WIDTH;
    }

    public void enableSwipeBack(boolean enable) {
        if (mEnableSwipeBack == enable) {
            return;
        }
        mEnableSwipeBack = enable;
    }

    public boolean processTouchEvent(MotionEvent ev) {
        if (!mEnableSwipeBack) {
            return false;
        }
        if (mSwipeAnimPlaying) {
            return true;
        }
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            mLastPointX = ev.getRawX();
            Log.i(TAG, "processTouchEvent-ev-ACTION_DOWN-mLastPointX: " + mLastPointX);
        }
        // 获取触控点
        final int actionIndex = ev.getActionIndex();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "processTouchEvent-ev-ACTION_DOWN");
                processSwipeBackEvent(SWIPE_STATE_ACTION_DOWN);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "processTouchEvent-ev-ACTION_MOVE");
                final float curPointX = ev.getRawX();
                if (Math.abs(curPointX - mLastPointX) < mTouchSlop) {
                    return false;
                } else {
//                    Log.i("Activity", "curPointX: " + curPointX);
//                    Log.i("Activity", "mLastPointX: " + mLastPointX);
                    mIsSwipeBack = true;
//                    return true;
                }
                if (actionIndex == 0) {
                    // 第一个触控点，开始滑动
                    final float distanceX = curPointX - mLastPointX;
                    mLastPointX = curPointX;
                    Log.i(TAG, "processTouchEvent-ev-ACTION_MOVE-translateX");
                    translateX(mDistanceX + distanceX);
                    return true;
                } else {
                    return true;
                }
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "processTouchEvent-ev-ACTION_UP");
                if (mDistanceX == 0) {
                    // 没有进行滑动
                    mIsSwipeBack = false;
                    return false;
                }
                if (mIsSwipeBack && actionIndex == 0) {
                    // 取消滑动 && 抬起手势（第一手势），开始动画
                    mIsSwipeBack = false;
                    processSwipeBackEvent(SWIPE_STATE_ACTION_UP);
                }
                break;
            default:
                mIsSwipeBack = false;
                break;
        }
        return false;
    }

    private void processSwipeBackEvent(int eventType) {
        switch (eventType) {
            case SWIPE_STATE_ACTION_DOWN:
                // 预处理
                Log.i(TAG, "processTouchEvent-SWIPE_STATE_ACTION_DOWN");
                SystemUtil.hideInputMethod(mActivity, mActivity.getCurrentFocus());
                if (!swipeViewManager.init()) {
                    return;
                }
                break;
            case SWIPE_STATE_ACTION_UP:
                Log.i(TAG, "processTouchEvent-SWIPE_STATE_ACTION_UP");
                if (mDistanceX == 0) {
                    swipeViewManager.recover();
                } else if (mDistanceX < SWIPE_THRESHOLD_ONE) {
                    processSwipeBackEvent(SWIPE_STATE_ACTION_SWIPE_START);
                } else {
                    processSwipeBackEvent(SWIPE_STATE_ACTION_SWIPEBACK_START);
                }
                break;
            case SWIPE_STATE_ACTION_SWIPE_START:
                Log.i(TAG, "processTouchEvent-SWIPE_STATE_ACTION_SWIPE_START");
                startSwipeAnim();
                break;
            case SWIPE_STATE_ACTION_SWIPE_END:
                Log.i(TAG, "processTouchEvent-SWIPE_STATE_ACTION_SWIPE_END");
                mDistanceX = 0;
                mIsSwipeBack = false;
                swipeViewManager.recover();
                break;
            case SWIPE_STATE_ACTION_SWIPEBACK_START:
                Log.i(TAG, "processTouchEvent-SWIPE_STATE_ACTION_SWIPEBACK_START");
                startSwipeBackAnim();
                break;
            case SWIPE_STATE_ACTION_SWIPEBACK_END:
                Log.i(TAG, "processTouchEvent-SWIPE_STATE_ACTION_SWIPEBACK_END");
                swipeViewManager.recover();
                if (callback != null) {
                    callback.onSwipeBack();
                }
                break;
            default:
                break;
        }
    }

    private void startSwipeAnim() {
        mSwipeAnimPlaying = true;
        final int maxValue = 150;
        mValueAnimator = new ValueAnimator();
        mValueAnimator.setInterpolator(new DecelerateInterpolator(2f));
        mValueAnimator.setIntValues(0, maxValue);
        mValueAnimator.setDuration(maxValue);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                float curDistanceX = mDistanceX * (maxValue - value) / maxValue;
                translateX(curDistanceX);
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mValueAnimator.removeListener(this);
                mSwipeAnimPlaying = false;
                processSwipeBackEvent(SWIPE_STATE_ACTION_SWIPE_END);
            }
        });
        mValueAnimator.start();
    }

    private void startSwipeBackAnim() {
        mSwipeAnimPlaying = true;
        final int maxValue = 300;
        mValueAnimator = new ValueAnimator();
        mValueAnimator.setInterpolator(new DecelerateInterpolator());
        mValueAnimator.setIntValues(0, maxValue);
        mValueAnimator.setDuration(maxValue);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                float curDistanceX = mDistanceX + (screenWidth - mDistanceX) * value / maxValue;
                translateX(curDistanceX);
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animation.removeListener(this);
                mSwipeAnimPlaying = false;
                processSwipeBackEvent(SWIPE_STATE_ACTION_SWIPEBACK_END);
            }
        });
        mValueAnimator.start();
    }

    private void translateX(float x) {
        // todo 滑动退出后白屏？
        mDistanceX = x;
        mDistanceX = Math.max(0, mDistanceX);
        mDistanceX = Math.min(screenWidth, mDistanceX);
        swipeViewManager.translate(mDistanceX, screenWidth);
    }

    public interface SwipeBackCallback {
        void onSwipeBack();
    }

}

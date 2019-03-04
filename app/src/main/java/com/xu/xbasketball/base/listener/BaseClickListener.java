package com.xu.xbasketball.base.listener;

import android.view.View;

import com.xu.xbasketball.app.Constants;

/**
 * Created by Xu on 2019/3/5.
 *
 * 解决重复点击的click listener
 *
 * @author Xu
 */
public abstract class BaseClickListener implements View.OnClickListener{

    private long mLastClickTime;

    @Override
    public void onClick(View view) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime > Constants.TIME_INTERNAL) {
            // 单次点击
            onSingleClick(view);
            mLastClickTime = nowTime;
        } else {
            onFastClick(view);
        }
    }

    public abstract void onSingleClick(View view);
    public abstract void onFastClick(View view);
}

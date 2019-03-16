package com.xu.xbasketball.model.img;

import android.graphics.Bitmap;

/**
 * Created by xu on 2019/02/24.
 *
 * 加载过程监听
 *
 * @author xu
 */
public interface ILoadingImg {

    /**
     * 资源准备妥当
     * @param bitmap
     */
    void onResourceReady(Bitmap bitmap);

    /**
     * 开始加载
     */
    void onStart();

    /**
     * 资源加载失败
     */
    void onFail();

    /**
     * 资源清除
     */
    void onClear();

}

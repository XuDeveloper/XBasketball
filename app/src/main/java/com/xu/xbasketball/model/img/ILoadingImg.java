package com.xu.xbasketball.model.img;

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
     */
    void onResourceReady();

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

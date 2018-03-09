package com.xu.xbasketball.base;

/**
 * Created by Xu on 2017/11/27.
 */

public interface IBasePresenter<T extends IBaseView> {

    void attach(T view);

    void detach();
}

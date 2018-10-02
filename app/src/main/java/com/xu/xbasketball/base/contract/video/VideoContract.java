package com.xu.xbasketball.base.contract.video;

import com.xu.xbasketball.base.IBasePresenter;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.model.bean.TencentVideoBean;

import java.util.List;

/**
 * Created by xu on 2018/9/28.
 */
public interface VideoContract {

    interface View extends IBaseView {
        /**
         * 获取视频
         * @param videos
         */
        void showVideos(List<TencentVideoBean> videos);
    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 获取视频
         * @param page
         */
        void getVideos(int page);
    }

}

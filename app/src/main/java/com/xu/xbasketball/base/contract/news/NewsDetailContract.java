package com.xu.xbasketball.base.contract.news;

import com.xu.xbasketball.base.IBasePresenter;
import com.xu.xbasketball.base.IBaseView;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public interface NewsDetailContract {

    interface View extends IBaseView {
        /**
         * 显示新闻详情
         * @param
         */
        void showNewsDetail();
    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 显示新闻详情
         * @param client
         * @param nid
         */
        void getNewsDetail(String client, String nid);
    }

}

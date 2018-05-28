package com.xu.xbasketball.base.contract.news;

import com.xu.xbasketball.base.IBasePresenter;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.model.bean.TencentNewsBean;

import java.util.List;

/**
 * Created by Xu on 2018/4/10.
 *
 * @author Xu
 */
public interface NewsContract {

    interface View extends IBaseView {
        /**
         * 获取每日新闻
         * @param news
         */
        void showNews(List<TencentNewsBean> news);
    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 获取每日新闻
         * @param time
         */
        void getNews(String time);
    }

}

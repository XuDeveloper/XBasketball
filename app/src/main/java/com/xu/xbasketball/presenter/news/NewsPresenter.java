package com.xu.xbasketball.presenter.news;

import com.xu.xbasketball.base.BaseSubscriber;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.news.NewsContract;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.bean.HupuNewsBean;
import com.xu.xbasketball.utils.RxUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/3/11.
 *
 * @author Xu
 */

public class NewsPresenter extends RxPresenter<NewsContract.View> implements NewsContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public NewsPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getNews(String client) {
        addSubscribe(mDataManager.getNews(client)
            .compose(RxUtil.<List<HupuNewsBean>>rxSchedulerHelper())
            .subscribeWith(new BaseSubscriber<List<HupuNewsBean>>() {
                @Override
                public void onNext(List<HupuNewsBean> hupuNewsBeans) {
                    mView.showNews(hupuNewsBeans);
                }

                @Override
                public IBaseView getBaseView() {
                    return mView;
                }
            }));
    }
}

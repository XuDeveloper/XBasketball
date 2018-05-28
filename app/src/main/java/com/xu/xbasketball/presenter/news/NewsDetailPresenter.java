package com.xu.xbasketball.presenter.news;

import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.news.NewsDetailContract;
import com.xu.xbasketball.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public class NewsDetailPresenter extends RxPresenter<NewsDetailContract.View> implements NewsDetailContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public NewsDetailPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getNewsDetail(String client, String nid) {
//        addSubscribe(mDataManager.getNewsDetail(client, nid)
//                .compose(RxUtil.<HupuNewsDetailBean>rxSchedulerHelper())
//                .subscribeWith(new BaseSubscriber<HupuNewsDetailBean>() {
//                    @Override
//                    public void onNext(HupuNewsDetailBean hupuNewsDetailBean) {
//                        mView.showNewsDetail(hupuNewsDetailBean);
//                    }
//
//                    @Override
//                    public IBaseView getBaseView() {
//                        return mView;
//                    }
//                }));
    }

}

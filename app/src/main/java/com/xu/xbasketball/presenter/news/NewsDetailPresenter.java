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

    }

}

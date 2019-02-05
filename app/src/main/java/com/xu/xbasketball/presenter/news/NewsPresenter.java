package com.xu.xbasketball.presenter.news;

import com.xu.xbasketball.base.BaseSubscriber;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.news.NewsContract;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.bean.TencentNewsBean;
import com.xu.xbasketball.model.bean.TencentNewsResultBean;
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
    public void getNews() {
        addSubscribe(mDataManager.getNews()
            .compose(RxUtil.<TencentNewsResultBean>rxSchedulerHelper())
            .subscribeWith(new BaseSubscriber<TencentNewsResultBean>() {
                @Override
                public void onNext(TencentNewsResultBean tencentNewsResultBean) {
                    List<TencentNewsBean> news = tencentNewsResultBean.getData();
                    if (news != null) {
                        mView.showNews(news);
                    }
                }

                @Override
                public IBaseView getBaseView() {
                    return mView;
                }
            }));
    }
}

package com.xu.xbasketball.presenter.video;

import com.xu.xbasketball.base.BaseSubscriber;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.video.VideoContract;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.bean.TencentVideoResultBean;
import com.xu.xbasketball.utils.RxUtil;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/10/2.
 *
 * @author Xu
 */
public class VideoPresenter extends RxPresenter<VideoContract.View> implements VideoContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public VideoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getVideos(int page) {
        addSubscribe(mDataManager.getVideos(page)
                .compose(RxUtil.<TencentVideoResultBean>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<TencentVideoResultBean>() {
                    @Override
                    public void onNext(TencentVideoResultBean tencentVideoResultBean) {
                        if (tencentVideoResultBean != null && tencentVideoResultBean.getData() != null) {
                            mView.showVideos(tencentVideoResultBean.getData());
                        } else {
                            mView.showLoadFailMsg("暂无数据，请重试！");
                        }
                    }

                    @Override
                    public IBaseView getBaseView() {
                        return mView;
                    }
                }));
    }
}

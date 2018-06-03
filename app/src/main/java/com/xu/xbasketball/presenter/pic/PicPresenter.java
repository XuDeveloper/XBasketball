package com.xu.xbasketball.presenter.pic;

import com.xu.xbasketball.base.BaseSubscriber;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.pic.PicContract;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.bean.SinaPicResultBean;
import com.xu.xbasketball.utils.RxUtil;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/6/3.
 *
 * @author Xu
 */
public class PicPresenter extends RxPresenter<PicContract.View> implements PicContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public PicPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getPics(int page, int num) {
        addSubscribe(mDataManager.getPics(page, num)
                .compose(RxUtil.<SinaPicResultBean>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<SinaPicResultBean>() {
                    @Override
                    public void onNext(SinaPicResultBean sinaPicResultBean) {
                        if (sinaPicResultBean.getData() != null) {
                            mView.showPics(sinaPicResultBean.getData());
                        }
                    }

                    @Override
                    public IBaseView getBaseView() {
                        return mView;
                    }
                }));
    }
}

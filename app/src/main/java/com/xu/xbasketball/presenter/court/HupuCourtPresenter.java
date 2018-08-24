package com.xu.xbasketball.presenter.court;

import android.util.Log;

import com.xu.xbasketball.base.BaseSubscriber;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.court.HupuCourtContract;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.bean.HupuCourtBean;
import com.xu.xbasketball.utils.RxUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by xu on 2018/8/23.
 */
public class HupuCourtPresenter extends RxPresenter<HupuCourtContract.View> implements HupuCourtContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public HupuCourtPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getCourtArticles(int page) {
        addSubscribe(mDataManager.getCourtArticles(page)
                .compose(RxUtil.<ResponseBody>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.i("test", responseBody.toString());
//                        ArrayList<HupuCourtBean> list = new ArrayList<>();
//                        try {
//                            Document document = Jsoup.parse(responseBody.toString());
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }

                    @Override
                    public IBaseView getBaseView() {
                        return mView;
                    }
                }));
    }
}

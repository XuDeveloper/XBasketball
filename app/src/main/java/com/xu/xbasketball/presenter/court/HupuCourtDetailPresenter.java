package com.xu.xbasketball.presenter.court;

import com.xu.xbasketball.base.BaseSubscriber;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.court.HupuCourtDetailContract;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.bean.HupuCourtDetailBean;
import com.xu.xbasketball.utils.RxUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by xu on 2018/8/23.
 */
public class HupuCourtDetailPresenter extends RxPresenter<HupuCourtDetailContract.View> implements HupuCourtDetailContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public HupuCourtDetailPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getCourtArticleDetail(String detail) {
        addSubscribe(mDataManager.getCourtArticleDetail(detail)
                .compose(RxUtil.<ResponseBody>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        HupuCourtDetailBean bean = new HupuCourtDetailBean();
                        bean.setTitle("");
                        bean.setImg("");
                        try {
                            Document document = Jsoup.parse(responseBody.string());
                            Element title = document.select("h1.headline").first();
                            Element img = document.select(".detail-wrap").select(".detail-content")
                                    .select(".article-content")
                                    .select("div")
                                    .select("center")
                                    .select("center")
                                    .select("img")
                                    .first();

                            if (img != null) {
                                bean.setImg(img.attr("src"));
                            }
                            if (title != null) {
                                bean.setTitle(title.text());
                            }

                            mView.showCourtArticleDetail(bean);
                        } catch (IOException e) {
                            mView.showLoadFailMsg(e.getMessage());
                        }
                    }

                    @Override
                    public IBaseView getBaseView() {
                        return mView;
                    }
                }));
    }

}

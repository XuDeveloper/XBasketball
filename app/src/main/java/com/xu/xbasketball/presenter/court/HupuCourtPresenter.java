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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
                        ArrayList<HupuCourtBean> list = new ArrayList<>();
                        try {
                            Document document = Jsoup.parse(responseBody.string());
                            Element parent = document.select("div.common-list.news-list").select("ul").first();
                            Elements children = parent.children();
                            Log.i("test", "" + list.size());
                            for (Element e : children) {
                                Elements a = e.getElementsByTag("a");
                                String url = a.attr("href");
                                Elements h3 = e.getElementsByTag("h3");
                                String title = h3.text();
                                Element t = e.select(".news-time").first();
                                String time = t.text();
                                Element s = e.select(".news-source").first();
                                String source = s.text();

                                HupuCourtBean bean = new HupuCourtBean();
                                bean.setTitle(title);
                                bean.setUrl(url);
                                bean.setTime(time);
                                bean.setSource(source);
                                list.add(bean);
                            }
                        } catch (IOException e) {
                            mView.showLoadFailMsg(e.getMessage());
                        }
                        if (list.size() != 0) {
                            mView.showCourtArticles(list);
                        }
                    }

                    @Override
                    public IBaseView getBaseView() {
                        return mView;
                    }
                }));
    }
}

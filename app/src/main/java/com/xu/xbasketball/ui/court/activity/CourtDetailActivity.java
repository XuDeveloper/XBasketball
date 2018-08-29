package com.xu.xbasketball.ui.court.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseMVPActivity;
import com.xu.xbasketball.base.contract.court.HupuCourtDetailContract;
import com.xu.xbasketball.model.bean.HupuCourtDetailBean;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.presenter.court.HupuCourtDetailPresenter;


import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public class CourtDetailActivity extends BaseMVPActivity<HupuCourtDetailPresenter> implements HupuCourtDetailContract.View {

    @BindView(R.id.htv_court_detail_content)
    HtmlTextView htvCourtDetail;
    @BindView(R.id.loading)
    ViewStub loading;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.iv_court_detail_pic)
    ImageView ivCourtDetailPic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_court_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        String url = getIntent().getStringExtra(Constants.COURT_URL);

        String detail = url.split("/")[url.split("/").length - 1];
//        Log.i("test", "url:" + url + ", detail: " + detail);
        mPresenter.getCourtArticleDetail(detail);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void showCourtArticleDetail(HupuCourtDetailBean data) {
        clpToolbar.setTitle(data.getTitle());
        htvCourtDetail.setHtml(data.getContent(), new HtmlHttpImageGetter(htvCourtDetail));
        ImageLoader.load(this, data.getImg(), ivCourtDetailPic);
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }

    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, CourtDetailActivity.class);
        intent.putExtra(Constants.COURT_URL, url);
        context.startActivity(intent);
    }
}

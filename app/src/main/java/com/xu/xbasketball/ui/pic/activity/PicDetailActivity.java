package com.xu.xbasketball.ui.pic.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.model.img.ImageLoader;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;

/**
 * Created by Xu on 2018/9/1.
 *
 * @author Xu
 */
public class PicDetailActivity extends BaseActivity {

    @BindView(R.id.loading)
    ViewStub loading;
    @BindView(R.id.iv_pic_detail)
    ImageView ivPicDetail;
    @BindView(R.id.tb_basketball)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pic_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initData() {
        setToolBar(toolbar, "");
        loading.setVisibility(View.VISIBLE);
        String img = getIntent().getStringExtra(Constants.PIC_URL);
        if (img != null) {
            ImageLoader.load(this, img, R.mipmap.pic_placeholder, new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                    ivPicDetail.setImageBitmap(resource);
                    loading.setVisibility(View.GONE);
                }
            });
        }
    }

    public static void launch(Context context, String url, Bundle bundle) {
        Intent intent = new Intent(context, PicDetailActivity.class);
        intent.putExtra(Constants.PIC_URL, url);
        if (bundle != null) {
            context.startActivity(intent, bundle);
        } else {
            context.startActivity(intent);
        }
    }
}

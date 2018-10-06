package com.xu.xbasketball.ui.pic.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.utils.ImageUtil;
import com.xu.xbasketball.utils.SnackBarUtil;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

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

    private RxPermissions rxPermissions;
    private String imgUrl;
    private Bitmap bitmap;

    public static void launch(Context context, String url, Bundle bundle) {
        Intent intent = new Intent(context, PicDetailActivity.class);
        intent.putExtra(Constants.PIC_URL, url);
        if (bundle != null) {
            context.startActivity(intent, bundle);
        } else {
            context.startActivity(intent);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pic_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        setToolBar(toolbar, "");
        loading.setVisibility(View.VISIBLE);
        imgUrl = getIntent().getStringExtra(Constants.PIC_URL);
        if (imgUrl != null) {
            ImageLoader.load(this, imgUrl, R.mipmap.pic_placeholder, new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                    bitmap = resource;
                    ivPicDetail.setImageBitmap(resource);
                    loading.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
                save();
                break;
            case R.id.action_share:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            ImageUtil.saveBitmapToFile(mContext, imgUrl, bitmap, ivPicDetail);
                        } else {
                            SnackBarUtil.show(view, "获取权限失败，请重试！");
                        }
                    }
                });
    }
}

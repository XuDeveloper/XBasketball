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

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.model.img.ILoadingImg;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.model.img.ImgConfig;
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
            ImgConfig imgConfig = new ImgConfig(R.mipmap.pic_placeholder, R.mipmap.pic_fail_placeholder);
            ImageLoader.load(this, imgUrl, ivPicDetail, imgConfig, new ILoadingImg() {
                @Override
                public void onResourceReady(Bitmap resource) {
                    loading.setVisibility(View.GONE);
                    bitmap = resource;
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFail() {
                    loading.setVisibility(View.GONE);
                    SnackBarUtil.show(view, "加载图片失败，请重试！");
                }

                @Override
                public void onClear() {

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

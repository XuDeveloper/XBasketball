package com.xu.xbasketball.ui.video.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.model.video.VideoManager;

import butterknife.BindView;

/**
 * Created by xu on 2018/12/09.
 *
 * @author xu
 */
public class VideoDetailActivity extends BaseActivity {

    @BindView(R.id.loading)
    ViewStub loading;
    @BindView(R.id.tb_basketball)
    Toolbar toolbar;
    @BindView(R.id.video_player_video_detail)
    StandardGSYVideoPlayer videoPlayer;
    @BindView(R.id.tv_video_detail_title)
    TextView tvVideoTitle;

    private ImageView imageView;
    private String videoUrl;
    private String videoBImgUrl;
    private String title;
    private OrientationUtils orientationUtils;
    private boolean isPlay;
    private boolean isPause;

    private VideoManager videoManager;

    public static void launch(Context context, String video_url, String video_bimg_url, String title, Bundle bundle) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra(Constants.VIDEO_URL, video_url);
        intent.putExtra(Constants.VIDEO_BIMG_URL, video_bimg_url);
        intent.putExtra(Constants.VIDEO_TITLE, title);
        if (bundle != null) {
            context.startActivity(intent, bundle);
        } else {
            context.startActivity(intent);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        setToolBar(toolbar, "");
        loading.setVisibility(View.VISIBLE);
        videoUrl = getIntent().getStringExtra(Constants.VIDEO_URL);
        videoBImgUrl = getIntent().getStringExtra(Constants.VIDEO_BIMG_URL);
        title = getIntent().getStringExtra(Constants.VIDEO_TITLE);

        tvVideoTitle.setText(title);
        // 增加封面
        imageView = new ImageView(this);
        if (videoBImgUrl != null) {
            ImageLoader.load(this, videoBImgUrl, R.mipmap.pic_placeholder, new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                    imageView.setImageBitmap(resource);
                    loading.setVisibility(View.GONE);
                }
            });
        }

        // 去除自带的title和back UI
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        videoPlayer.getBackButton().setVisibility(View.GONE);

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        videoManager = new VideoManager();
        videoManager.setDefaultOption(imageView, title);
        videoManager.play(videoPlayer, videoUrl, new GSYSampleCallBack() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        }, (view, lock) -> {
            if (orientationUtils != null) {
                //配合下方的onConfigurationChanged
                orientationUtils.setEnable(!lock);
            }
        });

        videoPlayer.getFullscreenButton().setOnClickListener(view -> {
            //直接横屏
            orientationUtils.resolveByClick();
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            videoPlayer.startWindowFullscreen(VideoDetailActivity.this, true, true);
        });

    }

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        videoPlayer.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        videoPlayer.getCurrentPlayer().onVideoResume();
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            videoPlayer.getCurrentPlayer().release();
        }
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 旋转即全屏
        if (isPlay && !isPause) {
            videoPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }

}

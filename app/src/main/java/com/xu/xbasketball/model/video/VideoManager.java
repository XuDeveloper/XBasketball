package com.xu.xbasketball.model.video;

import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * Created by Xu on 2018/12/30.
 * <p>
 * 播放视频的封装类
 *
 * @author Xu
 */
public class VideoManager {

    // todo 考虑通用性，例如随时切换新的视频播放组件？

    private GSYVideoOptionBuilder gsyVideoOption;

    public void setDefaultOption(ImageView thumbImageView, String videoTitle) {
        gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setThumbImageView(thumbImageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setAutoFullWithSize(true)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setCacheWithPlay(true)
                .setVideoTitle(videoTitle);
    }

    public void play(StandardGSYVideoPlayer player, String url, GSYSampleCallBack callBack, LockClickListener lockClickListener) {
        gsyVideoOption.setUrl(url)
                .setVideoAllCallBack(callBack)
                .setLockClickListener(lockClickListener)
                .build(player);
    }

}

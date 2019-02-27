package com.xu.xbasketball.model.img;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Xu on 2019/2/27.
 *
 * 图片加载基础接口
 *
 * @author Xu
 */
public interface IBaseImgLoad {

    /**
     * 基础加载接口
     * @param context
     * @param imgUrl
     * @param imageView
     * @param imgConfig
     * @param iLoadingImg
     */
    void load(Context context, String imgUrl, ImageView imageView, ImgConfig imgConfig, ILoadingImg iLoadingImg);

    /**
     * 暂停图片加载
     * @param context
     * @param imgUrl
     */
    void pauseImgLoad(Context context, String imgUrl);

    /**
     * 恢复图片加载
     * @param context
     * @param imgUrl
     */
    void resumeImgLoad(Context context, String imgUrl);

    /**
     *
     * @param context
     * @param imageView
     * @param url
     */
    void clearImageView(Context context, ImageView imageView, String url);
}

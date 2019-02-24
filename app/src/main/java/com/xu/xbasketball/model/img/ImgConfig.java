package com.xu.xbasketball.model.img;

import android.widget.ImageView;

/**
 * Created by xu on 2019/02/23.
 *
 * @author xu
 */
public class ImgConfig {

    /**
     * 默认占位符
     */
    private int defaultRes;

    /**
     * 失败占位符
     */
    private int failRes;

    /**
     * 圆角半径
     */
    private int radius;

    /**
     * 图片展示样式
     */
    private ImageView.ScaleType scaleType;

    /**
     * 图片宽
     */
    private int width;

    /**
     * 图片高
     */
    private int height;

    public ImgConfig(int defaultRes, int failRes, int radius, ImageView.ScaleType scaleType, int width, int height) {
        this.defaultRes = defaultRes;
        this.failRes = failRes;
        this.radius = radius;
        this.scaleType = scaleType;
        this.width = width;
        this.height = height;
    }

    public ImgConfig(int defaultRes, int failRes, int radius, int width, int height) {
        this(defaultRes, failRes, radius, ImageView.ScaleType.FIT_CENTER, width, height);
    }

    public ImgConfig(int defaultRes, int failRes, int width, int height) {
        this(defaultRes, failRes, 0, ImageView.ScaleType.FIT_CENTER, width, height);
    }

    public ImgConfig(int defaultRes, int failRes, int radius) {
        this(defaultRes, failRes, radius, ImageView.ScaleType.FIT_CENTER, -1, -1);
    }

    public ImgConfig(int defaultRes, int failRes) {
        this(defaultRes, failRes, 0);
    }

    public ImgConfig(int defaultRes) {
        this(defaultRes, -1);
    }

    public int getDefaultRes() {
        return defaultRes;
    }

    public void setDefaultRes(int defaultRes) {
        this.defaultRes = defaultRes;
    }

    public int getFailRes() {
        return failRes;
    }

    public void setFailRes(int failRes) {
        this.failRes = failRes;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

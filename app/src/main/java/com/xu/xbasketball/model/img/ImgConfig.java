package com.xu.xbasketball.model.img;

import android.widget.ImageView;

/**
 * Created by xu on 2019/02/23.
 *
 * @author xu
 */
public class ImgConfig {

    /**
     * 默认/加载过程中的占位符
     */
    private int defaultPlaceholder;

    /**
     * 失败占位符
     */
    private int failPlaceholder;

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

    public ImgConfig(int defaultPlaceholder, int failPlaceholder, int radius, ImageView.ScaleType scaleType, int width, int height) {
        this.defaultPlaceholder = defaultPlaceholder;
        this.failPlaceholder = failPlaceholder;
        this.radius = radius;
        this.scaleType = scaleType;
        this.width = width;
        this.height = height;
    }

    public ImgConfig(int defaultPlaceholder, int failPlaceholder, int radius, int width, int height) {
        this(defaultPlaceholder, failPlaceholder, radius, ImageView.ScaleType.FIT_CENTER, width, height);
    }

    public ImgConfig(int defaultPlaceholder, int failPlaceholder, int width, int height) {
        this(defaultPlaceholder, failPlaceholder, 0, ImageView.ScaleType.FIT_CENTER, width, height);
    }

    public ImgConfig(int defaultPlaceholder, int failPlaceholder, int radius) {
        this(defaultPlaceholder, failPlaceholder, radius, ImageView.ScaleType.FIT_CENTER, -1, -1);
    }

    public ImgConfig(int defaultPlaceholder, int failPlaceholder) {
        this(defaultPlaceholder, failPlaceholder, 0);
    }

    public ImgConfig(int defaultPlaceholder) {
        this(defaultPlaceholder, -1);
    }

    public int getDefaultPlaceholder() {
        return defaultPlaceholder;
    }

    public void setDefaultPlaceholder(int defaultPlaceholder) {
        this.defaultPlaceholder = defaultPlaceholder;
    }

    public int getFailPlaceholder() {
        return failPlaceholder;
    }

    public void setFailPlaceholder(int failPlaceholder) {
        this.failPlaceholder = failPlaceholder;
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

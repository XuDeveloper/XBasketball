package com.xu.xbasketball.model.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * Created by xu on 2019/03/03.
 *
 * @author xu
 */
public class ImgLoadByGlide implements IBaseImgLoad {

    private static final String TAG = "ImgLoadByGlide";

    @Override
    public void load(Context context, String imgUrl, ImageView imageView, ImgConfig imgConfig, ILoadingImg iLoadingImg) {
        if (context == null || imageView == null) {
            return;
        }
        try {
            if ((imgConfig == null || imgConfig.getDefaultPlaceholder() <= 0) && TextUtils.isEmpty(imgUrl)) {
                return;
            }
            RequestOptions requestOptions = parseImgConfig(imgConfig);
            BitmapImageViewTarget imageViewTarget = new BitmapImageViewTarget(imageView) {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    super.onResourceReady(resource, transition);
                    if (iLoadingImg != null) {
                        iLoadingImg.onResourceReady();
                    }
                }

                @Override
                public void onLoadStarted(@Nullable Drawable placeholder) {
                    super.onLoadStarted(placeholder);
                    if (iLoadingImg != null) {
                        iLoadingImg.onStart();
                    }
                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    if (iLoadingImg != null) {
                        iLoadingImg.onFail();
                    }
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                    super.onLoadCleared(placeholder);
                    if (iLoadingImg != null) {
                        iLoadingImg.onClear();
                    }
                }
            };
            Glide.with(context).asBitmap().load(imgUrl).apply(requestOptions).into(imageViewTarget);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadResource(Context context, int resourceId, ImageView imageView, ImgConfig imgConfig, ILoadingImg iLoadingImg) {
        if (context == null || imageView == null) {
            return;
        }
        try {
            if ((imgConfig == null || imgConfig.getDefaultPlaceholder() <= 0) && resourceId < 0) {
                return;
            }
            RequestOptions requestOptions = parseImgConfig(imgConfig);
            BitmapImageViewTarget imageViewTarget = new BitmapImageViewTarget(imageView) {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    super.onResourceReady(resource, transition);
                    if (iLoadingImg != null) {
                        iLoadingImg.onResourceReady();
                    }
                }

                @Override
                public void onLoadStarted(@Nullable Drawable placeholder) {
                    super.onLoadStarted(placeholder);
                    if (iLoadingImg != null) {
                        iLoadingImg.onStart();
                    }
                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    if (iLoadingImg != null) {
                        iLoadingImg.onFail();
                    }
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                    super.onLoadCleared(placeholder);
                    if (iLoadingImg != null) {
                        iLoadingImg.onClear();
                    }
                }
            };
            Glide.with(context).asBitmap().load(resourceId).apply(requestOptions).into(imageViewTarget);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RequestOptions parseImgConfig(ImgConfig imgConfig) {
        RequestOptions requestOptions = new RequestOptions();
        if (imgConfig != null) {
            if (imgConfig.getDefaultPlaceholder() > 0) {
                requestOptions.placeholder(imgConfig.getDefaultPlaceholder());
            }
            if (imgConfig.getFailPlaceholder() > 0) {
                requestOptions.error(imgConfig.getFailPlaceholder());
            }
            if (imgConfig.getScaleType() != null) {
                switch (imgConfig.getScaleType()) {
                    case CENTER_CROP:
                        requestOptions.centerCrop();
                        break;
                    case FIT_CENTER:
                        requestOptions.fitCenter();
                        break;
                    default:
                        requestOptions.fitCenter();
                        break;
                }
            } else {
                requestOptions.fitCenter();
            }
            if (imgConfig.getRadius() > 0) {
                requestOptions.transform(new RoundedCorners(imgConfig.getRadius()));
            }
        }
        return requestOptions;
    }

    @Override
    public void pauseImgLoad(Context context, String imgUrl) {
        if (context != null) {
            Glide.with(context).pauseRequests();
        }
    }

    @Override
    public void pauseAllImgLoad(Context context) {
        if (context != null) {
            Glide.with(context).pauseRequests();
        }
    }

    @Override
    public void resumeImgLoad(Context context, String imgUrl) {
        if (context != null) {
            Glide.with(context).resumeRequests();
        }
    }

    @Override
    public void resumeAllImgLoad(Context context) {
        if (context != null) {
            Glide.with(context).resumeRequests();
        }
    }

    @Override
    public void clearImgLoad(Context context, ImageView imageView, String imgUrl) {
        if (context != null && imageView != null) {
            Glide.with(context).clear(imageView);
        }
    }

}

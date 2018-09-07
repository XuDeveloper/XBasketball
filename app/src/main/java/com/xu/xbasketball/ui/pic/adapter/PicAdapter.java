package com.xu.xbasketball.ui.pic.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.model.bean.SinaPicBean;
import com.xu.xbasketball.model.img.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2018/6/7.
 *
 * @author Xu
 */
public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {

    private List<SinaPicBean> list;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public PicAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
    }

    public void updateData(List<SinaPicBean> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycle_item_pic,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final SinaPicBean picBean = list.get(position);
        if (picBean == null) {
            return;
        }
        if (list.get(holder.getAdapterPosition()).getHeight() > 0) {
            ViewGroup.LayoutParams lp = holder.ivNewsPic.getLayoutParams();
            lp.height = list.get(holder.getAdapterPosition()).getHeight();
        }

        holder.ivNewsPic.setImageResource(R.mipmap.pic_placeholder);
        holder.ivNewsPic.setTag(picBean.getImg_url());
        holder.ivNewsPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });

        if (picBean.getImg_url().equals(holder.ivNewsPic.getTag())) {
            ImageLoader.load(mContext, picBean.getImg_url(), R.mipmap.pic_placeholder, new SimpleTarget<Bitmap>(App.SCREEN_WIDTH / 2, App.SCREEN_WIDTH / 2) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                        if (list.get(holder.getAdapterPosition()).getHeight() <= 0) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            int realHeight = (App.SCREEN_WIDTH / 2) * height / width;
                            list.get(holder.getAdapterPosition()).setHeight(realHeight);
                            ViewGroup.LayoutParams lp = holder.ivNewsPic.getLayoutParams();
                            lp.height = realHeight;
                        }
                    }
                    holder.ivNewsPic.setImageBitmap(resource);
                }
            });
        }
    }

    /**
     * 在StaggeredGridLayoutManager瀑布流中,当需要依据图片实际相对高度,不断动态设置ImageView的LayoutParams时,
     * 会导致快速滑动状态下产生重新排列,重写getItemViewType并设置StaggeredGridLayoutManager.GAP_HANDLING_NONE解决了这个问题，原因目前未知
     * https://github.com/oxoooo/mr-mantou-android/blob/master/app/src/main/java/ooo/oxo/mr/MainAdapter.java
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return Math.round((float) App.SCREEN_WIDTH / (float) list.get(position).getHeight() * 10f);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_pic)
        ImageView ivNewsPic;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}

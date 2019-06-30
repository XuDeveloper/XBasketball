package com.xu.xbasketball.ui.pic.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.base.listener.BaseClickListener;
import com.xu.xbasketball.model.bean.SinaPicBean;
import com.xu.xbasketball.model.img.ILoadingImg;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.model.img.ImgConfig;

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
            ViewGroup.LayoutParams lp = holder.ivPic.getLayoutParams();
            lp.height = list.get(holder.getAdapterPosition()).getHeight();
        }

        holder.ivPic.setImageResource(R.mipmap.pic_placeholder);
        // You must not call setTag() on a view Glide is targeting
        // https://stackoverflow.com/questions/34833627/error-you-must-not-call-settag-on-a-view-glide-is-targeting-when-use-glide
        holder.ivPic.setTag(R.id.glide_tag, picBean.getImg_url());
        holder.ivPic.setOnClickListener(new BaseClickListener() {
            @Override
            public void onSingleClick(View view) {
                if (onItemClickListener != null) {
                    View shareView = view.findViewById(R.id.iv_pic);
                    onItemClickListener.onItemClick(position, shareView);
                }
            }

            @Override
            public void onFastClick(View view) {

            }
        });

        if (picBean.getImg_url().equals(holder.ivPic.getTag(R.id.glide_tag))) {
            ImgConfig imgConfig = new ImgConfig(R.mipmap.pic_placeholder);
            ImageLoader.load(mContext, picBean.getImg_url(), holder.ivPic, imgConfig, new ILoadingImg() {
                @Override
                public void onResourceReady(Bitmap resource) {
                    if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                        if (list.get(holder.getAdapterPosition()).getHeight() <= 0) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            int realHeight = (App.SCREEN_WIDTH / 2) * height / width;
                            list.get(holder.getAdapterPosition()).setHeight(realHeight);
                            ViewGroup.LayoutParams lp = holder.ivPic.getLayoutParams();
                            lp.height = realHeight;
                        } else {
                            ViewGroup.LayoutParams lp = holder.ivPic.getLayoutParams();
                            lp.height = list.get(holder.getAdapterPosition()).getHeight();
                        }
                    }
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFail() {

                }

                @Override
                public void onClear() {

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
        void onItemClick(int position, View shareView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_pic)
        ImageView ivPic;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}

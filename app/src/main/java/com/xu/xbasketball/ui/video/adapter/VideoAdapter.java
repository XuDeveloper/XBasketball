package com.xu.xbasketball.ui.video.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.model.bean.TencentVideoBean;
import com.xu.xbasketball.model.img.ImageLoaderBack;
import com.xu.xbasketball.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2018/10/4.
 *
 * @author Xu
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<TencentVideoBean> list;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
    }

    public void updateData(List<TencentVideoBean> list) {
        // 去重
//        ArrayList<TencentVideoBean> noDuplicateList = new ArrayList<>();
//        HashSet<TencentVideoBean> set = new HashSet<>();
//        for (TencentVideoBean videoBean: list) {
//            if (set.add(videoBean)) {
//                noDuplicateList.add(videoBean);
//            }
//        }
//        this.list = noDuplicateList;
        this.list = list;
        Collections.sort(this.list, new Comparator<TencentVideoBean>() {
            @Override
            public int compare(TencentVideoBean o1, TencentVideoBean o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date1 = format.parse(o1.getUpdate_time());
                    Date date2 = format.parse(o2.getUpdate_time());
                    if (date1.getTime() > date2.getTime()) {
                        return -1;
                    } else if (date1.getTime() < date2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycle_item_video,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        TencentVideoBean videoBean = list.get(position);
        if (videoBean == null) {
            return;
        }
        holder.tvVideoDuration.setText(DateUtil.millisToMinute(videoBean.getDuration()));
        holder.tvVideoSource.setText(videoBean.getSource());
        holder.tvVideoTitle.setText(videoBean.getTitle());
        holder.tvVideoUpdateTime.setText(videoBean.getUpdate_time());
        holder.ivVideoBimg.setTag(videoBean.getImg());
        if (videoBean.getImg().equals(holder.ivVideoBimg.getTag())) {
            ImageLoaderBack.load(mContext, videoBean.getImg() + ".jpg", R.mipmap.pic_placeholder, new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                    if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                        if (list.get(holder.getAdapterPosition()).getImgHeight() <= 0) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            int realHeight = App.SCREEN_WIDTH * height / width;
                            list.get(holder.getAdapterPosition()).setImgHeight(realHeight);
                            ViewGroup.LayoutParams lp = holder.ivVideoBimg.getLayoutParams();
                            lp.height = realHeight;
                        } else {
                            ViewGroup.LayoutParams lp = holder.ivVideoBimg.getLayoutParams();
                            lp.height = list.get(holder.getAdapterPosition()).getImgHeight();
                        }
                    }
                    holder.ivVideoBimg.setImageBitmap(resource);
                }
            });
        }
        holder.cvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    View shareView = view.findViewById(R.id.iv_video_bimg);
                    onItemClickListener.onItemClick(position, shareView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_video_source)
        TextView tvVideoSource;

        @BindView(R.id.tv_video_title)
        TextView tvVideoTitle;

        @BindView(R.id.iv_video_bimg)
        ImageView ivVideoBimg;

        @BindView(R.id.tv_video_duration)
        TextView tvVideoDuration;

        @BindView(R.id.tv_video_update_time)
        TextView tvVideoUpdateTime;

        @BindView(R.id.cv_video)
        CardView cvVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View shareView);
    }

}

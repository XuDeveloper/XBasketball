package com.xu.xbasketball.ui.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.model.bean.HupuNewsBean;
import com.xu.xbasketball.model.bean.TencentNewsBean;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2018/4/5.
 *
 * @author Xu
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<TencentNewsBean> list;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
    }

    public void updateData(List<TencentNewsBean> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycle_item_news,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            TencentNewsBean newsBean = list.get(position);
            if (newsBean == null) {
                return;
            }
            holder.tvNewsTitle.setText(newsBean.getTitle());
            holder.tvNewsTime.setText(newsBean.getPub_time().split("T")[0]);
            ImageLoader.load(mContext, newsBean.getImage(), holder.ivNewsPic);
            holder.llNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_news_pic)
        ImageView ivNewsPic;

        @BindView(R.id.tv_news_title)
        TextView tvNewsTitle;

        @BindView(R.id.tv_news_time)
        TextView tvNewsTime;

        @BindView(R.id.ll_news)
        LinearLayout llNews;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}

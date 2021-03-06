package com.xu.xbasketball.ui.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.base.listener.BaseClickListener;
import com.xu.xbasketball.model.bean.TencentNewsBean;
import com.xu.xbasketball.model.img.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
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
        Collections.sort(this.list, new Comparator<TencentNewsBean>() {
            @Override
            public int compare(TencentNewsBean o1, TencentNewsBean o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    if (!o1.getTime().equals("") && !o2.getTime().equals("")) {
                        Date date1 = format.parse(o1.getTime());
                        Date date2 = format.parse(o2.getTime());
                        if (date1.getTime() > date2.getTime()) {
                            return -1;
                        } else if (date1.getTime() < date2.getTime()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else {
                        return 1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        // 去除"垂直化入口"条目
        Iterator<TencentNewsBean> iterator = list.iterator();
        while (iterator.hasNext()) {
            TencentNewsBean newsBean = iterator.next();
            if (newsBean.getTitle().equals("垂直化入口")) {
                iterator.remove();
            }
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycle_item_news,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        TencentNewsBean newsBean = list.get(position);
        if (newsBean == null) {
            return;
        }
        holder.tvNewsTitle.setText(newsBean.getTitle());
        holder.tvNewsTime.setText(newsBean.getTime());
        if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
            if (newsBean.getBigImage().size() > 0) {
                ImageLoader.load(mContext, newsBean.getBigImage().get(0) + ".jpg", holder.ivNewsPic);
            }
        }
        holder.llNews.setOnClickListener(new BaseClickListener() {
            @Override
            public void onSingleClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, holder.ivNewsPic, holder.llNews);
                }
            }

            @Override
            public void onFastClick(View view) {

            }
        });
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
        void onItemClick(int position, View shareImg, View shareContent);
    }

}

package com.xu.xbasketball.ui.dailyscore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.model.bean.GameBean;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Xu on 2018/4/5.
 *
 * @author Xu
 */
public class DailyscoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GameBean> list;
    private Context mContext;

    public DailyscoreAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
    }

    public void updateData(List<GameBean> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case Constants.TYPE_EMPTY:
                v = LayoutInflater.from(mContext).inflate(R.layout.recycle_item_dailyscore_nodata,
                        parent, false);
                return new EmptyViewHolder(v);
            default:
                v = LayoutInflater.from(mContext).inflate(R.layout.recycle_item_dailyscore,
                        parent, false);
                return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            GameBean gameBean = list.get(position);
            if (gameBean == null) {
                return;
            }
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tvDate.setText(DateUtil.getTodayDate());
            viewHolder.tvAwayName.setText(gameBean.getLeftName());
            viewHolder.tvAwayScore.setText(gameBean.getLeftGoal());
            if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
                ImageLoader.load(mContext, gameBean.getLeftBadge(), viewHolder.ivAwayPic);
                ImageLoader.load(mContext, gameBean.getRightBadge(), viewHolder.ivHomePic);
            }
            viewHolder.tvHomeName.setText(gameBean.getRightName());
            viewHolder.tvHomeScore.setText(gameBean.getRightGoal());
            if (mContext.getString(R.string.quarter_time_end).equals(gameBean.getQuarterTime())) {
                viewHolder.tvStatus.setText(R.string.game_end);
            } else if (mContext.getString(R.string.quarter_not_start).equals(gameBean.getQuarter())) {
                viewHolder.tvStatus.setText(R.string.game_not_start);
            } else {
                String sb = gameBean.getQuarter() +
                        " " +
                        gameBean.getQuarterTime();
                viewHolder.tvStatus.setText(sb);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getCount() == 0) {
            return Constants.TYPE_EMPTY;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return getCount() == 0 ? 1 : getCount();
    }

    private int getCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_dailyscore_date)
        TextView tvDate;

        @BindView(R.id.iv_dailyscore_away_pic)
        CircleImageView ivAwayPic;

        @BindView(R.id.tv_dailyscore_away_name)
        TextView tvAwayName;

        @BindView(R.id.tv_dailyscore_away_score)
        TextView tvAwayScore;

        @BindView(R.id.iv_dailyscore_home_pic)
        CircleImageView ivHomePic;

        @BindView(R.id.tv_dailyscore_home_name)
        TextView tvHomeName;

        @BindView(R.id.tv_dailyscore_home_score)
        TextView tvHomeScore;

        @BindView(R.id.tv_dailyscore_status)
        TextView tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

}

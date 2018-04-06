package com.xu.xbasketball.ui.dailyscore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.model.bean.GameBean;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Xu on 2018/4/5.
 *
 * @author Xu
 */
public class DailyscoreAdapter extends RecyclerView.Adapter<DailyscoreAdapter.ViewHolder> {

    private List<GameBean> list;
    private Context mContext;

    public DailyscoreAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void updateData(List<GameBean> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycle_item_dailyscore,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            GameBean gameBean = list.get(position);
            if (gameBean == null) {
                return;
            }
            holder.tvDate.setText(DateUtil.getTodayDate());
            ImageLoader.load(mContext, gameBean.getLeftBadge(), holder.ivAwayPic);
            holder.tvAwayName.setText(gameBean.getLeftName());
            holder.tvAwayScore.setText(gameBean.getLeftGoal());
            ImageLoader.load(mContext, gameBean.getRightBadge(), holder.ivHomePic);
            holder.tvHomeName.setText(gameBean.getRightName());
            holder.tvHomeScore.setText(gameBean.getRightGoal());
            if (mContext.getString(R.string.quarter_four).equals(gameBean.getQuarter())
                    && mContext.getString(R.string.quarter_time_end).equals(gameBean.getQuarterTime())) {
                holder.tvStatus.setText(R.string.game_end);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(gameBean.getQuarter());
                sb.append(" ");
                sb.append(gameBean.getQuarterTime());
                holder.tvStatus.setText(sb.toString());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
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

}

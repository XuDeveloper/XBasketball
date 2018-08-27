package com.xu.xbasketball.ui.court.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.model.bean.HupuCourtBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xu on 2018/8/5.
 *
 * @author Xu
 */
public class CourtAdapter extends RecyclerView.Adapter<CourtAdapter.ViewHolder> {

    private List<HupuCourtBean> list;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public CourtAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
    }

    public void updateData(List<HupuCourtBean> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycle_item_court,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        HupuCourtBean bean = list.get(position);
        holder.tvCourtSource.setText(bean.getSource());
        holder.tvCourtTitle.setText(bean.getTitle());
        holder.tvCourtTime.setText(bean.getTime());
        holder.llCourt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_court_source)
        TextView tvCourtSource;

        @BindView(R.id.tv_court_title)
        TextView tvCourtTitle;

        @BindView(R.id.tv_court_time)
        TextView tvCourtTime;

        @BindView(R.id.ll_court)
        LinearLayout llCourt;

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

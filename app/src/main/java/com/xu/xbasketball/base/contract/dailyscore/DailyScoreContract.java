package com.xu.xbasketball.base.contract.dailyscore;

import com.xu.xbasketball.base.IBasePresenter;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.model.bean.GameBean;

import java.util.List;

/**
 * Created by Xu on 2018/3/11.
 * @author Xu
 */

public interface DailyScoreContract {

    interface View extends IBaseView {
        /**
         * 展示每日战绩
         * @param data 战绩数据
         */
        void showDailyScore(List<GameBean> data);
    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 获取每日战绩
         * @param startTime 开始时间
         * @param endTime 结束时间
         */
        void getDailyScore(String startTime, String endTime);
    }

}

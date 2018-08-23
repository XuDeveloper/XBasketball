package com.xu.xbasketball.base.contract.court;

import com.xu.xbasketball.base.IBasePresenter;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.model.bean.HupuCourtBean;

import java.util.List;

/**
 * Created by xu on 2018/8/23.
 */
public interface HupuCourtContract {

    interface View extends IBaseView {
        void showContent(List<HupuCourtBean> data);
    }

    interface Presenter extends IBasePresenter<View> {
        void getContent(int page);
    }
}

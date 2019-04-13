package com.xu.xbasketball.base.contract.court;

import com.xu.xbasketball.base.IBasePresenter;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.model.bean.HupuCourtDetailBean;

/**
 * Created by xu on 2018/8/28.
 */
public interface HupuCourtDetailContract {

    interface View extends IBaseView {
        void showCourtArticleDetail(HupuCourtDetailBean data);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCourtArticleDetail(String detail);
    }
}

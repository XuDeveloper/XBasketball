package com.xu.xbasketball.base.contract.main;

import com.xu.xbasketball.base.IBasePresenter;
import com.xu.xbasketball.base.IBaseView;

/**
 * Created by xu on 2018/7/22.
 */
public interface SettingContract {

    interface View extends IBaseView {
//        void showUpdateDialog();
    }

    interface Presenter extends IBasePresenter<View> {
        void setNoImageState(boolean state);
        boolean getNoImageState();
//        void checkVersion(String curVersion);
    }

}

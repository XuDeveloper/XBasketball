package com.xu.xbasketball.base.contract.pic;

import com.xu.xbasketball.base.IBasePresenter;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.model.bean.SinaPicBean;

import java.util.List;

/**
 * Created by Xu on 2018/6/3.
 *
 * @author Xu
 */
public interface PicContract {

    interface View extends IBaseView {
        /**
         * 显示图片流
         * @param pics
         */
        void showPics(List<SinaPicBean> pics);
    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 获取图片流
         * @param page
         * @param num
         */
        void getPics(int page, int num);
    }

}

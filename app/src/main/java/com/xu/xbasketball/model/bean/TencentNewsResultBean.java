package com.xu.xbasketball.model.bean;

import java.util.List;

/**
 * Created by Xu on 2018/5/28.
 *
 * @author Xu
 */
public class TencentNewsResultBean {

    /**
     * newslist: List<TencentNewsBean>
     */
    private List<TencentNewsBean> newslist;

    public List<TencentNewsBean> getData() {
        return newslist;
    }

    public void setData(List<TencentNewsBean> newslist) {
        this.newslist = newslist;
    }
}

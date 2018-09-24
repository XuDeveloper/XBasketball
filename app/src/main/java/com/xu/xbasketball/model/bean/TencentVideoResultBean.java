package com.xu.xbasketball.model.bean;

import java.util.List;

/**
 * Created by xu on 2018/09/24.
 *
 * @author xu
 */
public class TencentVideoResultBean {

    /**
     * biz:
     * code:
     * data: List<TencentVideoBean>
     */
    private int biz;
    private int code;
    private List<TencentVideoBean> data;

    public int getBiz() {
        return biz;
    }

    public void setBiz(int biz) {
        this.biz = biz;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<TencentVideoBean> getData() {
        return data;
    }

    public void setData(List<TencentVideoBean> data) {
        this.data = data;
    }
}

package com.xu.xbasketball.model.bean;

import java.util.List;

/**
 * Created by Xu on 2018/6/2.
 *
 * @author Xu
 */
public class SinaPicResultBean {

    /**
     * total: Int
     * count: Int
     * data: List<SinaPicBean>
     */

    private int total;
    private int count;
    private List<SinaPicBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SinaPicBean> getData() {
        return data;
    }

    public void setData(List<SinaPicBean> data) {
        this.data = data;
    }
}

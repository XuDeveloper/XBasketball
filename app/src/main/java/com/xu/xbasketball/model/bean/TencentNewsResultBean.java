package com.xu.xbasketball.model.bean;

/**
 * Created by zhaoxuzhang on 2018/5/28.
 *
 * @author zhaoxuzhang
 */
public class TencentNewsResultBean {

    /**
     * response: TencentNewsInnerResponseBean
     * data: TencentNewsInnerDataBean
     */
    private TencentNewsInnerDataBean data;

    public TencentNewsInnerDataBean getData() {
        return data;
    }

    public void setData(TencentNewsInnerDataBean data) {
        this.data = data;
    }

}

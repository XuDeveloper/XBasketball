package com.xu.xbasketball.model.bean;

/**
 * Created by Xu on 2018/4/15.
 *
 * @author Xu
 */
public class HupuResultBean {

    /**
     * result: HupuDataBean
     * is_login
     * crt
     */

    private HupuDataBean result;

    private int is_login;

    private String crt;

    public HupuDataBean getResult() {
        return result;
    }

    public void setResult(HupuDataBean result) {
        this.result = result;
    }

    public int getIs_login() {
        return is_login;
    }

    public void setIs_login(int is_login) {
        this.is_login = is_login;
    }

    public String getCrt() {
        return crt;
    }

    public void setCrt(String crt) {
        this.crt = crt;
    }
}

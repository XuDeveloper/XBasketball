package com.xu.xbasketball.model.bean;

/**
 * Created by Xu on 2018/6/2.
 *
 * @author Xu
 */
public class SinaPicBean {

    /**
     * id: Int
     * img_url: String
     */

    private int id;
    private String img_url;
    private int height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

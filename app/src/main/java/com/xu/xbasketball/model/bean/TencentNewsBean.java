package com.xu.xbasketball.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhaoxuzhang on 2018/5/28.
 *
 * @author zhaoxuzhang
 */
public class TencentNewsBean {

    /**
     * article_id : 20180528028077
     * title : 红黑榜：新皇登基？怕是在做梦 格林必获总冠军
     * pub_time : 2018-05-28T15:28:56+08:00
     * image : http://inews.gtimg.com/newsapp_bt/0/3743784685/641
     * tags : ["詹姆斯","骑士队","凯尔特人队","NBA"]
     * abstract : 腾讯体育讯 北京时间5月28日，詹姆斯在2018年NBA季后赛东部决赛抢七大战中打满48分钟，拿到35分15篮板9助攻2盖帽，帮助骑士87比79击败凯尔特人。这是詹姆斯连续第...
     * meta : {"source":"腾讯体育","url":"http://sports.qq.com/a/20180528/028077.htm","platform":"web"}
     * site : sports
     * timestamp : 1527492536
     * url : http://sports.qq.com/a/20180528/028077.htm
     */

    private String article_id;
    private String title;
    private String pub_time;
    @SerializedName("abstract")
    private String abstractX;
    private String image;
    private int timestamp;
    private String url;
    private List<String> tags;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }

    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

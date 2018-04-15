package com.xu.xbasketball.model.bean;

/**
 * Created by Xu on 2018/4/15.
 *
 * @author Xu
 */
public class HupuNewsBean {

    /**
     * nid : 193347
     * title : [教你打球]弱侧手不会用？跟着我这样练习吧
     * summary :
     * uptime : 1523775863
     * img : https://i1.hoopchina.com.cn/blogfile/201804/04/BbsImg152283355341472_150x130big.jpg?x-oss-process=image/resize,w_250/sharpen,100/format,webp
     * type : 5
     * lights : 0
     * replies : 10
     * link : kanqiu://bbs/topic/21962774?entrance=6&fid=130
     * hid :
     * un_replay : 0
     * read : 193347
     */

    private String nid;
    private String title;
    private String uptime;
    private String img;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}

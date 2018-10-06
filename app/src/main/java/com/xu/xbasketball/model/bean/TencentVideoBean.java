package com.xu.xbasketball.model.bean;

/**
 * Created by xu on 2018/09/24.
 *
 * @author xu
 */
public class TencentVideoBean {

    /**
     * img: http://puui.qpic.cn/qqvideo_ori/0/i0716r7bhs6_496_280/0
     * title: 罚球开始罚球结束 回顾科比生涯最后一球
     * vurl: http://new.qq.com/omv/video/i0716r7bhs6
     * source: 单位看门大爷爱看球
     * duration: 22
     * update_name: 2018-10-04 14:27:08
     */

    private String img;
    private String title;
    private String vurl;
    private String source;
    private int duration;
    private String update_time;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVurl() {
        return vurl;
    }

    public void setVurl(String vurl) {
        this.vurl = vurl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}

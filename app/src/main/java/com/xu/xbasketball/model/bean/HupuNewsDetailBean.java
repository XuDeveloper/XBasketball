package com.xu.xbasketball.model.bean;

/**
 * Created by Xu on 2018/5/1.
 *
 * @author Xu
 */
public class HupuNewsDetailBean {

    /**
     * nid : 2294804
     * title : 斯奈德：我们需要克劳德继续投篮，打出侵略性
     * origin : 盐湖城论坛报
     * img_m : https://c2.hoopchina.com.cn/uploads/star/event/images/180501/bmiddle-10f8cb7313221f1abdb47e43969ce3e594c6e4b7.jpg?x-oss-process=image/resize,w_800/format,webp/quality,Q_60
     * content : <p>虎扑5月1日讯&nbsp;爵士主帅奎因-斯奈德今天接受了媒体采访，他谈到了阵中球员杰-克劳德。</p>
                 <p>爵士与火箭的第二场较量将于北京时间5月3日早上8点开打。</p>
     * addtime : 13分钟前
     * origin_url : https://www.sltrib.com/sports/jazz/2018/04/30/jae-crowders-shooting-improves-as-his-familiarity-with-jazz-offense-grows/
     */

    private String nid;
    private String title;
    private String origin;
    private String img_m;
    private String content;
    private String addtime;
    private String origin_url;

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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImg_m() {
        return img_m;
    }

    public void setImg_m(String img_m) {
        this.img_m = img_m;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getOrigin_url() {
        return origin_url;
    }

    public void setOrigin_url(String origin_url) {
        this.origin_url = origin_url;
    }
}

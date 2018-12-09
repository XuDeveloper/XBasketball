package com.xu.xbasketball.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xu on 2018/5/28.
 *
 * @author Xu
 */
public class TencentNewsBean {

    /**
     * id : NBA2018052802448400
     * uinnick : 腾讯新闻
     * uinname : qqnews
     * title : 独行侠多种方式提升球队实力 内部挖掘亦成选择
     * longtitle : 独行侠多种方式提升球队实力 内部挖掘亦成选择
     * surl : https://view.inews.qq.com/a/NBA2018052802448405?uid=280745397
     * short_url : https://view.inews.qq.com/a/NBA2018052802448405?uid=280745397
     * weiboid :
     * commentid : 2703311249
     * url : https://view.inews.qq.com/a/NBA2018052802448405?uid=280745397
     * time : 2018-05-28 13:48:58
     * timestamp : 1527487790
     * articletype : 0
     * media_id :
     * showType_video : normal
     * thumbnails_big : []
     * thumbnails : ["http://inews.gtimg.com/newsapp_ls/0/3743576988_150120/0"]
     * qishu :
     * source : 腾讯体育
     * imagecount : 0
     * comment :
     * flag : 3
     * tag : ["芬利","独行侠队","NBA"]
     * thumbnails_qqnews : ["http://inews.gtimg.com/newsapp_ls/0/3743576988_150120/0"]
     * voteId :
     * voteNum :
     * abstract :
     * pushCommentCount : 0
     * thumbnails_qqnews_photo : ["http://inews.gtimg.com/newsapp_ls/0/3743576988_640330/0"]
     * showType : three
     * show_expr : 1
     * openAds : 1
     * openAdsText : 1
     * openAdsComment : 1
     * openAdsPhotos : 0
     * adTitle :
     * gesture : 1
     * smallWindow : 1
     * openBigImage : 0
     * commentPlacementId :
     * showBigPicStyle : 0
     * bigPicStyleImg :
     * FadCid : c18woviomk0mhzx
     * disableDelete : 0
     * enableCoverGifForAuto : 0
     * enableCoverGifForNonAuto : 0
     * picShowType : 0
     * show_source : 1
     * forbidCommentUpDown : 0
     * disableDeclare : 0
     * forbidExpr : 1
     * emojiRelatedSwitch : 1
     * emojiSwitch : 1
     * isSensitive : 0
     * forbidRedPacket : 0
     * toneScore : 3
     * qualityScore : 3
     * bigImage : ["http://inews.gtimg.com/newsapp_ls/0/3743576988_640330/0"]
     * realChlName :
     * commentGifSwitch : 1
     * forbidShowCommentNum : 0
     * forbidShowReadCount : 0
     * a_ver : 05
     * alg_version : 6
     * up_labelList : []
     * labelList : [{"type":1,"color":"#ff848e98","nightColor":"#ff93989f","border":0,"displayMode":0,"focusDisplayMode":0,"word":"腾讯体育"}]
     * comments : 32
     * likeInfo : 0
     * readCount : 12083
     * reasonInfo :
     * seq_no : 4646487838602481006$$$3-3---0-0-0-0-99-99-0--
     * article_pos : 4
     * is_focused : 0
     */

    private String id;
    private String title;
    private String url;
    private String time;
    private int timestamp;
    private int readCount;
    private List<String> bigImage = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public List<String> getBigImage() {
        return bigImage;
    }

    public void setBigImage(List<String> bigImage) {
        this.bigImage = bigImage;
    }
}

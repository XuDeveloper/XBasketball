package com.xu.xbasketball.model.bean;

/**
 * 单场比赛数据
 * Created by Xu on 2018/3/9.
 */

public class GameBean {

    /**
     * matchType : 2
     * mid : 100000:1471301
     * leftId : 4
     * leftName : 公牛(客)
     * leftBadge : http://mat1.gtimg.com/sports/nba/logo/1602/4.png
     * leftGoal : 83
     * leftHasUrl : 1
     * rightId : 8
     * rightName : 活塞
     * rightBadge : http://mat1.gtimg.com/sports/nba/logo/new/8.png
     * rightGoal : 99
     * rightHasUrl : 1
     * matchDesc : NBA常规赛
     * startTime : 2018-03-10 08:00:00
     * matchPeriod : 2
     * livePeriod : 2
     * quarter : 第4节
     * quarterTime : 00:00
     * programId : 48355
     * isPay : 1
     * competitionId : 100000
     * latestNews : LatestNewBean
     */

    private String matchType;
    private String mid;
    private String leftId;
    private String leftName;
    private String leftBadge;
    private String leftGoal;
    private String leftHasUrl;
    private String rightId;
    private String rightName;
    private String rightBadge;
    private String rightGoal;
    private String rightHasUrl;
    private String matchDesc;
    private String startTime;
    private String matchPeriod;
    private String livePeriod;
    private String quarter;
    private String quarterTime;
    private String groupName;
    private String competitionId;
    private String ifHasPlayback;
    private LatestNewsBean latestNews;

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getQuarterTime() {
        return quarterTime;
    }

    public void setQuarterTime(String quarterTime) {
        this.quarterTime = quarterTime;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getLeftId() {
        return leftId;
    }

    public void setLeftId(String leftId) {
        this.leftId = leftId;
    }

    public String getLeftName() {
        return leftName;
    }

    public void setLeftName(String leftName) {
        this.leftName = leftName;
    }

    public String getLeftBadge() {
        return leftBadge;
    }

    public void setLeftBadge(String leftBadge) {
        this.leftBadge = leftBadge;
    }

    public String getLeftGoal() {
        return leftGoal;
    }

    public void setLeftGoal(String leftGoal) {
        this.leftGoal = leftGoal;
    }

    public String getLeftHasUrl() {
        return leftHasUrl;
    }

    public void setLeftHasUrl(String leftHasUrl) {
        this.leftHasUrl = leftHasUrl;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getRightBadge() {
        return rightBadge;
    }

    public void setRightBadge(String rightBadge) {
        this.rightBadge = rightBadge;
    }

    public String getRightGoal() {
        return rightGoal;
    }

    public void setRightGoal(String rightGoal) {
        this.rightGoal = rightGoal;
    }

    public String getRightHasUrl() {
        return rightHasUrl;
    }

    public void setRightHasUrl(String rightHasUrl) {
        this.rightHasUrl = rightHasUrl;
    }

    public String getMatchDesc() {
        return matchDesc;
    }

    public void setMatchDesc(String matchDesc) {
        this.matchDesc = matchDesc;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMatchPeriod() {
        return matchPeriod;
    }

    public void setMatchPeriod(String matchPeriod) {
        this.matchPeriod = matchPeriod;
    }

    public String getLivePeriod() {
        return livePeriod;
    }

    public void setLivePeriod(String livePeriod) {
        this.livePeriod = livePeriod;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getIfHasPlayback() {
        return ifHasPlayback;
    }

    public void setIfHasPlayback(String ifHasPlayback) {
        this.ifHasPlayback = ifHasPlayback;
    }

    public LatestNewsBean getLatestNews() {
        return latestNews;
    }

    public void setLatestNews(LatestNewsBean latestNews) {
        this.latestNews = latestNews;
    }


}

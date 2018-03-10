package com.xu.xbasketball.model.bean;

import java.util.List;
import java.util.Map;

/**
 * 每日所有比赛数据
 * http://matchweb.sports.qq.com/kbs/list?columnId=100000&startTime=2018-03-10&endTime=2018-03-10
 * Created by Xu on 2018/3/9.
 */

public class ScoreBoardBean {

    private int code;

    private Map<String, List<GameBean>> data;

    private String version;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, List<GameBean>> getData() {
        return data;
    }

    public void setData(Map<String, List<GameBean>> data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

package com.example.aisearch.bean.util.volunteer;

import java.util.List;

/*
 * @Author : XJC
 * @Time : 2021/4/8 8:59
 * @Description :
 *
 */
public class TeamMates {

    private List<String> names;
    private int nowNub;
    private int maxNub;

    public TeamMates(List<String> names, int nowNub, int maxNub) {
        this.names = names;
        this.nowNub = nowNub;
        this.maxNub = maxNub;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public int getNowNub() {
        return nowNub;
    }

    public void setNowNub(int nowNub) {
        this.nowNub = nowNub;
    }

    public int getMaxNub() {
        return maxNub;
    }

    public void setMaxNub(int maxNub) {
        this.maxNub = maxNub;
    }
}

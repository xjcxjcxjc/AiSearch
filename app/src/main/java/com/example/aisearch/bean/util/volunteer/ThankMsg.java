package com.example.aisearch.bean.util.volunteer;

import android.graphics.Bitmap;

/*
 * @Author : XJC
 * @Time : 2021/3/15 9:57
 * @Description :
 *
 */
public class ThankMsg {

    private Bitmap headimg;
    private String Time;
    private String name;
    private String describe;
    private boolean NewMsg;

    public ThankMsg() {
    }


    public ThankMsg(Bitmap headimg, String time, String name, String describe, boolean newMsg) {
        this.headimg = headimg;
        Time = time;
        this.name = name;
        this.describe = describe;
        NewMsg = newMsg;
    }

    public Bitmap getHeadimg() {
        return headimg;
    }

    public void setHeadimg(Bitmap headimg) {
        this.headimg = headimg;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isNewMsg() {
        return NewMsg;
    }

    public void setNewMsg(boolean newMsg) {
        NewMsg = newMsg;
    }
}

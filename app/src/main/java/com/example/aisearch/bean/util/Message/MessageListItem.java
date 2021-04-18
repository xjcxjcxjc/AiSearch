package com.example.aisearch.bean.util.Message;

import android.graphics.Bitmap;

/*
 * @Author : XJC
 * @Time : 2021/3/7 21:13
 * @Description :
 *
 */
public class MessageListItem {


    private Bitmap headimg;
    private String title;
    private String describe;
    private int newbun;
    private String time;

    public MessageListItem() {
    }

    public MessageListItem(Bitmap headimg, String title, String describe, int newbun, String time) {
        this.headimg = headimg;
        this.title = title;
        this.describe = describe;
        this.newbun = newbun;
        this.time = time;
    }

    public Bitmap getHeadimg() {
        return headimg;
    }

    public void setHeadimg(Bitmap headimg) {
        this.headimg = headimg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getNewbun() {
        return newbun;
    }

    public void setNewbun(int newbun) {
        this.newbun = newbun;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

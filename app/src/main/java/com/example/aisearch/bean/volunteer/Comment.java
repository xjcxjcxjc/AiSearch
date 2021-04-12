package com.example.aisearch.bean.volunteer;

import android.graphics.Bitmap;

/*
 * @Author : XJC
 * @Time : 2021/3/11 19:57
 * @Description :
 *
 */
public class Comment {

    private Bitmap head;
    private String name;
    private String comment;
    private String time;
    private int likenub;

    public Comment() {
    }

    public Comment(Bitmap head, String name, String comment, String time, int likenub) {
        this.head = head;
        this.name = name;
        this.comment = comment;
        this.time = time;
        this.likenub = likenub;
    }

    public Bitmap getHead() {
        return head;
    }

    public void setHead(Bitmap head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikenub() {
        return likenub;
    }

    public void setLikenub(int likenub) {
        this.likenub = likenub;
    }
}

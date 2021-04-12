package com.example.aisearch.bean;

import android.graphics.Bitmap;

/*
 * @Author : XJC
 * @Time : 2021/3/15 22:21
 * @Description :
 *
 */
public class Clue {

    private Bitmap headimg;
    private String name;
    private String clue;
    private String time;

    public Clue() {
    }

    public Clue(Bitmap headimg, String name, String clue, String time) {
        this.headimg = headimg;
        this.name = name;
        this.clue = clue;
        this.time = time;
    }

    public Bitmap getHeadimg() {
        return headimg;
    }

    public void setHeadimg(Bitmap headimg) {
        this.headimg = headimg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}



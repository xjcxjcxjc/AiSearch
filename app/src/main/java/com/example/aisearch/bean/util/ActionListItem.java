package com.example.aisearch.bean.util;

import android.graphics.Bitmap;

/*
 * @Author : XJC
 * @Time : 2021/3/7 21:40
 * @Description :
 *
 */
public class ActionListItem {


    private Bitmap img;
    private String name;
    private String location;
    private String Time;
    private String lable1;
    private String lable2;
    private String lable3;
    private int urgentgrade;
    private int addNub;

    public ActionListItem() {
    }

    public ActionListItem(Bitmap img, String name, String location, String time, String lable1, String lable2, String lable3, int urgentgrade, int addNub) {
        this.img = img;
        this.name = name;
        this.location = location;
        Time = time;
        this.lable1 = lable1;
        this.lable2 = lable2;
        this.lable3 = lable3;
        this.urgentgrade = urgentgrade;
        this.addNub = addNub;
    }

    public ActionListItem(Bitmap img, String name, String location, String time, int urgentgrade, int addNub) {
        this.img = img;
        this.name = name;
        this.location = location;
        Time = time;
        this.urgentgrade = urgentgrade;
        this.addNub = addNub;
    }


    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLable1() {
        return lable1;
    }

    public void setLable1(String lable1) {
        this.lable1 = lable1;
    }

    public String getLable2() {
        return lable2;
    }

    public void setLable2(String lable2) {
        this.lable2 = lable2;
    }

    public String getLable3() {
        return lable3;
    }

    public void setLable3(String lable3) {
        this.lable3 = lable3;
    }

    public int getUrgentgrade() {
        return urgentgrade;
    }

    public void setUrgentgrade(int urgentgrade) {
        this.urgentgrade = urgentgrade;
    }

    public int getAddNub() {
        return addNub;
    }

    public void setAddNub(int addNub) {
        this.addNub = addNub;
    }


}


package com.example.aisearch.bean.util.Message;

import android.graphics.Bitmap;

/*
 * @Author : XJC
 * @Time : 2021/3/8 20:21
 * @Description :
 *
 */
public class TeamMate {

    public enum POSITION{CAPTAIN,MATE}

    private String name;
    private Bitmap head;
    private POSITION position;
    private String phone;

    public TeamMate() {
    }

    public TeamMate(String name, Bitmap head, POSITION position, String phone) {
        this.name = name;
        this.head = head;
        this.position = position;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getHead() {
        return head;
    }

    public void setHead(Bitmap head) {
        this.head = head;
    }

    public POSITION getPosition() {
        return position;
    }

    public void setPosition(POSITION position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

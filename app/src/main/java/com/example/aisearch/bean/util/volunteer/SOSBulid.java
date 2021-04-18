package com.example.aisearch.bean.util.volunteer;

import java.io.Serializable;

/*
 * @Author : XJC
 * @Time : 2021/3/3 11:31
 * @Description : SOSActivity的cop，hospt，shelter
 *
 */
public class SOSBulid implements Serializable {

    private String name;
    private String address;
    private String phone;
    private int distance;

    public SOSBulid(String name, String address, String phone, int distance) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.distance = distance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}

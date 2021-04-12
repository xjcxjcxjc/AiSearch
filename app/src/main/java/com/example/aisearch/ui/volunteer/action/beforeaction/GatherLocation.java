package com.example.aisearch.ui.volunteer.action.beforeaction;

import com.baidu.mapapi.model.LatLng;

public class GatherLocation {
    private String name;
    private double miles;
    private String address;
    private String other;
    private LatLng latLng;



    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public GatherLocation(String name, double miles, String address, String other, LatLng latLng) {
        this.name = name;
        this.miles = miles;
        this.address = address;
        this.other = other;
        this.latLng = latLng;
    }

    public GatherLocation() {
    }
}

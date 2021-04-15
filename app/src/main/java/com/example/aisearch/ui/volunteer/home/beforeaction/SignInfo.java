package com.example.aisearch.ui.volunteer.home.beforeaction;

public class SignInfo {
    private int id;
    private String name;
    private String traffic;
    private String equip;
    private String status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SignInfo(int id, String name, String traffic, String equip, String status) {
        this.id = id;
        this.name = name;
        this.traffic = traffic;
        this.equip = equip;
        this.status = status;
    }

    public SignInfo() {
    }
}

package com.example.aisearch.bean.util.volunteer;

import java.io.Serializable;

/*
 * @Author : XJC
 * @Time : 2021/2/22 16:42
 * @Description :
 *
 */
public class Video implements Serializable {

    private String title;
    private String category;
    private String Describe;
    private int hard;
    private int studynub;
    private int requiregrade;

    private String course1;
    private String course2;
    private String course3;
    private String course4;
    private String course5;
    private String course6;
    private String course7;
    private String nowcourse7;



    private String studyprogress;
    private String lastTime;
    private String lasttitle;


    public Video(String title, String category, String describe, int hard, int studynub, int requiregrade,
                 String studyprogress, String lastTime, String lasttitle,
                 String course1, String course2, String course3, String course4, String course5,
                 String course6, String course7,String nowcourse7) {
        this.title = title;
        this.category = category;
        Describe = describe;
        this.hard = hard;
        this.studynub = studynub;
        this.requiregrade = requiregrade;
        this.studyprogress = studyprogress;
        this.lastTime = lastTime;
        this.lasttitle = lasttitle;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.course5 = course5;
        this.course6 = course6;
        this.course7 = course7;
        this.nowcourse7=nowcourse7;
    }


    public Video( ) {
    }

    public int getRequiregrade() {
        return requiregrade;
    }

    public void setRequiregrade(int requiregrade) {
        this.requiregrade = requiregrade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public int getHard() {
        return hard;
    }

    public void setHard(int hard) {
        this.hard = hard;
    }

    public int getStudynub() {
        return studynub;
    }

    public void setStudynub(int studynub) {
        this.studynub = studynub;
    }

    public String getStudyprogress() {
        return studyprogress;
    }

    public void setStudyprogress(String studyprogress) {
        this.studyprogress = studyprogress;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLasttitle() {
        return lasttitle;
    }

    public void setLasttitle(String lasttitle) {
        this.lasttitle = lasttitle;
    }


    public String getCourse1() {
        return course1;
    }

    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    public String getCourse2() {
        return course2;
    }

    public void setCourse2(String course2) {
        this.course2 = course2;
    }

    public String getCourse3() {
        return course3;
    }

    public void setCourse3(String course3) {
        this.course3 = course3;
    }

    public String getCourse4() {
        return course4;
    }

    public void setCourse4(String course4) {
        this.course4 = course4;
    }

    public String getCourse5() {
        return course5;
    }

    public void setCourse5(String course5) {
        this.course5 = course5;
    }

    public String getCourse6() {
        return course6;
    }

    public void setCourse6(String course6) {
        this.course6 = course6;
    }

    public String getCourse7() {
        return course7;
    }

    public void setCourse7(String course7) {
        this.course7 = course7;
    }

    public String getNowcourse7() {
        return nowcourse7;
    }

    public void setNowcourse7(String nowcourse7) {
        this.nowcourse7 = nowcourse7;
    }
}

package com.example.aisearch.bean.util.volunteer;

/*
 * @Author : XJC
 * @Time : 2021/3/25 8:59
 * @Description :
 *
 */
public class Subject_Single {

    private String title;
    private String choose_A;
    private String choose_B;
    private String choose_C;
    private String choose_D;

    /**
     * 四个选项的话 ，rang就是 0,1,2,3
     */
    private int rightkey;

    public Subject_Single(String title, String choose_A, String choose_B, String choose_C, String choose_D, int rightkey) {
        this.title = title;
        this.choose_A = choose_A;
        this.choose_B = choose_B;
        this.choose_C = choose_C;
        this.choose_D = choose_D;
        this.rightkey = rightkey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChoose_A() {
        return choose_A;
    }

    public void setChoose_A(String choose_A) {
        this.choose_A = choose_A;
    }

    public String getChoose_B() {
        return choose_B;
    }

    public void setChoose_B(String choose_B) {
        this.choose_B = choose_B;
    }

    public String getChoose_C() {
        return choose_C;
    }

    public void setChoose_C(String choose_C) {
        this.choose_C = choose_C;
    }

    public String getChoose_D() {
        return choose_D;
    }

    public void setChoose_D(String choose_D) {
        this.choose_D = choose_D;
    }

    public int getRightkey() {
        return rightkey;
    }

    public void setRightkey(int rightkey) {
        this.rightkey = rightkey;
    }
}

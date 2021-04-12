package com.example.aisearch.bean.family;

/*
 * @Author : XJC
 * @Time : 2021/2/26 13:51
 * @Description :
 *
 */
public class ActionStatus {

    public static int LISTTYPE_EXAMINE =214;
    public static int LISTTYPE_ACTION =215;
    public static int LISTTYPE_END =216;

    private int type;

    public ActionStatus(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

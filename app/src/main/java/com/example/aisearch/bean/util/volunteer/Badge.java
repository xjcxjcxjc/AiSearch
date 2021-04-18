package com.example.aisearch.bean.util.volunteer;

import android.graphics.Bitmap;

/*
 * @Author : XJC
 * @Time : 2021/2/24 16:26
 * @Description :
 *
 */
public class Badge {

    private Bitmap bitmap;
    private String title;
    private String title2;


    public Badge(Bitmap bitmap, String title) {
        this.bitmap = bitmap;
        this.title = title;
    }

    public Badge(String title, String title2) {
        this.title = title;
        this.title2 = title2;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }
}

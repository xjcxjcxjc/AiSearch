package com.example.aisearch.bean.volunteer;

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

    public Badge(Bitmap bitmap, String title) {
        this.bitmap = bitmap;
        this.title = title;
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
}

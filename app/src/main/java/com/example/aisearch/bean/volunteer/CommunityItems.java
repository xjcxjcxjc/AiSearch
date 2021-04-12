package com.example.aisearch.bean.volunteer;

import android.graphics.Bitmap;

/*
 * @Author : XJC
 * @Time : 2021/3/9 22:39
 * @Description :
 *
 */
public class CommunityItems{

    public enum TYPE{FIND,CITY};
    public enum GROUP {COMMEND,EXPERIENCE,STORY, KNOWLEDGE,GOODS,PRACTICE};


    private Bitmap headimg;
    private Bitmap coverimg;

    /**
     * 0表示未点赞，1
     */
    private int like;
    private String name;
    private String title;
    private String likeNub;
    private String collectionNub;
    private String commentNub;

    public CommunityItems() {
    }

    public CommunityItems(Bitmap headimg, Bitmap coverimg, int like,
                String name, String title, String likeNub,
                String collectionNub, String commentNub) {
        this.headimg = headimg;
        this.coverimg = coverimg;
        this.like = like;
        this.name = name;
        this.title = title;
        this.likeNub = likeNub;
        this.collectionNub = collectionNub;
        this.commentNub = commentNub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLikeNub() {
        return likeNub;
    }

    public void setLikeNub(String likeNub) {
        this.likeNub = likeNub;
    }

    public String getCollectionNub() {
        return collectionNub;
    }

    public void setCollectionNub(String collectionNub) {
        this.collectionNub = collectionNub;
    }

    public String getCommentNub() {
        return commentNub;
    }

    public void setCommentNub(String commentNub) {
        this.commentNub = commentNub;
    }

    public Bitmap getHeadimg() {
        return headimg;
    }

    public void setHeadimg(Bitmap headimg) {
        this.headimg = headimg;
    }

    public Bitmap getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(Bitmap coverimg) {
        this.coverimg = coverimg;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

}

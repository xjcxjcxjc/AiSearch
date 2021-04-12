package com.example.aisearch.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/*
 * @Author : XJC
 * @Time : 2021/3/9 20:18
 * @Description :
 *
 */
public class Person implements Parcelable {

    private Bitmap img;
    private String name;
    private String sex;
    private String age;
    private String features;
    private String features1;
    private String features2;
    private String features3;
    private String clothes;
    private String clothes1;
    private String clothes2;
    private String time;
    private String location;
    private String birth;
    private String from;
    private String high;
    private String type;
    private String describe;

    private String nub;

    /**
     * 这里的读的顺序必须与writeToParcel(Parcel dest, int flags)方法中
     * 写的顺序一致，否则数据会有差错，比如你的读取顺序如果是：
     * nickname = source.readString();
     * username=source.readString();
     * age = source.readInt();
     * 即调换了username和nickname的读取顺序，那么你会发现你拿到的username是nickname的数据，
     * 而你拿到的nickname是username的数据
     * @param source
     */
    public Person(Parcel source) {

//        img=source.readParcelable(Bitmap.class.getClassLoader());
        name=source.readString();
        sex=source.readString();
        age=source.readString();
        features=source.readString();
        features1=source.readString();
        features2=source.readString();
        features3=source.readString();
        clothes=source.readString();
        clothes1=source.readString();
        clothes2=source.readString();
        time=source.readString();
        location=source.readString();
        birth=source.readString();
        from=source.readString();
        high=source.readString();
        type=source.readString();
        describe=source.readString();
        nub=source.readString();
    }


    public Person(Bitmap img, String name, String sex, String age, String features, String features1, String features2,
                  String features3, String clothes, String clothes1, String clothes2,
                  String time, String location, String birth, String from,
                  String high, String type, String describe) {

        this.img = img;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.features = features;
        this.features1 = features1;
        this.features2 = features2;
        this.features3 = features3;
        this.clothes = clothes;
        this.clothes1 = clothes1;
        this.clothes2 = clothes2;
        this.time = time;
        this.location = location;
        this.birth = birth;
        this.from = from;
        this.high = high;
        this.type = type;
        this.describe = describe;
    }

    public Person(Bitmap img, String name, String sex, String age, String features, String features1, String features2,
                  String features3, String clothes, String clothes1, String clothes2,
                  String time, String location, String birth, String from,
                  String high, String type, String describe,String nub) {
        this.img = img;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.features = features;
        this.features1 = features1;
        this.features2 = features2;
        this.features3 = features3;
        this.clothes = clothes;
        this.clothes1 = clothes1;
        this.clothes2 = clothes2;
        this.time = time;
        this.location = location;
        this.birth = birth;
        this.from = from;
        this.high = high;
        this.type = type;
        this.describe = describe;
        this.nub=nub;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeParcelable(img,0);
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeString(age);
        dest.writeString(features);
        dest.writeString(features1);
        dest.writeString(features2);
        dest.writeString(features3);
        dest.writeString(clothes);
        dest.writeString(clothes1);
        dest.writeString(clothes2);
        dest.writeString(time);
        dest.writeString(location);
        dest.writeString(birth);
        dest.writeString(from);
        dest.writeString(high);
        dest.writeString(type);
        dest.writeString(describe);
        dest.writeString(nub);

    }
    public static final Creator<Person> CREATOR = new Creator<Person>() {

        /**
         * 供外部类反序列化本类数组使用
         */
        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }

        /**
         * 从Parcel中读取数据
         */
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }


    };

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFeatures1() {
        return features1;
    }

    public void setFeatures1(String features1) {
        this.features1 = features1;
    }

    public String getFeatures2() {
        return features2;
    }

    public void setFeatures2(String features2) {
        this.features2 = features2;
    }

    public String getClothes1() {
        return clothes1;
    }

    public void setClothes1(String clothes1) {
        this.clothes1 = clothes1;
    }

    public String getClothes2() {
        return clothes2;
    }

    public void setClothes2(String clothes2) {
        this.clothes2 = clothes2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFeatures3() {
        return features3;
    }

    public void setFeatures3(String features3) {
        this.features3 = features3;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getClothes() {
        return clothes;
    }

    public void setClothes(String clothes) {
        this.clothes = clothes;
    }

    public String getNub() {
        return nub;
    }

    public void setNub(String nub) {
        this.nub = nub;
    }
}


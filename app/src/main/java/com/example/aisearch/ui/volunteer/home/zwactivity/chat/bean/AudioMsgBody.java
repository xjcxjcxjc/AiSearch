package com.example.aisearch.ui.volunteer.home.zwactivity.chat.bean;



public class AudioMsgBody extends FileMsgBody {
    //语音消息长度,单位：秒。
    private long duration;
    private String name2;

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


}

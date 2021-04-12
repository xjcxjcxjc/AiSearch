package com.example.aisearch;

import android.content.SharedPreferences;

import com.example.aisearch.base.BaseActivity;

public class LodingActivity extends BaseActivity {

    public static int IDENTIFY_FML=11;
    public static int IDENTIFY_VLT=12;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_loding;
    }

    @Override
    protected void init() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                initidentity();
//            }
//        }, 1000);
        initidentity();
    }

    @Override
    protected void initBtn() {

    }


    private void initidentity(){
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);   //私自模式，只允许本应用访问
        editor =sharedPreferences.edit();

        int identity= sharedPreferences.getInt("identity",0);
        if (0==identity){
            editor.putInt("identity",IDENTIFY_VLT);
            editor.commit();
            startActivity(MainActivity.class,true);
        }else if(identity==IDENTIFY_VLT){
            startActivity(MainActivity.class,true);
        }else if(identity==IDENTIFY_FML){
            startActivity(MainActivity2.class,true);
        }
//        editor.putString("identity","fml");
//        editor.commit();

    }



}
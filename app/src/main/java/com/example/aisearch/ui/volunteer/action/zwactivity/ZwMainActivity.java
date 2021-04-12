package com.example.aisearch.ui.volunteer.action.zwactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aisearch.R;
import com.example.aisearch.ui.volunteer.action.zwactivity.chat.SplashActivity;

public class ZwMainActivity extends AppCompatActivity {
    private Button btnQiandao;
    private Button btnPeople;
    private Button btnShangBao;;
    private Button btnRenlianShiBie;
    private Button btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //体态识别
        btnPeople = findViewById(R.id.btn_person_re_identification);
        btnPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PersonReIdentificationActivity.class));
            }
        });

        //签到
        btnQiandao = findViewById(R.id.btn_face_qiandao);
        btnQiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChooseFunctionActivity.class));
            }
        });


        //人脸识别
        btnRenlianShiBie = findViewById(R.id.btn_face_shibie);
        btnRenlianShiBie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChooseFunctionActivity2.class));
            }
        });

        //上报
        btnShangBao = findViewById(R.id.btn_shangbao);
        btnShangBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchPeopleByImageActivity.class));
            }
        });


        //聊天
        btnChat = findViewById(R.id.btn_chat);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            }
        });
    }
}
package com.example.aisearch.ui.volunteer.publish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aisearch.R;

public class MainActivity extends AppCompatActivity {

    private  Button mBtnPublish,mBtnScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_main3);
        mBtnPublish = findViewById(R.id.btn_publish);
        mBtnScreen = findViewById(R.id.btn_screen);
        mBtnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PublishActivity.class);
                startActivity(intent);
            }
        });
        mBtnScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(MainActivity.this,ScreenActivity.class));
            }
        });

    }
}
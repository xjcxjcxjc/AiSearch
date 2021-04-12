package com.example.aisearch.ui.family.take_a_hand.share;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aisearch.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class ShareActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share1);



        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(ShareActivity1.this);



        Button submit=findViewById(R.id.submit);
        submit.setOnClickListener(v -> {
            startActivity(new Intent(ShareActivity1.this,ShareActivity2.class));
            finish();
        });
    }




}
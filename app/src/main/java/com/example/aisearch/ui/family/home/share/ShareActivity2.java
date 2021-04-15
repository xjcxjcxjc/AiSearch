package com.example.aisearch.ui.family.home.share;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aisearch.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class ShareActivity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share1);

        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarDarkMode(ShareActivity2.this);



        ImageView img=findViewById(R.id.back);
        img.setImageResource(R.mipmap.background71);
//        img.setImageResource(R.mipmap.pyq_share2);
        img.setOnClickListener(v -> {
            startActivity(new Intent(ShareActivity2.this,ShareActivity3.class));
        });

    }


}
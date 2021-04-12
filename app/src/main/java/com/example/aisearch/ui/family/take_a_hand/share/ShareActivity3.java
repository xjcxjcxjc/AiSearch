package com.example.aisearch.ui.family.take_a_hand.share;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aisearch.R;
import com.example.aisearch.ui.volunteer.publish.ScreenActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareActivity3 extends AppCompatActivity {

    @BindView(R.id.submit2)
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share1);

        ButterKnife.bind(this);
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(ShareActivity3.this);

        ImageView img=findViewById(R.id.back);
        img.setImageResource(R.mipmap.background64);


        submit.setOnClickListener(v -> {
            Intent intent=new Intent(ShareActivity3.this, ScreenActivity.class);
            intent.putExtra("see","yes");

            startActivity(intent);
        });

    }




}
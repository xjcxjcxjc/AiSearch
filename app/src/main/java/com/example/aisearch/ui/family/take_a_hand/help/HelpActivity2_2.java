package com.example.aisearch.ui.family.take_a_hand.help;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.volunteer.practice.dialog.LockDialog;
import com.example.aisearch.util.TakePhotoUtils;

import butterknife.BindView;

public class HelpActivity2_2 extends BaseActivity {

    @BindView(R.id.help_view1card4)
    CardView help_view1card4;

    @BindView(R.id.finish)
    Button finish;
    @BindView(R.id.help_photo)
    ImageView help_photo;
    @BindView(R.id.help_takephoto)
    RelativeLayout help_takephoto;
    @BindView(R.id.help2_chlicktext)
    TextView help2_chlicktext;

    @BindView(R.id.help2_title)
    TextView  help2_title;
    TakePhotoUtils takePhotoUtils;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_help2_2;
    }

    @Override
    protected void init() {
        initLogic();
        takePhotoUtils=new TakePhotoUtils(this);
        help_takephoto.setOnClickListener(takePhotoUtils);
    }

    @Override
    protected void initBtn() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap=takePhotoUtils.PhotoActivityResult(requestCode,resultCode,data);
        help_photo.setImageBitmap(bitmap);
    }


    /**
     * 如果是寻人启事就改一下逻辑代码
     */
    private void initLogic(){

        String id=getIntent().getStringExtra("type");
        if (null!=id&&"releaseSearch".equals(id)){
            help2_chlicktext.setText("发布");
            help2_title.setText("发布寻人启事");
            help_view1card4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new LockDialog(HelpActivity2_2.this,()->{
                        finish();
                    },"您的寻人启事提交").show();
                }
            });
        }else{
            help_view1card4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(HelpActivity4.class,true);
                }
            });
        }

    }


}
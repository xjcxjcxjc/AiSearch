package com.example.aisearch.ui.volunteer.train.video;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.volunteer.train.adapter.ProgressBarAdapter;
import com.example.aisearch.ui.volunteer.train.dialog.SubmitDialog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class VideoTestActivity extends BaseActivity {


    @Override
    public int getLayoutRes() {
        return R.layout.activity_video_test;
    }

    @Override
    protected void init() {

        ImageView practicetest_backimg=findViewById(R.id.practicetest_backimg);
        practicetest_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        initRecycleView();
    }

    @Override
    protected void initBtn() {
        CardView videotest_submitcard=findViewById(R.id.videotest_submitcard);
        videotest_submitcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SubmitDialog(VideoTestActivity.this, new SubmitDialog.ClickCallBack() {
                    @Override
                    public void onStop() {
                        finish();
                    }
                }).show();
            }
        });
    }

    private void initRecycleView() {
        RecyclerView progressbar=findViewById(R.id.video_test_barrecycle);
        progressbar.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        progressbar.setAdapter(new ProgressBarAdapter(VideoTestActivity.this));
    }
}
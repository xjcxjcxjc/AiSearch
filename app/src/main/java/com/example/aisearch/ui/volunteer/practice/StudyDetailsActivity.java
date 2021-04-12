package com.example.aisearch.ui.volunteer.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.bean.volunteer.Video;
import com.example.aisearch.ui.volunteer.practice.adapter.ClassItemAdapter;
import com.example.aisearch.ui.volunteer.practice.adapter.PracticeIndexAdapter;
import com.example.aisearch.ui.volunteer.practice.dialog.AppratusDialog;
import com.example.aisearch.ui.volunteer.practice.video.VideoActivity;
import com.example.aisearch.util.UiUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudyDetailsActivity extends VideoActivity implements View.OnClickListener {

    @BindView(R.id.studydetail_recycle)
    RecyclerView recyclerView;
    @BindView(R.id.studydetail_backimg)
    ImageView backimg;
    @BindView(R.id.studydetail_shareimg)
    ImageView shareimg;
    @BindView(R.id.studydetail_signup)
    TextView studydetail_signup;
    @BindView(R.id.studydetail_title)
    TextView studydetail_title;
    @BindView(R.id.studydetail_describe)
    TextView studydetail_describe;
    @BindView(R.id.studydetail_watchnub)
    TextView studydetail_watchnub;
    @BindView(R.id.studydetail_apparatus)
    TextView studydetail_apparatus;
    @BindView(R.id.studydetail_classnub)
    TextView studydetail_classnub;
    @BindView(R.id.studydetail_study)
    CardView studydetail_study;
    @BindView(R.id.studydetail_testcard)
    CardView studydetail_testcard;
    @BindView(R.id.studydetail_signupcard)
    CardView studydetail_signupcard;



    private List<String> classNames;
    Video video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }


    protected void init() {
        video= (Video) getIntent().getSerializableExtra("video");
        QMUIStatusBarHelper.translucent(this);
        ButterKnife.bind(this);
        initRecycleView(video.getTitle());
        initView(video);
    }

    protected void initBtn() {


    }

    private void initRecycleView(String videoName){
        initList();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(new ClassItemAdapter(StudyDetailsActivity.this,initList(),videoName,video));

    }

    private List initList(){
        classNames=new ArrayList<>();


        String course1=video.getCourse1();
        String course2=video.getCourse2();
        String course3=video.getCourse3();
        String course4=video.getCourse4();
        String course5=video.getCourse5();
        String course6=video.getCourse6();
        String course7=video.getCourse7();

        if(null!=course1&&!"".equals(course1))
            classNames.add(course1);
        if(null!=course2&&!"".equals(course2))
            classNames.add(course2);
        if(null!=course3&&!"".equals(course3))
            classNames.add(course3);
        if(null!=course4&&!"".equals(course4))
            classNames.add(course4);
        if(null!=course5&&!"".equals(course5))
            classNames.add(course5);
        if(null!=course6&&!"".equals(course6))
            classNames.add(course6);
        if(null!=course7&&!"".equals(course7))
            classNames.add(course7);

        studydetail_classnub.setText(classNames.size()+"节课");
        return classNames;
    }

    private void initView(Video video){
        studydetail_title.setText(video.getNowcourse7());
        if (PracticeIndexAdapter.FRESH.equals(video.getCategory()))
            studydetail_signup.setVisibility(View.GONE);

        backimg.setOnClickListener(this);
        shareimg.setOnClickListener(this);
        studydetail_signupcard.setOnClickListener(this);
        studydetail_testcard.setOnClickListener(this);
        studydetail_study.setOnClickListener(this);
        studydetail_apparatus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.studydetail_backimg:
                finish();
                break;
            case R.id.studydetail_shareimg:
                UiUtils.ShareToOthers(StudyDetailsActivity.this);
                break;
            case R.id.studydetail_signupcard:
                new SignUpDialog(StudyDetailsActivity.this, new SignUpDialog.ClickCallBack() {
                    @Override
                    public void signUp() {

                    }

                    @Override
                    public void onStop() {

                    }

                }).show();
                break;
            case R.id.studydetail_testcard:
                startActivity(new Intent(this,PracticeTestActivity.class));
                break;
            case R.id.studydetail_study:
//                startActivity(new Intent(this,VideoActivity.class));
                break;
            case R.id.studydetail_apparatus:
                new AppratusDialog(StudyDetailsActivity.this, new AppratusDialog.ClickCallBack() {
                    @Override
                    public void onStop() {
                    }
                },"救生衣，牛尾绳，抛绳包，救援头盔，救援靴，救援手套").show();
                break;
        }

    }
}
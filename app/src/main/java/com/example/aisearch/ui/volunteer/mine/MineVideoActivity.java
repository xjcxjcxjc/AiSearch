package com.example.aisearch.ui.volunteer.mine;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.volunteer.mine.adapter.MineVideoAdapter2;
import com.example.aisearch.util.datautil.PracticeDataUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineVideoActivity extends BaseActivity {


    @BindView(R.id.mine_video_recycle)
    RecyclerView mine_video_recycle;
    @BindView(R.id.finish)
    Button finish;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_mine_video;
    }

    @Override
    protected void init() {

        mine_video_recycle.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mine_video_recycle.setAdapter(new MineVideoAdapter2(MineVideoActivity.this,initVideos()));

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



    private List initVideos(){
        List videos=new ArrayList();
        videos.addAll(PracticeDataUtils.getMineVideos());
//        videos.addAll(PracticeDataUtils.getFreshBirdVideos());
        return videos;
    }



}
package com.example.aisearch.ui.volunteer.practice;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;
import com.example.aisearch.bean.volunteer.Video;
import com.example.aisearch.ui.volunteer.practice.adapter.PracticeIndexAdapter;
import com.example.aisearch.util.UiUtils;
import com.example.aisearch.util.datautil.PracticeDataUtils;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillCampFragment extends BaseFragment {

    @BindView(R.id.freshbird_recycle)
    RecyclerView recyclerView;

    private List<Video> videos;

    @Override
    protected void init(View view, Bundle savedInstanceState) {

        ButterKnife.bind(this,view);
        initRecycleView();
    }

    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {


    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_skill;
    }
    private void initRecycleView(){
        initVideos();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new PracticeIndexAdapter(getContext(),videos,initbackground()));
    }

    private void initVideos(){
        if (null==videos)
            videos=new ArrayList<>();
        videos= PracticeDataUtils.getSkillImpvVideos();
    }

    private List initbackground(){
        List list= Lists.newArrayList(
                UiUtils.resourceToBitmap(getContext(),R.mipmap.video_bacg6),
                UiUtils.resourceToBitmap(getContext(),R.mipmap.video_bacg7),
                UiUtils.resourceToBitmap(getContext(),R.mipmap.video_bacg8),
                UiUtils.resourceToBitmap(getContext(),R.mipmap.video_bacg9),
                UiUtils.resourceToBitmap(getContext(),R.mipmap.video_bacg10)
        );
        return list;
    }
}

package com.example.aisearch.ui.volunteer.practice;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.volunteer.Video;
import com.example.aisearch.ui.volunteer.practice.adapter.PracticeIndexAdapter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends BaseActivity{

    @BindView(R.id.searchresult_recycleview)
    RecyclerView recyclerView;
    private List<Video> videos;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initRecycleView();
    }

    @Override
    protected void initBtn() {

    }

    private void initRecycleView(){

        QMUIStatusBarHelper.translucent(this);
        initVideos();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new PracticeIndexAdapter(SearchResultActivity.this,videos));
    }

    private void initVideos(){
        if (null==videos)
            videos=new ArrayList<>();

        videos.add(new Video("常见急救知识", PracticeIndexAdapter.FRESH, "志愿者精神体现着个人对生命，社会，人类，和世界",
                1, 4231,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("常见急救知识", PracticeIndexAdapter.SKILL, "志愿者精神体现着个人对生命，社会，人类，和世界",
                5, 1111,2,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));

    }

}
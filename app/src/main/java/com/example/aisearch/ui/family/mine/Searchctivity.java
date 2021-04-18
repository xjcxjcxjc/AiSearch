package com.example.aisearch.ui.family.mine;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.util.Clue;
import com.example.aisearch.util.UiUtils;
import com.google.common.collect.Lists;

import java.util.List;

import butterknife.BindView;

public class Searchctivity extends BaseActivity {


    @BindView(R.id.search_recycle)
    RecyclerView search_recycle;

    @BindView(R.id.finish)
    Button finish;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_searchctivity;
    }

    @Override
    protected void init() {

        search_recycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        search_recycle.setAdapter(new SearchAdapter(Searchctivity.this,initData()));
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



    private List initData(){
        List list= Lists.newArrayList(
                new Clue(UiUtils.resourceToBitmap(Searchctivity.this,R.mipmap.orgnization_headimg2),
                        "太阳","在下沙小区看到疑似老人","2021.3.21 10:34"),
                new Clue(UiUtils.resourceToBitmap(Searchctivity.this,R.mipmap.orgnization_headimg14),
                        "阿糖姐","在和平路附近看到相似着装老人","2021.3.20 16:22"),
                new Clue(UiUtils.resourceToBitmap(Searchctivity.this,R.mipmap.orgnization_headimg7),
                        "Ming","在白云路看到相似老人朝东南方向走去","2021.3.20 15:51")
        );


        return list;
    }


}
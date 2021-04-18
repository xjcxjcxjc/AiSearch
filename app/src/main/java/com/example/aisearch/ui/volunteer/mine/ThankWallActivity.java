package com.example.aisearch.ui.volunteer.mine;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.util.volunteer.ThankMsg;
import com.example.aisearch.ui.volunteer.mine.adapter.MineThankWallAdapter;
import com.example.aisearch.util.UiUtils;
import com.google.common.collect.Lists;

import java.util.List;

import butterknife.BindView;

public class ThankWallActivity extends BaseActivity {

    @BindView(R.id.mine_thankwall_recycle)
    RecyclerView recyclerView;

    @BindView(R.id.finish)
    Button finish;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_thank_wall;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MineThankWallAdapter(ThankWallActivity.this,initData()));

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


    private List<ThankMsg> initData(){
        List list= Lists.newArrayList(
                new ThankMsg(UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg14),"2021-01-10",
                        "李卫国家人","谢谢你们寻回我的父亲，给我的生命带来了阳光温暖，没有英雄们的付出，" +
                        "这将是我一生之痛，祝你们平安健康，万分感激!",true)


        );


        return list;
    }

}
package com.example.aisearch.ui.volunteer.home.message;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.Message.TeamMate;
import com.example.aisearch.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TeamMatesActivity extends BaseActivity {

    @BindView(R.id.teammates_recycle)
    RecyclerView teammates_recycle;


    @BindView(R.id.finish)
    Button finish;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_team_mates;
    }

    @Override
    protected void init() {
        teammates_recycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        teammates_recycle.setAdapter(new TeamMatesAdapter(this,initdata()));
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initBtn() {


    }

    private List<TeamMate> initdata(){
        List<TeamMate> orgnizationItems=new ArrayList<>();


        orgnizationItems.add(new TeamMate("羁客", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg1),
                TeamMate.POSITION.CAPTAIN,"13454223781"));
        orgnizationItems.add(new TeamMate("洒一地阳光ヽ", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg2),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("陆佳伟", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg3),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("じее", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg4),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("晚雾", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg5),
                TeamMate.POSITION.MATE,"13454223781"));


        orgnizationItems.add(new TeamMate("周世凯", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg6),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("贪杯", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg7),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("黎明", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg8),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("于初秋", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg9),
                TeamMate.POSITION.MATE,"13454223781"));


        orgnizationItems.add(new TeamMate("∞白馒头", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg10),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("有需要call我", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg11),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("暮年", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg12),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("转身泪倾城", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg13),
                TeamMate.POSITION.MATE,"13454223781"));


        orgnizationItems.add(new TeamMate("五花", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg14),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("六哥", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg15),
                TeamMate.POSITION.MATE,"13454223781"));
        orgnizationItems.add(new TeamMate("张卫国", UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg16),
                TeamMate.POSITION.MATE,"13454223781"));

        return orgnizationItems;

    }


}
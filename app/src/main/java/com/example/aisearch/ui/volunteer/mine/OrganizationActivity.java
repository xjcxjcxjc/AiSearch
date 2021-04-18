package com.example.aisearch.ui.volunteer.mine;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.util.volunteer.Badge;
import com.example.aisearch.ui.volunteer.mine.adapter.OrganizationAdapter2;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganizationActivity extends BaseActivity {

    @BindView(R.id.organization_recycleview)
    RecyclerView organization_recycleview;

    @BindView(R.id.actionlist_finish)
    Button finish;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_organization;
    }

    @Override
    protected void init() {
        QMUIStatusBarHelper.translucent(this);
        ButterKnife.bind(this);
        initRecycle();
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

    private void initRecycle() {
        organization_recycleview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        organization_recycleview.setAdapter(new OrganizationAdapter2(OrganizationActivity.this,initData1()));


    }

    private List<Badge> initData1(){
        List<Badge> badges=new ArrayList<>();

        badges.add(new Badge("NBZYZXH","宁波市志愿者协会"));
        badges.add(new Badge("BSR","蓝天救援队"));
        badges.add(new Badge("UVA","浙江高校志愿者协会"));
        badges.add(new Badge("HZZYZXH","杭州市志愿者协会"));
        badges.add(new Badge("SHZYZXH","上海市志愿者协会"));



        return badges;
    }

}
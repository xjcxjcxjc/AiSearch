package com.example.aisearch.ui.volunteer.mine;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.volunteer.Badge;
import com.example.aisearch.ui.volunteer.mine.adapter.OrganizationAdapter;
import com.example.aisearch.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CertificateActivity extends BaseActivity {


    @BindView(R.id.badge_recyclerview)
    RecyclerView badge_recyclerview;

    OrganizationAdapter badgeAdapter;
    List<Badge> badges;

    @BindView(R.id.finish)
    Button finish;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_certificate;
    }

    @Override
    protected void init() {
        initRecycleView();
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

    private void initRecycleView(){
        badgeAdapter= new OrganizationAdapter(CertificateActivity.this,initData1());
        badge_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        badge_recyclerview.setAdapter(badgeAdapter);


    }

    private List<Badge> initData1(){
        if (null==badges)
            badges=new ArrayList<>();
        else
            badges.clear();

        badges.add(new Badge(UiUtils.resourceToBitmap(CertificateActivity.this,R.mipmap.mine_img1),"xxx证书"));
        badges.add(new Badge(UiUtils.resourceToBitmap(CertificateActivity.this,R.mipmap.mine_img1),"xxx证书"));
        badges.add(new Badge(UiUtils.resourceToBitmap(CertificateActivity.this,R.mipmap.mine_img1),"xxx证书"));
        badges.add(new Badge(UiUtils.resourceToBitmap(CertificateActivity.this,R.mipmap.mine_img1),"xxx证书"));



        return badges;
    }
}
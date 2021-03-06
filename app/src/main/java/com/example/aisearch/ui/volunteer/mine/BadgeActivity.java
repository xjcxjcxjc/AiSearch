package com.example.aisearch.ui.volunteer.mine;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.util.volunteer.Badge;
import com.example.aisearch.ui.volunteer.mine.adapter.OrganizationAdapter;
import com.example.aisearch.util.UiUtils;
import com.qmuiteam.qmui.util.QMUIViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BadgeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.badge_recyclerview)
    RecyclerView badge_recyclerview;
    @BindView(R.id.badge_rg)
    RadioGroup rg;
    @BindView(R.id.badge_rb1)
    RadioButton rb1;
    @BindView(R.id.badge_rb2)
    RadioButton rb2;
    @BindView(R.id.badge_rb3)
    RadioButton rb3;
    @BindView(R.id.badge_certificate)
    CardView badge_certificate;
    @BindView(R.id.finish)
    Button finish;
    @BindView(R.id.share)
    ImageView share;

    OrganizationAdapter badgeAdapter;
    List<Badge> badges;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_badge;

    }

    @Override
    protected void init() {
        initRecycleView();

    }

    @Override
    protected void initBtn() {
        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.badge_rb1);
        rb1.setOnCheckedChangeListener(this);
        rb2.setOnCheckedChangeListener(this);
        rb3.setOnCheckedChangeListener(this);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.ShareToOthers(BadgeActivity.this);
            }
        });

    }


    private void initRecycleView(){
        badgeAdapter= new OrganizationAdapter(BadgeActivity.this,initData1());
        badge_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        badge_recyclerview.setAdapter(badgeAdapter);


    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.badge_rb1:
                if (badge_certificate.getVisibility()== View.VISIBLE)
                    QMUIViewHelper.fadeOut(badge_certificate,500,null,true);
                initData1();
                badgeAdapter.notifyDataSetChanged();
                break;
            case R.id.badge_rb2:
                if (badge_certificate.getVisibility()== View.VISIBLE)
                    QMUIViewHelper.fadeOut(badge_certificate,500,null,true);
                initData2();
                badgeAdapter.notifyDataSetChanged();
                break;
            case R.id.badge_rb3:
                QMUIViewHelper.fadeIn(badge_certificate,500,null,true);
                initData3();
                badgeAdapter.notifyDataSetChanged();
                break;
        }
    }


    private List<Badge> initData1(){
        if (null==badges)
            badges=new ArrayList<>();
        else
            badges.clear();

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img1),"??????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img2),"??????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img3),"??????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img4),"??????????????????"));

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img5),"?????????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img6),"????????????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img7),"?????????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img8),"????????????????????????"));

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img9),"????????????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img10),"????????????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img11),"????????????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img12),"????????????????????????"));


        return badges;
    }

    private List<Badge> initData2(){
        if (null==badges)
            badges=new ArrayList<>();
        else
            badges.clear();

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img1),"??????????????????30??????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img2),"??????????????????2??????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img3),"??????????????????5??????"));

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img4),"????????????5???"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img5),"????????????10???"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img6),"????????????100???"));

        return badges;
    }

    private List<Badge> initData3(){
        if (null==badges)
            badges=new ArrayList<>();
        else
            badges.clear();

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group3_img1),"??????????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group3_img2),"???????????????"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group3_img3),"?????????????????????"));

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group3_img4),"?????????????????????"));
        return badges;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked)
            buttonView.setTextColor(getResources().getColor(R.color.title_blue));
        else
            buttonView.setTextColor(getResources().getColor(R.color.text_gray1));

    }
}
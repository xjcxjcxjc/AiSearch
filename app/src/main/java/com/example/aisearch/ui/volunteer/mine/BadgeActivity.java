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
import com.example.aisearch.bean.volunteer.Badge;
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

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img1),"参与一次活动"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img2),"参与十次活动"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img3),"参与百次活动"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img4),"五次成功寻回"));

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img5),"五十次成功寻回"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img6),"一百次次成功寻回"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img7),"累计志愿五小时"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img8),"累计志愿五十小时"));

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img9),"累计志愿一百小时"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img10),"志愿等级达到一级"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img11),"志愿等级达到二级"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group1_img12),"志愿等级达到三级"));


        return badges;
    }

    private List<Badge> initData2(){
        if (null==badges)
            badges=new ArrayList<>();
        else
            badges.clear();

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img1),"观看视频时长30分钟"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img2),"观看视频时长2小时"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img3),"观看视频时长5小时"));

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img4),"答对习题5道"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img5),"答对习题10道"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group2_img6),"答对习题100道"));

        return badges;
    }

    private List<Badge> initData3(){
        if (null==badges)
            badges=new ArrayList<>();
        else
            badges.clear();

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group3_img1),"户外领队专属"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group3_img2),"急救员专属"));
        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group3_img3),"应急急救员专属"));

        badges.add(new Badge(UiUtils.resourceToBitmap(BadgeActivity.this,R.mipmap.mine_group3_img4),"游泳救生员专属"));
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
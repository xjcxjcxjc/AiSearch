package com.example.aisearch.ui.volunteer.community.fragment;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;
import com.example.aisearch.bean.util.volunteer.CommunityItems;
import com.example.aisearch.util.datautil.DataUtil;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * @Author : XJC
 * @Time : 2021/2/13 14:11
 * @Description : 发现fragment
 *
 */
public class SameCityFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.community_rg)
    RadioGroup radioGroup;
    @BindView(R.id.community_rb1)
    RadioButton rb1;
    @BindView(R.id.community_rb2)
    RadioButton rb2;
    @BindView(R.id.community_rb3)
    RadioButton rb3;
    @BindView(R.id.community_rb4)
    RadioButton rb4;
    @BindView(R.id.community_rb5)
    RadioButton rb5;
    @BindView(R.id.community_rb6)
    RadioButton rb6;

    Drawable drawable1;
    Drawable drawable2;
    Drawable drawable3;
    Drawable drawable4;
    Drawable drawable5;
    Drawable drawable6;

    //fragment
    Fragment communityItemFragment1;
    Fragment communityItemFragment2;
    Fragment communityItemFragment3;
    Fragment communityItemFragment4;

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        initBackGrd();
        rb1.setOnCheckedChangeListener(this);

        rb1.setTextColor(getResources().getColor(R.color.white));
        rb2.setOnCheckedChangeListener(this);
        rb3.setOnCheckedChangeListener(this);
        rb4.setOnCheckedChangeListener(this);
        rb5.setOnCheckedChangeListener(this);
        rb6.setOnCheckedChangeListener(this);

        rb1.setBackground(drawable1);
        rb2.setBackground(drawable2);
        rb3.setBackground(drawable3);
        rb4.setBackground(drawable4);
        rb5.setBackground(drawable5);
        rb6.setBackground(drawable6);

        radioGroup.check(R.id.community_rb1);
        initFragment1();
    }

    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_community_find;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked)
            buttonView.setTextColor(getResources().getColor(R.color.white));
        else
            buttonView.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


    }


//    private void initRecycleView(View view){
//        RecyclerView recyclerView=view.findViewById(R.id.community_recycle);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setAdapter(new CommunityFragmentAdapter(getContext(),initData()));
//
//    }
//    private List<CommunityItem> initData(){
//        return DataUtil.getCommunityData2(getContext());
//
//    }


    private void initBackGrd(){
        SharedPreferences sharedPreferences;
        sharedPreferences=getContext().getSharedPreferences("data",getContext().MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML){
            drawable1=getContext().getResources().getDrawable(R.drawable.community_radio_btn2);
            drawable2=getContext().getResources().getDrawable(R.drawable.community_radio_btn2);
            drawable3=getContext().getResources().getDrawable(R.drawable.community_radio_btn2);
            drawable4=getContext().getResources().getDrawable(R.drawable.community_radio_btn2);
            drawable5=getContext().getResources().getDrawable(R.drawable.community_radio_btn2);
            drawable6=getContext().getResources().getDrawable(R.drawable.community_radio_btn2);

        }
        else{
            drawable1=getContext().getResources().getDrawable(R.drawable.community_radio_btn);
            drawable2=getContext().getResources().getDrawable(R.drawable.community_radio_btn);
            drawable3=getContext().getResources().getDrawable(R.drawable.community_radio_btn);
            drawable4=getContext().getResources().getDrawable(R.drawable.community_radio_btn);
            drawable5=getContext().getResources().getDrawable(R.drawable.community_radio_btn);
            drawable6=getContext().getResources().getDrawable(R.drawable.community_radio_btn);
        }


    }

    @OnClick(R.id.community_rb1)
    //显示第一个fragment
    protected void initFragment1(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(communityItemFragment1 == null){
            communityItemFragment1 = new CommunityItemFragment(DataUtil.getCommunityData(getContext(), CommunityItems.TYPE.CITY,CommunityItems.GROUP.COMMEND));
            transaction.add(R.id.community_fragment, communityItemFragment1);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(communityItemFragment1);

        //提交事务
        transaction.commit();
    }
    @OnClick(R.id.community_rb2)
    //显示第一个fragment
    protected void initFragment2(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(communityItemFragment2 == null){
            communityItemFragment2 = new CommunityItemFragment(DataUtil.getCommunityData(getContext(), CommunityItems.TYPE.CITY,CommunityItems.GROUP.EXPERIENCE));
            transaction.add(R.id.community_fragment, communityItemFragment2);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(communityItemFragment2);

        //提交事务
        transaction.commit();
    }


    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(communityItemFragment1 != null){
            transaction.hide(communityItemFragment1);
        }
        if(communityItemFragment2 != null){
            transaction.hide(communityItemFragment2);
        }
    }


}

package com.example.aisearch.ui.volunteer.practice;


import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.aisearch.MyViewPagerAdapter;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class PracticeFragment extends BaseFragment implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener{

    private ViewPager practicevp;
    private TextView border1,border2;
    private RadioGroup rg;
    List<Fragment> fragments;
    TextView tosearch;

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        fragments=new ArrayList<>();

        FreshBirdFragment freshBirdFragment=new FreshBirdFragment();
        SkillCampFragment skillCampFragment=new SkillCampFragment();
        fragments.add(freshBirdFragment);
        fragments.add(skillCampFragment);


        practicevp=view.findViewById(R.id.practice_viewpager);
        border1=view.findViewById(R.id.pracrice_radiogroup_fbbtn_border);
        border2=view.findViewById(R.id.pracrice_radiogroup_skill_border);

        rg=view.findViewById(R.id.pracrice_radiogroup);

        MyViewPagerAdapter PracticeViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager(),fragments);
        practicevp.setAdapter(PracticeViewPagerAdapter);
//        //默认选中第一个
        rg.check(R.id.pracrice_radiogroup_fbbtn);
        rg.setOnCheckedChangeListener(this);
        practicevp.addOnPageChangeListener(this);
        practicevp.setOffscreenPageLimit(2);

        tosearch=view.findViewById(R.id.practice_tosearch);
        tosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PracticeSearchActivity.class,false);
            }
        });

    }

    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_practice;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rg.check(R.id.pracrice_radiogroup_fbbtn);
                border1.setBackground(getResources().getDrawable(R.color.white));
                border2.setBackground(getResources().getDrawable(R.color.qmui_config_color_transparent));
                break;
            case 1:
                rg.check(R.id.pracrice_radiogroup_skill);
                border2.setBackground(getResources().getDrawable(R.color.white));
                border1.setBackground(getResources().getDrawable(R.color.qmui_config_color_transparent));
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.pracrice_radiogroup_fbbtn:
                practicevp.setCurrentItem(0);
                border1.setBackground(getResources().getDrawable(R.color.white));
                border2.setBackground(getResources().getDrawable(R.color.qmui_config_color_transparent));
                break;
            case R.id.pracrice_radiogroup_skill:
                practicevp.setCurrentItem(1);
                border2.setBackground(getResources().getDrawable(R.color.white));
                border1.setBackground(getResources().getDrawable(R.color.qmui_config_color_transparent));
                break;
        }
    }

}

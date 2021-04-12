package com.example.aisearch;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;


/**
 * Created by KID on 2017/8/10 0010.
 */

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments;
    private static final String TAG = "ViewPagerAdapter";
    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    //防止销毁fragment
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //如果注释这行，那么不管怎么切换，page都不会被销毁
//        super.destroyItem(container, position, object);
    }

}

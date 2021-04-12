package com.example.aisearch;

import android.Manifest;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.volunteer.action.index.ActionFragment;
import com.example.aisearch.ui.volunteer.community.CommunityFragment;
import com.example.aisearch.ui.volunteer.mine.MineFragment;
import com.example.aisearch.ui.volunteer.practice.PracticeFragment;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;

    MyViewPagerAdapter myViewPagerAdapter;
    List<Fragment> fragments;
    ActionFragment actionFragment;
    CommunityFragment communityFragment;
    PracticeFragment practiceFragment;
    MineFragment mineFragment;


    ImageView actionImag;
    ImageView communityImag;
    ImageView practiceImag;
    ImageView mineImag;


    private String permissions[] = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
    };

    private void initView(){
        QMUIStatusBarHelper.translucent(this);
        actionImag=findViewById(R.id.main_actionimg);
        communityImag=findViewById(R.id.main_communityimg);
        practiceImag=findViewById(R.id.main_practiceimg);
        mineImag=findViewById(R.id.main_mineimg);


        RadioButton radioButton1=findViewById(R.id.main_rb_1);
        RadioButton radioButton2=findViewById(R.id.main_rb_2);
        RadioButton radioButton3=findViewById(R.id.main_rb_3);
        RadioButton radioButton4=findViewById(R.id.main_rb_4);

        radioButton1.setOnCheckedChangeListener(this);
        radioButton2.setOnCheckedChangeListener(this);
        radioButton3.setOnCheckedChangeListener(this);
        radioButton4.setOnCheckedChangeListener(this);
    }

    public void request() {
        if (EasyPermissions.hasPermissions(MainActivity.this, permissions)) {
            // Toast.makeText(MainActivity.this, "已授权 ", Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, "请授予以下权限，以便您有更好的体验", 101, permissions);
        }
    }
    private void initViewPager() {
        fragments=new ArrayList<>();
        actionFragment=new ActionFragment();
        communityFragment =new CommunityFragment();
        practiceFragment =new PracticeFragment();
        mineFragment =new MineFragment();

        fragments.add(actionFragment);
        fragments.add(practiceFragment);
        fragments.add(communityFragment);
        fragments.add(mineFragment);

        myViewPagerAdapter =new MyViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myViewPagerAdapter);
        //默认选中第一个
        rg.check(R.id.main_rb_1);
        rg.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rg.check(R.id.main_rb_1);
                break;
            case 1:
                rg.check(R.id.main_rb_2);
                break;
            case 2:
                rg.check(R.id.main_rb_3);
                break;
            case 3:
                rg.check(R.id.main_rb_4);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_rb_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.main_rb_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_rb_3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.main_rb_4:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);//将第三方权限框架与activity绑定
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        initView();
        initViewPager();
        request();
    }

    @Override
    protected void initBtn() {
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.main_rb_1:
                if (isChecked)
                    actionImag.setImageResource(R.mipmap.ab1_2);
                else
                    actionImag.setImageResource(R.mipmap.ab1_1);
                break;
            case R.id.main_rb_2:
                if (isChecked)
                    communityImag.setImageResource(R.mipmap.ab3_2);
                else
                    communityImag.setImageResource(R.mipmap.ab3_1);
                break;
            case R.id.main_rb_3:
                if (isChecked)
                    practiceImag.setImageResource(R.mipmap.ab2_2);
                else
                    practiceImag.setImageResource(R.mipmap.ab2_1);
                break;
            case R.id.main_rb_4:
                if (isChecked)
                    mineImag.setImageResource(R.mipmap.ab4_2);
                else
                    mineImag.setImageResource(R.mipmap.ab4_1);
                break;
        }
    }
}
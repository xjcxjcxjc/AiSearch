package com.example.aisearch;

import android.Manifest;
import android.content.SharedPreferences;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.family.myfamily.MyFamilyFragment;
import com.example.aisearch.ui.family.take_a_hand.tkahandFragment;
import com.example.aisearch.ui.volunteer.community.CommunityFragment;
import com.example.aisearch.util.NotificationHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity2 extends BaseActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.rg2)
    RadioGroup rg;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.main2_backgrd)
    LinearLayout main2_backgrd;

    MyViewPagerAdapter myViewPagerAdapter;
    List<Fragment> fragments;
    com.example.aisearch.ui.family.take_a_hand.tkahandFragment tkahandFragment;
    CommunityFragment communityFragment;
    MyFamilyFragment myFamilyFragment;

    ImageView actionImag;
    ImageView communityImag;
    ImageView practiceImag;
    ImageView mineImag;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main4;
    }

    @Override
    protected void init() {
        initView();
        initViewPager();
        request();
        initBackGrd();
        NotificationHelper.show(MainActivity2.this);
        NotificationHelper.show2(MainActivity2.this);
    }

    @Override
    protected void initBtn() {
    }

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
//        mineImag=findViewById(R.id.main_mineimg);


        RadioButton radioButton1=findViewById(R.id.main2_rb_1);
        RadioButton radioButton2=findViewById(R.id.main2_rb_2);
        RadioButton radioButton3=findViewById(R.id.main2_rb_3);

        radioButton1.setOnCheckedChangeListener(this);
        radioButton2.setOnCheckedChangeListener(this);
        radioButton3.setOnCheckedChangeListener(this);
    }

    public void request() {
        if (EasyPermissions.hasPermissions(MainActivity2.this, permissions)) {
            // Toast.makeText(MainActivity.this, "已授权 ", Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, "请授予以下权限，以便您有更好的体验", 101, permissions);
        }
    }
    private void initViewPager() {
        fragments=new ArrayList<>();
        tkahandFragment=new tkahandFragment();
        communityFragment =new CommunityFragment();
        myFamilyFragment =new MyFamilyFragment();

        fragments.add(tkahandFragment);
        fragments.add(communityFragment);
        fragments.add(myFamilyFragment);

        myViewPagerAdapter =new MyViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myViewPagerAdapter);
        //默认选中第一个
        rg.check(R.id.main2_rb_1);
        rg.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(3);
    }


    private void initBackGrd(){
        SharedPreferences sharedPreferences;
        sharedPreferences= getSharedPreferences("data",  MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML)
            main2_backgrd.setBackgroundColor( getResources().getColor(R.color.title_red));
        else
            main2_backgrd.setBackgroundColor( getResources().getColor(R.color.title_blue));

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rg.check(R.id.main2_rb_1);
                break;
            case 1:
                rg.check(R.id.main2_rb_2);
                break;
            case 2:
                rg.check(R.id.main2_rb_3);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main2_rb_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.main2_rb_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main2_rb_3:
                viewPager.setCurrentItem(2);
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.main2_rb_1:
                if (isChecked)
                    actionImag.setImageResource(R.mipmap.family_main2);
                else
                    actionImag.setImageResource(R.mipmap.family_main1);
                break;
            case R.id.main2_rb_2:
                if (isChecked)
                    communityImag.setImageResource(R.mipmap.family_main4);
                else
                    communityImag.setImageResource(R.mipmap.family_main3);
                break;
            case R.id.main2_rb_3:
                if (isChecked)
                    practiceImag.setImageResource(R.mipmap.main2_mine_person);
                else
                    practiceImag.setImageResource(R.mipmap.main2_mine_person2);
                break;
        }
    }
}
package com.example.aisearch.ui.volunteer.community;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.MyViewPagerAdapter;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;
import com.example.aisearch.bean.volunteer.CommunityItems;
import com.example.aisearch.ui.volunteer.community.fragment.FindFragment;
import com.example.aisearch.ui.volunteer.community.fragment.SameCityFragment;
import com.example.aisearch.ui.volunteer.home.publish.PublishActivity;
import com.example.aisearch.util.UiUtils;
import com.example.aisearch.util.datautil.DataUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CommunityFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener{


    public CommunityFragment() {
    }


    TextView city;
    ImageView add;
    FindFragment findFragment;
    SameCityFragment sameCityFragment;

    @BindView(R.id.community_main_backgrd)
    LinearLayout backgrd;

    @BindView(R.id.rg1)
    RadioGroup rg1;
    @BindView(R.id.community_find)
    RadioButton community_find;
    @BindView(R.id.community_samecity)
    RadioButton community_samecity;
    @BindView(R.id.background1)
    TextView background1;
    @BindView(R.id.background2)
    TextView background2;

    @BindView(R.id.community_fragment)
    ViewPager viewPager;

    List fragments;

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        city=view.findViewById(R.id.community_city);
        city.setText("宁波");
        initBackGrd();
        initViewPager();
    }



    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {
        add=view.findViewById(R.id.community_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), PublishActivity.class),7650);
            }
        });

        rg1.check(R.id.community_find);
        community_find.setOnCheckedChangeListener(this);



    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_communication;
    }


    /**
     * 得到Publish后的信息
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(7650==requestCode){

            byte[] res = data.getByteArrayExtra("img");
            Bitmap bitmap= DataUtil.getPicFromBytes(res,null);

            CommunityItems communityItems =new CommunityItems( UiUtils.resourceToBitmap(getContext(), R.mipmap.orgnization_headimg6),
                    UiUtils.bitmapFitWidth(UiUtils.getDeviceWidth(getContext())/2, bitmap),
                    1,  data.getStringExtra("title"),
                    data.getStringExtra("content"), "495","26" ,"68" );

            findFragment.addContent(communityItems);
        }

    }

    private void initViewPager() {
        fragments=new ArrayList<>();
        findFragment =new FindFragment();
        sameCityFragment =new SameCityFragment();

        fragments.add(findFragment);
        fragments.add(sameCityFragment);

        MyViewPagerAdapter myViewPagerAdapter =new MyViewPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(myViewPagerAdapter);
        //默认选中第一个
        rg1.check(R.id.community_find);
        rg1.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);
    }


    private void initBackGrd(){
        SharedPreferences sharedPreferences;
        sharedPreferences=getContext().getSharedPreferences("data",getContext().MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML)
            backgrd.setBackgroundColor(getContext().getResources().getColor(R.color.title_red));
        else
            backgrd.setBackgroundColor(getContext().getResources().getColor(R.color.title_blue));
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.community_find:
                if (isChecked){
                    background1.setVisibility(View.VISIBLE);
                    background2.setVisibility(View.INVISIBLE);
                }else{
                    background2.setVisibility(View.VISIBLE);
                    background1.setVisibility(View.INVISIBLE);
                }

                break;
        }


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.community_find:
                viewPager.setCurrentItem(0);
                break;
            case R.id.community_samecity:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rg1.check(R.id.community_find);
                break;
            case 1:
                rg1.check(R.id.community_samecity);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}

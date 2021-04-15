package com.example.aisearch.ui.volunteer.home;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.aisearch.MyViewPagerAdapter;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;
import com.example.aisearch.bean.family.GetConfigReq;
import com.example.aisearch.ui.volunteer.home.index.SearchActivity;
import com.example.aisearch.ui.volunteer.home.index.fragments.ActionCenterFragment;
import com.example.aisearch.ui.volunteer.home.index.fragments.SearchNoticeFragment;
import com.example.aisearch.ui.volunteer.home.message.MessageActivity;
import com.example.aisearch.util.CommonPopWindow;
import com.example.aisearch.util.PickerScrollView;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActionFragment extends BaseFragment implements ViewPager.OnPageChangeListener,
        RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, CommonPopWindow.ViewClickListener,
        View.OnClickListener{



    

    Button locationbtn;
    QMUIRoundButton search;
    Button talkbtn;
    RadioGroup rg;
    RadioButton rb1;
    RadioButton rb2;
    TextView rb1bg;
    TextView rb2bg;


    ViewPager viewPager;

    private List<GetConfigReq.DatasBean> datasBeanList1;
    private List<GetConfigReq.DatasBean> datasBeanList2;

    String categoryName;

    @BindView(R.id.index_topbar_locationtv)
    TextView hand_topbar_city;
    SearchNoticeFragment searchNoticeFragment;
    ActionCenterFragment actionCenterFragment;
    @Override
    protected void init(View view, Bundle savedInstanceState) {
        searchNoticeFragment=new SearchNoticeFragment();
        actionCenterFragment=new ActionCenterFragment();
        List<Fragment>fragments=new ArrayList<>();
        fragments.add(actionCenterFragment);
        fragments.add(searchNoticeFragment);
        initData();
        viewPager=view.findViewById(R.id.index_viewpager);
        MyViewPagerAdapter myViewPagerAdapter =new MyViewPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);



    }

    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {
        rg=view.findViewById(R.id.index_topbar_rg);
        rb1=view.findViewById(R.id.index_topbar_rb1);
        rb2=view.findViewById(R.id.index_topbar_rb2);
        rb1bg=view.findViewById(R.id.index_topbar_rb1bg);
        rb2bg=view.findViewById(R.id.index_topbar_rb2bg);
        locationbtn=view.findViewById(R.id.index_topbar_locationbtn);

        search=view.findViewById(R.id.index_topbar_searchbtn);
        talkbtn=view.findViewById(R.id.index_topbar_talkbtn);
        locationbtn.setOnClickListener(this);
        search.setOnClickListener(this);
        talkbtn.setOnClickListener(this);

        rg.check(R.id.index_topbar_rb1);
        rg.setOnCheckedChangeListener(this);
        rb1.setOnCheckedChangeListener(this);
        rb2.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_action;

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.index_topbar_rb1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.index_topbar_rb2:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.index_topbar_rb1:
                if (isChecked){
                    rb1.setTextColor(getResources().getColor(R.color.title_blue));
                    rb1bg.setBackgroundColor(getResources().getColor(R.color.title_blue));
                }
                else{
                    rb1.setTextColor(getResources().getColor(R.color.text_gray1));
                    rb1bg.setBackgroundColor(getResources().getColor(R.color.qmui_config_color_transparent));
                }
                break;
            case R.id.index_topbar_rb2:
                if (isChecked){
                    rb2.setTextColor(getResources().getColor(R.color.title_blue));
                    rb2bg.setBackgroundColor(getResources().getColor(R.color.title_blue));
                }
                else{
                    rb2.setTextColor(getResources().getColor(R.color.text_gray1));
                    rb2bg.setBackgroundColor(getResources().getColor(R.color.qmui_config_color_transparent));
                }
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.index_topbar_searchbtn:
                startActivity(SearchActivity.class,false);
                break;
            case R.id.index_topbar_locationbtn:
                setAddressSelectorPopup(v);
                break;
            case R.id.index_topbar_talkbtn:
                startActivity(new Intent(getContext(), MessageActivity.class));
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
                rg.check(R.id.index_topbar_rb1);
                break;
            case 1:
                rg.check(R.id.index_topbar_rb2);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initData() {
        //模拟请求后台返回数据
        String response = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"  0\",\"categoryName\":\"浙江\",\"state\":\"1\"}," +
                "{\"ID\":\"1\",\"categoryName\":\"上海\",\"state\":\"1\"}," +
                "{\"ID\":\"2\",\"categoryName\":\"江西\",\"state\":\"1\"}," +
                "{\"ID\":\"3\",\"categoryName\":\"湖南\",\"state\":\"1\"}," +
                "{\"ID\":\"4\",\"categoryName\":\"湖北\",\"state\":\"1\"}," +
                "{\"ID\":\"5\",\"categoryName\":\"北京\",\"state\":\"1\"}," +
                "{\"ID\":\"6\",\"categoryName\":\"福建\",\"state\":\"1\"}," +
                "{\"ID\":\"7\",\"categoryName\":\"四川\",\"state\":\"1\"}," +
                "{\"ID\":\"8\",\"categoryName\":\"南京\",\"state\":\"1\"}," +
                "{\"ID\":\"9\",\"categoryName\":\"河南\",\"state\":\"1\"}" +
                "]}";

        String response2 = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"  0\",\"categoryName\":\"宁波\",\"state\":\"1\"}," +
                "{\"ID\":\"1\",\"categoryName\":\"杭州\",\"state\":\"1\"}," +
                "{\"ID\":\"2\",\"categoryName\":\"温州\",\"state\":\"1\"}," +
                "{\"ID\":\"3\",\"categoryName\":\"绍兴\",\"state\":\"1\"}," +
                "{\"ID\":\"4\",\"categoryName\":\"嘉兴\",\"state\":\"1\"}," +
                "{\"ID\":\"5\",\"categoryName\":\"台州\",\"state\":\"1\"}," +
                "{\"ID\":\"6\",\"categoryName\":\"金华\",\"state\":\"1\"}," +
                "{\"ID\":\"7\",\"categoryName\":\"湖州\",\"state\":\"1\"}," +
                "{\"ID\":\"8\",\"categoryName\":\"丽水\",\"state\":\"1\"}," +
                "{\"ID\":\"9\",\"categoryName\":\"舟山\",\"state\":\"1\"}" +
                "]}";

        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        GetConfigReq getConfigReq2 = new Gson().fromJson(response2, GetConfigReq.class);
        //0请求表示成功
        if (getConfigReq.getRet() == 0) {
            //滚动选择数据集合
            datasBeanList1 = getConfigReq.getDatas();
            datasBeanList2 = getConfigReq2.getDatas();
        }


    }


    /**
     * 将选择器放在底部弹出框
     * @param v
     */
    private void setAddressSelectorPopup(View v) {
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;

        CommonPopWindow.newBuilder()
                .setView(R.layout.pop_picker_selector_bottom_address)
//                .setAnimationStyle(R.style.AnimUp)
                .setBackgroundDrawable(new BitmapDrawable())
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(screenHeigh * 0.3f))
                .setViewOnClickListener(this)
                .setBackgroundDarkEnable(true)
                .setBackgroundAlpha(0.7f)
                .setBackgroundDrawable(new ColorDrawable(999999))
                .build(getContext())
                .showAsBottom(v);
    }


    @Override
    public void getChildView(PopupWindow mPopupWindow, View view, int mLayoutResId) {
        switch (mLayoutResId) {
            case R.layout.pop_picker_selector_bottom_address:
                TextView imageBtn = view.findViewById(R.id.img_guanbi2);
                PickerScrollView addressSelector1 = view.findViewById(R.id.address1);
                PickerScrollView addressSelector2 = view.findViewById(R.id.address2);

                // 设置数据，默认选择第一条
                addressSelector1.setData(datasBeanList1);
                addressSelector1.setSelected(0);
                addressSelector2.setData(datasBeanList2);
                addressSelector2.setSelected(0);


                //滚动监听
                addressSelector2.setOnSelectListener(new PickerScrollView.onSelectListener() {
                    @Override
                    public void onSelect(GetConfigReq.DatasBean pickers) {
                        categoryName = pickers.getCategoryName();
                    }
                });

                //完成按钮
                imageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        hand_topbar_city.setText(categoryName);
                    }
                });
                break;
        }
    }
}

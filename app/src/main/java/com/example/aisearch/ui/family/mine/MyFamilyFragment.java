package com.example.aisearch.ui.family.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;
import com.example.aisearch.bean.family.ActionStatus;
import com.example.aisearch.ui.volunteer.mine.PersonPrivacyActivity;
import com.example.aisearch.ui.volunteer.mine.SettingActivity;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;

/*
 * @Author : XJC
 * @Time : 2021/2/26 6:17
 * @Description :
 *
 */
public class MyFamilyFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.mine2_urgenthelp)
    RelativeLayout mine2_urgenthelp;

    @BindView(R.id.mine2_setting)
    Button mine2_setting;

    @BindView(R.id.mine2_examine)
    RelativeLayout mine2_examine;

    @BindView(R.id.mine2_search)
    RelativeLayout mine2_search;

    @BindView(R.id.mine2_familymsg)
    RelativeLayout mine2_familymsg;


    @BindView(R.id.mine2_headimg)
    QMUIRadiusImageView mine2_headimg;


    @Override
    protected void init(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {
        mine2_urgenthelp.setOnClickListener(this);
        mine2_setting.setOnClickListener(this);
        mine2_examine.setOnClickListener(this);
        mine2_search.setOnClickListener(this);
        mine2_headimg.setOnClickListener(this);
        mine2_familymsg.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_myfamily;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine2_headimg:
                startActivity(PersonPrivacyActivity.class,false);
                break;
            case R.id.mine2_urgenthelp:
                startActivity(UrgentHelpActivity.class,false);
                break;
            case R.id.mine2_setting:
                startActivity(SettingActivity.class,false);
                break;
            case R.id.mine2_examine:
                Intent intent=new Intent(getContext(), ActionListActivity.class);
                intent.putExtra("type", ActionStatus.LISTTYPE_EXAMINE);
                startActivity(intent);
                break;
            case R.id.mine2_search:
                startActivity(Searchctivity.class,false);
                break;
            case R.id.mine2_familymsg:
                startActivity(Searchctivity.class,false);
                break;
        }


    }
}

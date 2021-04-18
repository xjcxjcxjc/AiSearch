package com.example.aisearch.ui.family.home;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.util.Person;
import com.example.aisearch.ui.volunteer.home.zwactivity.SearchPeopleByImageActivity;
import com.example.aisearch.util.datautil.DataUtil;
import com.example.aisearch.util.UiUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;

public class ClaimPersonDetailsActivity extends BaseActivity {

    @BindView(R.id.perosondetails_addclue)
    CardView addclue;

    @BindView(R.id.join_background)
    LinearLayout join_background;

    @BindView(R.id.join_finish)
    Button join_finish;

    @BindView(R.id.detail_lost_time_detailed)
    TextView pd_losttime;
    @BindView(R.id.pd_name)
    TextView pd_name;
    @BindView(R.id.pd_lostplace)
    TextView pd_lostplace;
    @BindView(R.id.pd_feature)
    TextView pd_feature;
    @BindView(R.id.pd_clothes)
    TextView pd_clothes;
    @BindView(R.id.detail_lost_nub)
    TextView detail_lost_nub;
    @BindView(R.id.describe)
    TextView describe;
    @BindView(R.id.share)
    ImageView share;

    @BindView(R.id.details2_headimg)
    ImageView details2_headimg;


    Person person;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_claimperson_details;
    }


    @Override
    protected void init() {
//        initBackGrd();
        QMUIStatusBarHelper.translucent(this);

        person= DataUtil.getPersonFromIntent(this);
        if(null!=person){
            initPersonData();
        }

        addclue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClaimPersonDetailsActivity.this, SearchPeopleByImageActivity.class));
            }
        });

        join_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTaskRoot())
                    finish();
                else{
                    startActivity(new Intent(ClaimPersonDetailsActivity.this, LodingActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void initBtn() {
        share.setOnClickListener((v)->{
            UiUtils.ShareToOthers(this);
        });
    }

    private void initPersonData(){
        details2_headimg.setImageBitmap(person.getImg());

        pd_losttime.setText(person.getTime());
        pd_name.setText("姓名："+person.getName());
        pd_lostplace.setText(person.getLocation());
        detail_lost_nub.setText(person.getNub());
        if (!"".equals(person.getDescribe()))
            describe.setText(person.getDescribe());


        if (null!= person.getFeatures())
            pd_feature.setText(person.getFeatures());
        if (null!= person.getClothes())
            pd_clothes.setText(person.getClothes());
        detail_lost_nub.setText(person.getNub());
    }



}
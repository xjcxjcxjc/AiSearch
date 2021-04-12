package com.example.aisearch.ui.volunteer.action;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.Person;
import com.example.aisearch.ui.volunteer.publish.ScreenActivity;
import com.example.aisearch.util.UiUtils;
import com.example.aisearch.util.datautil.DataUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;

public class LostPersonDetailsActivity extends BaseActivity {

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
    @BindView(R.id.pd_age)
    TextView pd_age;
    @BindView(R.id.pd_lostplace)
    TextView pd_lostplace;
    @BindView(R.id.pd_feature)
    TextView pd_feature;
    @BindView(R.id.pd_clothes)
    TextView pd_clothes;
    @BindView(R.id.actioncenter_actionnub)
    TextView actioncenter_actionnub;
    @BindView(R.id.birthday)
    TextView birthday;


    @BindView(R.id.share)
    ImageView share;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_lost_person_details;
    }


    @Override
    protected void init() {
        initBackGrd();
        QMUIStatusBarHelper.translucent(this);

        String msg =  getIntent().getStringExtra("msg");
        if ("noreport".equals(msg))
            addclue.setVisibility(View.GONE);

        initMsg();
        addclue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LostPersonDetailsActivity.this, ScreenActivity.class));
            }
        });

        join_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTaskRoot())
                    finish();
                else{
                    startActivity(new Intent(LostPersonDetailsActivity.this, LodingActivity.class));
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

    private void initBackGrd(){
        SharedPreferences sharedPreferences;
        sharedPreferences= getSharedPreferences("data",  MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML){
            join_background.setBackground(getDrawable(R.drawable.action_backgroud2));
            addclue.setCardBackgroundColor( getResources().getColor(R.color.title_red));
        } else if (identity== LodingActivity.IDENTIFY_VLT){
            join_background.setBackground(getDrawable(R.drawable.action_backgroud));
            addclue.setCardBackgroundColor( getResources().getColor(R.color.title_blue));
        }

    }

    /**
     * initMsg of lostPerson
     */
    private void initMsg(){
        Person person = DataUtil.getNoticePerson(this);
        pd_losttime.setText(person.getTime());
        pd_name.setText(person.getName());
        pd_age.setText(person.getAge());
        pd_lostplace.setText(person.getLocation());
        if (null!= person.getFeatures())
            pd_feature.setText(person.getFeatures());
        if (null!= person.getClothes())
            pd_clothes.setText(person.getClothes());
        if (null!= person.getBirth())
            birthday.setText(person.getBirth());

        actioncenter_actionnub.setText(person.getFrom());
    }




}
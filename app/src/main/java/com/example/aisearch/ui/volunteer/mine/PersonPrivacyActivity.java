package com.example.aisearch.ui.volunteer.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.MainActivity;
import com.example.aisearch.MainActivity2;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 个人信息
 */
public class PersonPrivacyActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.personprivacy_organization)
    LinearLayout organization;
    @BindView(R.id.personprivacy_grade)
    LinearLayout personprivacy_grade;
    @BindView(R.id.mine_privacychange)
    ImageView mine_privacychange;

    @BindView(R.id.background)
    LinearLayout linearLayout;

    @BindView(R.id.search_top)
    LinearLayout search_top;

    @BindView(R.id.actionlist_finish)
    Button finish;


    private SharedPreferences sharedPreferences=null;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_person_privacy;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        QMUIStatusBarHelper.translucent(this);
        initBackground();


    }

    @Override
    protected void initBtn() {
        organization.setOnClickListener(this);
        personprivacy_grade.setOnClickListener(this);
        mine_privacychange.setOnClickListener(this);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personprivacy_organization:
                startActivity(OrganizationActivity.class);
                break;
            case R.id.personprivacy_grade:
                startActivity(GradeActivity.class);
                break;
            case R.id.mine_privacychange:
                exchange();
                break;
        }

    }

    private void initBackground(){
        SharedPreferences sharedPreferences;
        sharedPreferences= getSharedPreferences("data",  MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML){
            search_top.setBackgroundColor( getResources().getColor(R.color.title_red));
            linearLayout.setBackgroundColor( getResources().getColor(R.color.title_red));
        }
        else{
            search_top.setBackgroundColor( getResources().getColor(R.color.title_blue));
            linearLayout.setBackgroundColor( getResources().getColor(R.color.title_blue));
        }
    }

    private void exchange(){
        if (null==sharedPreferences)
            sharedPreferences= getSharedPreferences("data",  MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML){
            SharedPreferences.Editor editor;
            editor =sharedPreferences.edit();
            editor.putInt("identity", LodingActivity.IDENTIFY_VLT);
            editor.commit();

            startActivity(MainActivity.class);
            Intent intent = new Intent();
            intent.setAction("action.exit");
            sendBroadcast(intent);
            finish();
        } else{
            SharedPreferences.Editor editor;
            editor =sharedPreferences.edit();
            editor.putInt("identity", LodingActivity.IDENTIFY_FML);
            editor.commit();

            startActivity(MainActivity2.class);
            Intent intent = new Intent();
            intent.setAction("action.exit");
            sendBroadcast(intent);
            finish();
        }

    }
}
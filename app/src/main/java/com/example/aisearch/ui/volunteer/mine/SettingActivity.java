package com.example.aisearch.ui.volunteer.mine;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.MainActivity;
import com.example.aisearch.MainActivity2;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.setting_background)
    LinearLayout background;
    @BindView(R.id.setting_top)
    LinearLayout top;
    @BindView(R.id.actionlist_finish)
    Button finish;

    private SharedPreferences sharedPreferences=null;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        QMUIStatusBarHelper.translucent(this);
        initBackGrd();
    }

    @Override
    protected void initBtn() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initBackGrd(){
        if (null==sharedPreferences)
            sharedPreferences= getSharedPreferences("data",  MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML){
            background.setBackgroundColor( getResources().getColor(R.color.title_red));
            top.setBackgroundColor( getResources().getColor(R.color.title_red));

        } else{
            background.setBackgroundColor( getResources().getColor(R.color.title_blue));
            top.setBackgroundColor( getResources().getColor(R.color.title_blue));
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
//            Intent intent = new Intent();
//            intent.setAction("action.exit");
//            sendBroadcast(intent);
            finish();
        } else{
            SharedPreferences.Editor editor;
            editor =sharedPreferences.edit();
            editor.putInt("identity", LodingActivity.IDENTIFY_FML);
            editor.commit();

            startActivity(MainActivity2.class);
//            Intent intent = new Intent();
//            intent.setAction("action.exit");
//            sendBroadcast(intent);
            finish();
        }

    }

}
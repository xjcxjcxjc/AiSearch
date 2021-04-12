package com.example.aisearch.ui.volunteer.action.inaction;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.example.aisearch.R;

public class WNaviGuideActivity extends AppCompatActivity {
    WalkNavigateHelper mNaviHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_navi_guide2);
        //获取WalkNavigateHelper实例
         mNaviHelper = WalkNavigateHelper.getInstance();
//获取诱导页面地图展示View
        View view = mNaviHelper.onCreate(WNaviGuideActivity.this);
        if (view != null) {
            setContentView(view);
        }
        mNaviHelper.startWalkNavi(WNaviGuideActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNaviHelper.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNaviHelper.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNaviHelper.quit();
    }
}
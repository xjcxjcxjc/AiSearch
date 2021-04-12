package com.example.aisearch.ui.volunteer.action.inaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.bikenavi.BikeNavigateHelper;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;

public class InActionActivity2 extends InActionActivity {

    private static final String TAG = "InActionActivity2";
    WalkNavigateHelper walkNavigateHelper= WalkNavigateHelper.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_in_action2);

//        initengine();
    }


    private void initengine(){


        if (null!=walkNavigateHelper)
            Log.e(TAG, "initengine: " );

        // 引擎初始化
        walkNavigateHelper.initNaviEngine(this, new IWEngineInitListener() {

            @Override
            public void engineInitSuccess() {
                //引擎初始化成功的回调
                Toast.makeText(InActionActivity2.this,"1111",Toast.LENGTH_SHORT).show();
                routeWalkPlanWithParam();
            }


            @Override
            public void engineInitFail() {
                //引擎初始化失败的回调
                Toast.makeText(InActionActivity2.this,"22222",Toast.LENGTH_SHORT).show();
                BikeNavigateHelper.getInstance().unInitNaviEngine();
            }
        });
    }



    private void routeWalkPlanWithParam() {

        //起终点位置
        LatLng startPt = new LatLng(40.047416, 116.312143);
        LatLng endPt = new LatLng(40.048424, 116.313513);
        //构造WalkNaviLaunchParam
        WalkNaviLaunchParam mParam = new WalkNaviLaunchParam().stPt(startPt).endPt(endPt);


        //发起算路
        walkNavigateHelper.
                routePlanWithParams(mParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                Log.e(TAG, "onRoutePlanStart: "+"发起算路onRoutePlanStart");
                //开始算路的回调
            }



            @Override
            public void onRoutePlanSuccess() {
                //算路成功
                //跳转至诱导页面
                Intent intent = new Intent(InActionActivity2.this, WNaviGuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError walkRoutePlanError) {
                //算路失败的回调
            }
        });

    }
}
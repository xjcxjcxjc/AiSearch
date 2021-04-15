package com.example.aisearch.ui.family.home.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.example.aisearch.R;
import com.example.aisearch.ui.volunteer.home.LostPersonDetailsActivity;
import com.example.aisearch.ui.volunteer.train.dialog.LockDialog;
import com.example.aisearch.util.BaiduMapUtils;
import com.example.aisearch.util.UiUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpActivity4 extends AppCompatActivity {

    @BindView(R.id.bmapView)
    MapView mMapView;
    BaiduMap mBaiduMap=null;

    @BindView(R.id.finish)
    Button finish;

    @BindView(R.id.share)
    ImageView share;


    @BindView(R.id.searchnotice)
    CardView searchnotice;
    @BindView(R.id.success_submit)
    CardView success_submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_submit);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        ButterKnife.bind(this);
        mBaiduMap=mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14f);
        mBaiduMap.setMapStatus(msu);
        BaiduMapUtils.hide(mMapView);
        BaiduMapUtils.centerToMyLocation(mBaiduMap,BaiduMapUtils.mylatitude,BaiduMapUtils.mylongtitude);
//        BaiduMapUtils.centerToMyLocation(mBaiduMap,29.876606,BaiduMapUtils.mylongtitude);



        QMUIStatusBarHelper.translucent(this);
        initMarker();


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.ShareToOthers(HelpActivity4.this);
            }
        });
        searchnotice.setOnClickListener(v -> {
            startActivity(new Intent(HelpActivity4.this, LostPersonDetailsActivity.class));

        });


        success_submit.setOnClickListener(v -> {
            new LockDialog(HelpActivity4.this,() -> {},"行动进入审核").show();
        });
    }

    private void initMarker(){
        LatLng center = new LatLng(BaiduMapUtils.mylatitude,BaiduMapUtils.mylongtitude);

//        //构造CircleOptions对象
//        CircleOptions mCircleOptions = new CircleOptions().center(center)
//                .radius(5400)
//                .fillColor(0xAA679cf8) //填充颜色
//        ; //边框宽和边框颜色
//        //在地图上显示圆
//        Overlay mCircle = mBaiduMap.addOverlay(mCircleOptions);

        View v_temp = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_marker_history, null);//加载自定义的布局
        ImageView imageView = v_temp.findViewById(R.id.marder);
        imageView.setImageResource(R.mipmap.impt);
        BaiduMapUtils.createMarker(v_temp,new LatLng(BaiduMapUtils.mylatitude-0.01,BaiduMapUtils.mylongtitude),null,mBaiduMap);
    }

}
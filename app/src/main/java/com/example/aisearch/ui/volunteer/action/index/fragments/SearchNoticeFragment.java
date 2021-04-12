package com.example.aisearch.ui.volunteer.action.index.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.aisearch.MyOrientationListener;
import com.example.aisearch.R;
import com.example.aisearch.base.myApplication;
import com.example.aisearch.bean.Person;
import com.example.aisearch.ui.volunteer.action.inaction.InActionActivity;
import com.example.aisearch.ui.volunteer.action.index.adapter.SearchNoticeAdapter;
import com.example.aisearch.util.BaiduMapUtils;
import com.example.aisearch.util.datautil.DataUtil;
import com.example.aisearch.util.UiUtils;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundRelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * @Author : XJC
 * @Time : 2021/2/14 14:13
 * @Description : 行动中心
 *
 */
public class SearchNoticeFragment extends Fragment {

    @BindView(R.id.searchnotice_recycleview)
    RecyclerView recyclerView;

    @BindView(R.id.searchnotice_map_weather)
    TextView weather;

    @BindView(R.id.searchnotice_map_temperature)
    TextView temperature;

    @BindView(R.id.searchnotice_map_wind)
    TextView wind;

    @BindView(R.id.searchnotice_map_windgrade)
    TextView windgrade;

    @BindView(R.id.searchnotice_map_personnub)
    TextView personnub;
    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.my_scrollview)
    NestedScrollView my_scrollview;


    BaiduMap mBaiduMap=null;

    private LocationClient mLocationClient;
    public MyLocationListener myListener;
    private LatLng mLastLocationData;
    //方向传感器
    private MyOrientationListener mMyOrientationListener;
    private float mCurrentX;
    private boolean isFirstin = true;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SDKInitializer.initialize(myApplication.getMyContext());
        View view = inflater.inflate(R.layout.fragment_searchnotice, null);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        ButterKnife.bind(this,view);
        initRecycleView(view);
        mBaiduMap=mMapView.getMap();
        initMyLocation();
        initMarkerListener();
        return view;
    }

    protected void init(View view, Bundle savedInstanceState) {
        QMUIRoundRelativeLayout qmuiRoundRelativeLayout=view.findViewById(R.id.searchnotice_map_map);
        qmuiRoundRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),InActionActivity.class);
                startActivity(intent);
            }
        });
        initRecycleView(view);
    }


    private void initRecycleView(View view){
        recyclerView=view.findViewById(R.id.searchnotice_recycleview);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SearchNoticeAdapter(getContext(),initData(),1));

    }


    private List<Person> initData(){
        return DataUtil.initSearchNoticeData(getContext());
    }


    private void initMap(){
        BaiduMapUtils.initSearchNoticeMap(getContext(),mLastLocationData,mBaiduMap);
    }


    //初始化定位
    private void initMyLocation() {
        //缩放地图
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(12f);
        mBaiduMap.setMapStatus(msu);

        // 隐藏百度的LOGO
        View child = mMapView.getChildAt(1);
        if (null!=child  && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        // 不显示地图上比例尺
        mMapView.showScaleControl(false);

        // 不显示地图缩放控件（按钮控制栏）
        mMapView.showZoomControls(false);

        //开启定位
        mBaiduMap.setMyLocationEnabled(true);

        //声明LocationClient类
        mLocationClient = new LocationClient(getContext());

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(true);//设置是否需要地址信息
        option.setScanSpan(1000);


        //设置locationClientOption
        mLocationClient.setLocOption(option);
        myListener = new MyLocationListener();
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);

        initOrientation();

        //开始定位
        mLocationClient.start();
    }
    //传感器
    private void initOrientation() {
        //传感器
        mMyOrientationListener = new MyOrientationListener(getContext());
        mMyOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
    }


    //定位
    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }


            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentX).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();


            mBaiduMap.setMyLocationData(locData);


            //设置定位属性
            MyLocationConfiguration config = new
                    MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.NORMAL, true, null);
            mBaiduMap.setMyLocationConfiguration(config);

            //更新经纬度
            //定位相关
            double mLatitude = location.getLatitude();
            double mLongtitude = location.getLongitude();
            //设置起点
            mLastLocationData = new LatLng(mLatitude, mLongtitude);

            if (isFirstin) {
                BaiduMapUtils.centerToMyLocation(mBaiduMap,location.getLatitude(),location.getLongitude());
                initMap();

                //marker动画
//              startMarkerTransform(center_y-radius,center_x,mMarkerA);
                isFirstin = false;
            }
        }
    }

    private void initMarkerListener(){

        /**
         * 设置marker的点击事件
         */
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            //若响应点击事件，返回true，否则返回false
            //默认返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker!=null)
                    UiUtils.showMsg(getContext(),"latitude"+Double.toString(marker.getPosition().latitude-mLastLocationData.latitude)+
                            "longitude"+Double.toString(marker.getPosition().longitude-mLastLocationData.longitude));
                return true;
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();

        //开启定位的允许
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();

            //开启方向传感器
            mMyOrientationListener.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    @Override
    public void onStop() {
        super.onStop();
        //停止定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        //停止方向传感器
        mMyOrientationListener.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }


}

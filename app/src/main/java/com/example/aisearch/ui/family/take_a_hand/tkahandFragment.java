package com.example.aisearch.ui.family.take_a_hand;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.aisearch.R;
import com.example.aisearch.base.myApplication;
import com.example.aisearch.bean.Person;
import com.example.aisearch.bean.family.GetConfigReq;
import com.example.aisearch.ui.family.take_a_hand.help.HelpActivity;
import com.example.aisearch.ui.volunteer.action.index.SearchActivity;
import com.example.aisearch.ui.volunteer.action.index.fragments.FullyLinearLayoutManager;
import com.example.aisearch.util.CommonPopWindow;
import com.example.aisearch.util.PickerScrollView;
import com.example.aisearch.util.datautil.DataUtil;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * @Author : XJC
 * @Time : 2021/2/26 6:19
 * @Description :
 *
 */
public class tkahandFragment extends Fragment implements View.OnClickListener, CommonPopWindow.ViewClickListener{
//    @BindView(R.id.hand_recycleview)
//    RecyclerView recyclerView;

    @BindView(R.id.hand_faceidetify)
    ImageView hand_faceidetify;
//    @BindView(R.id.hand_add)
//    ImageView hand_add;
    @BindView(R.id.hand_help)
    ImageView hand_help;
    @BindView(R.id.hand_help2)
    ImageView hand_help2;
    @BindView(R.id.hand_topbar_city)
    TextView hand_topbar_city;

    @BindView(R.id.hand_topbar_locationbtn)
    Button hand_topbar_locationbtn;
    @BindView(R.id.hand_topbar_talkbtn)
    Button hand_topbar_talkbtn;



    @BindView(R.id.hand_topbar_searchbtn)
    QMUIRoundButton qmuiRoundButtonl;

//    @BindView(R.id.index_topbar_rb1)
//    RadioButton index_topbar_rb1;
//    @BindView(R.id.index_topbar_rb2)
//    RadioButton index_topbar_rb2;

//    Fragment cliamPerson;
//    Fragment lostPerson;

    @BindView(R.id.hand_recycleview)
    RecyclerView recyclerView;
    private CliamAdapter cliamAdapter;

    private List<GetConfigReq.DatasBean> datasBeanList1;
    private List<GetConfigReq.DatasBean> datasBeanList2;
    private String categoryName;
//    private CliamAdapter cliamAdapter;



//    private LocationClient mLocationClient;
//    private LatLng mLastLocationData;
    //方向传感器
//    private MyOrientationListener mMyOrientationListener;
//    private float mCurrentX;
//    private boolean isFirstin = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SDKInitializer.initialize(myApplication.getMyContext());
        View view = inflater.inflate(R.layout.fragment_take_a_hand, null);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        ButterKnife.bind(this,view);
        initRecycleView(view);
//        initMyLocation();
//        initMarkerListener();
        initBtn();
        initData();
        initListener();
        return view;
    }


    private void initRecycleView(View view){
        cliamAdapter=new CliamAdapter(getContext(),initData2(),0);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        recyclerView.setAdapter(cliamAdapter);
    }


    private List<Person> initData2(){
        return DataUtil.initClaimNoticeData(getContext());
    }

    private void initBtn( ){
        hand_faceidetify.setOnClickListener(this);
//        hand_add.setOnClickListener(this);
        hand_help.setOnClickListener(this);
        hand_topbar_talkbtn.setOnClickListener(this);
        qmuiRoundButtonl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });

        hand_help2.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), ReleaseSearchActivity.class));
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hand_faceidetify:
                startActivity(new Intent(getContext(), ComparisonActivity.class));
//                startActivity(new Intent(getContext(), PersonReIdentificationActivity.class));
                break;
//            case R.id.hand_add:
//                startActivity(new Intent(getContext(), ReleaseSearchActivity.class));
//                break;
            case R.id.hand_help:
                startActivity(new Intent(getContext(), HelpActivity.class));
                break;
            case R.id.hand_topbar_locationbtn:
                setAddressSelectorPopup(v);
                break;
            case R.id.hand_topbar_talkbtn:
                startActivity(new Intent(getContext(), MsgActivity.class));
                break;
        }

    }

    private void initListener() {
        hand_topbar_locationbtn.setOnClickListener(this);

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
    public void getChildView(final PopupWindow mPopupWindow, View view, int mLayoutResId) {
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


//    @OnClick(R.id.index_topbar_rb1)
//    //显示第一个fragment
//    protected void initFragment1(){
//        //开启事务，fragment的控制是由事务来实现的
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
//        if(cliamPerson == null){
//            cliamPerson = new CliamFragment();
//            transaction.add(R.id.actiondetails_fl, cliamPerson);
//        }
//        //隐藏所有fragment
//        hideFragment(transaction);
//        //显示需要显示的fragment
//        transaction.show(cliamPerson);
//
//        //提交事务
//        transaction.commit();
//    }
//
//    @OnClick(R.id.index_topbar_rb2)
//    //显示第一个fragment
//    protected void initFragment2(){
//        //开启事务，fragment的控制是由事务来实现的
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
//        if(lostPerson == null){
//            lostPerson = new DetailsHostoryFragmet();
//            transaction.add(R.id.actiondetails_fl, lostPerson);
//        }
//        //隐藏所有fragment
//        hideFragment(transaction);
//        //显示需要显示的fragment
//        transaction.show(lostPerson);
//
//        //提交事务
//        transaction.commit();
//    }
//
//
//    //隐藏所有的fragment
//    private void hideFragment(FragmentTransaction transaction){
//        if(cliamPerson != null){
//            transaction.hide(cliamPerson);
//        }
//        if(lostPerson != null){
//            transaction.hide(lostPerson);
//        }
//    }

//    private void initMap(){
//        LatLng center1 = new LatLng(mLastLocationData.latitude-0.0576690,mLastLocationData.longitude-0.11254665);
//        LatLng center2 = new LatLng(mLastLocationData.latitude-0.06622078,mLastLocationData.longitude-0.01235463 );
//        LatLng center3 = new LatLng(mLastLocationData.latitude+0.06615329,mLastLocationData.longitude-0.02422222);
//        LatLng center4 = new LatLng(mLastLocationData.latitude-0.0270635,mLastLocationData.longitude+0.11326923);
//        LatLng center5 = new LatLng(mLastLocationData.latitude-0.098580697,mLastLocationData.longitude+0.1135585);
//
//
//        DrawMarker(R.mipmap.marker3, center1);
//        DrawMarker(R.mipmap.marker3, center2);
//        DrawMarker(R.mipmap.marker3, center3);
//        DrawMarker(R.mipmap.marker3, center4);
//        DrawMarker(R.mipmap.marker3, center5);
//
//
//        LatLng center6 = new LatLng(mLastLocationData.latitude+0.039117432,mLastLocationData.longitude-0.13638154);
//        LatLng center7 = new LatLng(mLastLocationData.latitude-0.088011,mLastLocationData.longitude+0.05390830);
//        LatLng center8 = new LatLng(mLastLocationData.latitude+0.002585311,mLastLocationData.longitude+0.0946422482);
//        DrawMarker(R.mipmap.marker1, center6);
//        DrawMarker(R.mipmap.marker1, center7);
//        DrawMarker(R.mipmap.marker2, center8);
//
//
//        //构造CircleOptions对象
//        CircleOptions mCircleOptions = new CircleOptions().center(center6)
//                .radius(7000)
//                .fillColor(0x36d81e06); //边框宽和边框颜色
//        //构造CircleOptions对象
//        CircleOptions mCircleOptions2 = new CircleOptions().center(center7)
//                .radius(7000)
//                .fillColor(0x36d81e06); //边框宽和边框颜色
//        //构造CircleOptions对象
//        CircleOptions mCircleOptions3= new CircleOptions().center(center8)
//                .radius(7000)
//                .fillColor(0x36df8226); //边框宽和边框颜色

//        mBaiduMap.addOverlay(mCircleOptions);
//        mBaiduMap.addOverlay(mCircleOptions2);
//        mBaiduMap.addOverlay(mCircleOptions3);
//    }



//    private void DrawMarker(@DrawableRes int resId, LatLng latLng){
//        View v_temp = LayoutInflater.from(getContext()).inflate(R.layout.item_volunteer_marker, null);//加载自定义的布局
//        ImageView imageView = v_temp.findViewById(R.id.volunteer);
//        imageView.setImageResource(resId);
//        BaiduMapUtils.createMarker(v_temp,latLng,null,mBaiduMap);
//
//
//    }

//    //初始化定位
//    private void initMyLocation() {
//        //缩放地图
//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(12f);
//        mBaiduMap.setMapStatus(msu);
//
//        // 隐藏百度的LOGO
//        View child = mMapView.getChildAt(1);
//        if (null!=child  && (child instanceof ImageView || child instanceof ZoomControls)) {
//            child.setVisibility(View.INVISIBLE);
//        }
//        // 不显示地图上比例尺
//        mMapView.showScaleControl(false);
//
//        // 不显示地图缩放控件（按钮控制栏）
//        mMapView.showZoomControls(false);
//
//        //开启定位
//        mBaiduMap.setMyLocationEnabled(true);
//
//        //声明LocationClient类
//        mLocationClient = new LocationClient(getContext());
//
//        //通过LocationClientOption设置LocationClient相关参数
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setIsNeedAddress(true);//设置是否需要地址信息
//        option.setScanSpan(1000);
//
//
//        //设置locationClientOption
//        mLocationClient.setLocOption(option);
//        myListener = new MyLocationListener();
//        //注册监听函数
//        mLocationClient.registerLocationListener(myListener);
//
//        initOrientation();
//
//        //开始定位
//        mLocationClient.start();
//    }
//    //传感器
//    private void initOrientation() {
//        //传感器
//        mMyOrientationListener = new MyOrientationListener(getContext());
//        mMyOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
//            @Override
//            public void onOrientationChanged(float x) {
//                mCurrentX = x;
//            }
//        });
//    }
//
//
//    //定位
//    private class MyLocationListener extends BDAbstractLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //mapView 销毁后不在处理新接收的位置
//            if (location == null || mMapView == null){
//                return;
//            }
//
//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(mCurrentX).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//
//
//            mBaiduMap.setMyLocationData(locData);
//
//
//            //设置定位属性
//            MyLocationConfiguration config = new
//                    MyLocationConfiguration(
//                    MyLocationConfiguration.LocationMode.NORMAL, true, null);
//            mBaiduMap.setMyLocationConfiguration(config);
//
//            //更新经纬度
//            //定位相关
//            double mLatitude = location.getLatitude();
//            double mLongtitude = location.getLongitude();
//            //设置起点
//            mLastLocationData = new LatLng(mLatitude, mLongtitude);
//
//            if (isFirstin) {
//                BaiduMapUtils.centerToMyLocation(mBaiduMap,location.getLatitude(),location.getLongitude());
//                initMap();
//
//                //marker动画
////              startMarkerTransform(center_y-radius,center_x,mMarkerA);
//                isFirstin = false;
//            }
//        }
//    }
//
//    private void initMarkerListener(){
//
//        /**
//         * 设置marker的点击事件
//         */
//        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            //若响应点击事件，返回true，否则返回false
//            //默认返回false
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                if (marker!=null)
//                    UiUtils.showMsg(getContext(),"latitude"+Double.toString(marker.getPosition().latitude-mLastLocationData.latitude)+
//                            "longitude"+Double.toString(marker.getPosition().longitude-mLastLocationData.longitude));
//                return true;
//            }
//        });
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        //开启定位的允许
//        mBaiduMap.setMyLocationEnabled(true);
//        if (!mLocationClient.isStarted()) {
//            mLocationClient.start();
//
//            //开启方向传感器
//            mMyOrientationListener.start();
//        }
//    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        mMapView.onResume();
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        mMapView.onPause();
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        //停止定位
//        mBaiduMap.setMyLocationEnabled(false);
//        mLocationClient.stop();
//        //停止方向传感器
//        mMyOrientationListener.stop();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mBaiduMap.setMyLocationEnabled(false);
//        mMapView.onDestroy();
//        mMapView = null;
//    }



}

package com.example.aisearch.ui.family.home;

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
import com.example.aisearch.bean.util.Person;
import com.example.aisearch.bean.util.family.GetConfigReq;
import com.example.aisearch.ui.family.home.help.HelpActivity;
import com.example.aisearch.ui.volunteer.home.index.SearchActivity;
import com.example.aisearch.ui.volunteer.home.index.fragments.FullyLinearLayoutManager;
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
    //???????????????
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
        //??????????????????????????????
        String response = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"  0\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"1\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"2\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"3\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"4\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"5\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"6\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"7\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"8\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"9\",\"categoryName\":\"??????\",\"state\":\"1\"}" +
                "]}";

        String response2 = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"  0\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"1\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"2\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"3\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"4\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"5\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"6\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"7\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"8\",\"categoryName\":\"??????\",\"state\":\"1\"}," +
                "{\"ID\":\"9\",\"categoryName\":\"??????\",\"state\":\"1\"}" +
                "]}";

        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        GetConfigReq getConfigReq2 = new Gson().fromJson(response2, GetConfigReq.class);
        //0??????????????????
        if (getConfigReq.getRet() == 0) {
            //????????????????????????
            datasBeanList1 = getConfigReq.getDatas();
            datasBeanList2 = getConfigReq2.getDatas();
        }


    }


    /**
     * ?????????????????????????????????
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

                // ????????????????????????????????????
                addressSelector1.setData(datasBeanList1);
                addressSelector1.setSelected(0);
                addressSelector2.setData(datasBeanList2);
                addressSelector2.setSelected(0);


                //????????????
                addressSelector2.setOnSelectListener(new PickerScrollView.onSelectListener() {
                    @Override
                    public void onSelect(GetConfigReq.DatasBean pickers) {
                        categoryName = pickers.getCategoryName();
                    }
                });



                //????????????
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
//    //???????????????fragment
//    protected void initFragment1(){
//        //???????????????fragment?????????????????????????????????
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//        //??????????????????add???????????????fragment?????????????????????????????????null???new??????
//        if(cliamPerson == null){
//            cliamPerson = new CliamFragment();
//            transaction.add(R.id.actiondetails_fl, cliamPerson);
//        }
//        //????????????fragment
//        hideFragment(transaction);
//        //?????????????????????fragment
//        transaction.show(cliamPerson);
//
//        //????????????
//        transaction.commit();
//    }
//
//    @OnClick(R.id.index_topbar_rb2)
//    //???????????????fragment
//    protected void initFragment2(){
//        //???????????????fragment?????????????????????????????????
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//        //??????????????????add???????????????fragment?????????????????????????????????null???new??????
//        if(lostPerson == null){
//            lostPerson = new DetailsHostoryFragmet();
//            transaction.add(R.id.actiondetails_fl, lostPerson);
//        }
//        //????????????fragment
//        hideFragment(transaction);
//        //?????????????????????fragment
//        transaction.show(lostPerson);
//
//        //????????????
//        transaction.commit();
//    }
//
//
//    //???????????????fragment
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
//        //??????CircleOptions??????
//        CircleOptions mCircleOptions = new CircleOptions().center(center6)
//                .radius(7000)
//                .fillColor(0x36d81e06); //????????????????????????
//        //??????CircleOptions??????
//        CircleOptions mCircleOptions2 = new CircleOptions().center(center7)
//                .radius(7000)
//                .fillColor(0x36d81e06); //????????????????????????
//        //??????CircleOptions??????
//        CircleOptions mCircleOptions3= new CircleOptions().center(center8)
//                .radius(7000)
//                .fillColor(0x36df8226); //????????????????????????

//        mBaiduMap.addOverlay(mCircleOptions);
//        mBaiduMap.addOverlay(mCircleOptions2);
//        mBaiduMap.addOverlay(mCircleOptions3);
//    }



//    private void DrawMarker(@DrawableRes int resId, LatLng latLng){
//        View v_temp = LayoutInflater.from(getContext()).inflate(R.layout.item_volunteer_marker, null);//????????????????????????
//        ImageView imageView = v_temp.findViewById(R.id.volunteer);
//        imageView.setImageResource(resId);
//        BaiduMapUtils.createMarker(v_temp,latLng,null,mBaiduMap);
//
//
//    }

//    //???????????????
//    private void initMyLocation() {
//        //????????????
//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(12f);
//        mBaiduMap.setMapStatus(msu);
//
//        // ???????????????LOGO
//        View child = mMapView.getChildAt(1);
//        if (null!=child  && (child instanceof ImageView || child instanceof ZoomControls)) {
//            child.setVisibility(View.INVISIBLE);
//        }
//        // ???????????????????????????
//        mMapView.showScaleControl(false);
//
//        // ????????????????????????????????????????????????
//        mMapView.showZoomControls(false);
//
//        //????????????
//        mBaiduMap.setMyLocationEnabled(true);
//
//        //??????LocationClient???
//        mLocationClient = new LocationClient(getContext());
//
//        //??????LocationClientOption??????LocationClient????????????
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // ??????gps
//        option.setCoorType("bd09ll"); // ??????????????????
//        option.setIsNeedAddress(true);//??????????????????????????????
//        option.setScanSpan(1000);
//
//
//        //??????locationClientOption
//        mLocationClient.setLocOption(option);
//        myListener = new MyLocationListener();
//        //??????????????????
//        mLocationClient.registerLocationListener(myListener);
//
//        initOrientation();
//
//        //????????????
//        mLocationClient.start();
//    }
//    //?????????
//    private void initOrientation() {
//        //?????????
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
//    //??????
//    private class MyLocationListener extends BDAbstractLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //mapView ???????????????????????????????????????
//            if (location == null || mMapView == null){
//                return;
//            }
//
//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // ?????????????????????????????????????????????????????????0-360
//                    .direction(mCurrentX).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//
//
//            mBaiduMap.setMyLocationData(locData);
//
//
//            //??????????????????
//            MyLocationConfiguration config = new
//                    MyLocationConfiguration(
//                    MyLocationConfiguration.LocationMode.NORMAL, true, null);
//            mBaiduMap.setMyLocationConfiguration(config);
//
//            //???????????????
//            //????????????
//            double mLatitude = location.getLatitude();
//            double mLongtitude = location.getLongitude();
//            //????????????
//            mLastLocationData = new LatLng(mLatitude, mLongtitude);
//
//            if (isFirstin) {
//                BaiduMapUtils.centerToMyLocation(mBaiduMap,location.getLatitude(),location.getLongitude());
//                initMap();
//
//                //marker??????
////              startMarkerTransform(center_y-radius,center_x,mMarkerA);
//                isFirstin = false;
//            }
//        }
//    }
//
//    private void initMarkerListener(){
//
//        /**
//         * ??????marker???????????????
//         */
//        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            //??????????????????????????????true???????????????false
//            //????????????false
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
//        //?????????????????????
//        mBaiduMap.setMyLocationEnabled(true);
//        if (!mLocationClient.isStarted()) {
//            mLocationClient.start();
//
//            //?????????????????????
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
//        //????????????
//        mBaiduMap.setMyLocationEnabled(false);
//        mLocationClient.stop();
//        //?????????????????????
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

package com.example.aisearch.ui.volunteer.action.beforeaction;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.ZoomControls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.Transformation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.aisearch.R;
import com.example.aisearch.ui.volunteer.action.ClueActivity;
import com.example.aisearch.ui.volunteer.action.inaction.InActionActivity;
import com.example.aisearch.ui.volunteer.action.inaction.InActionUtils;
import com.example.aisearch.ui.volunteer.action.zwactivity.chat.ChatActivity;
import com.example.aisearch.util.BaiduMapUtils;
import com.example.aisearch.util.UiUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

public class BeforeMainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private MapView mMapView = null;
    private LocationClient mLocationClient;
    private  BDLocationListener mBDLocationListener;
    private BaiduMap mBaiduMap = null;
    private LatLng myLatLng = null;
    private OverlayOptions options;
    private BitmapDescriptor bitmap;
    private Marker mMarkerC;
    private String permissions[] = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
    };
    private String Tag = "MainActivity";
    private static final int scu = 101;
    private ImageView mIvAdd;
    private PopupWindow popupWindow;
    private Button start;
    private ImageView team;
    private boolean isFirstin = true;

    @BindView(R.id.navigation)
    Button navigate;

    @BindView(R.id.gather)
    Button gather;

    @BindView(R.id.iv_time)
    ImageView iv_time;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main2);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        ButterKnife.bind(this);

//        startActivity(new Intent(BeforeMainActivity.this,InActionActivity.class));
//        finish();

        request();
        initView();
        initData();
        initListener();
        initAnim();
        startAnim();
        initMarkerListener();
        initBtn();
        View.OnClickListener popClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPopupWindow();
                //popupWindow.showAsDropDown(v);
                mIvAdd.setImageResource(R.mipmap.add_checked);
                popupWindow.showAsDropDown(v, 0, 0);
                popupWindow.showAtLocation(findViewById(R.id.iv_add),
                        Gravity.CENTER, 0, 0);
            }
        };
       mIvAdd.setOnClickListener(popClick);

       start=findViewById(R.id.iv_startbtn);
       start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(BeforeMainActivity.this,InActionActivity.class);
                startActivity(intent);
                finish();
            }
       });
    }


    private void initBtn(){
        navigate.setOnClickListener(v -> {
            Toast.makeText(BeforeMainActivity.this,"1111111",Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("baidumap://map/direction?destination=latlng:"+ StaticInfo.gatherLocation.getLatLng().latitude+","+ StaticInfo.gatherLocation.getLatLng().longitude+"|name:"+ StaticInfo.gatherLocation.getName()+"&mode=driving");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));


        });

        gather.setOnClickListener(v -> {
            /**
             * 集合点的dialog
             */
            DialogGather dialogGather = new DialogGather(BeforeMainActivity.this,() -> {
//                    UiUtils.showMsg(BeforeMainActivity.this,"hhhh");

                View v_temp = LayoutInflater.from(BeforeMainActivity.this).inflate(R.layout.item_volunteer_marker, null);//加载自定义的布局
                ImageView imageView = v_temp.findViewById(R.id.volunteer);
                imageView.setImageResource(R.mipmap.volunteer_jihe);
                BaiduMapUtils.createMarker(v_temp,new LatLng(myLatLng.latitude+0.006446,myLatLng.longitude+0.010344),null,mBaiduMap);

            });
            dialogGather.showDialog();
        });


        iv_time.setOnClickListener(v -> {
            startActivity(new Intent(BeforeMainActivity.this, ClueActivity.class));

        });

    }


    private void initAnim() {
        //通过LatLng列表构造Transformation对象
        Transformation mTransforma = new Transformation(new LatLng(30.067028,122.168723), new LatLng(30.067607,122.167625),new LatLng(30.067716,122.169633));

        //动画执行时间
        mTransforma.setDuration(8000);

        //动画重复模式
        mTransforma.setRepeatMode(Animation.RepeatMode.REVERSE);
        //设置动画
        bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.mark1);
        options = new MarkerOptions()
                .position(new LatLng(30.067028,122.168723))//设置位置
                .icon(bitmap)//设置图标样式
                .zIndex(9) // 设置marker所在层级
                .draggable(false); // 设置手势拖拽;
        //添加marker
        mMarkerC = (Marker) mBaiduMap.addOverlay(options);
        mMarkerC.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.mark1));
        mMarkerC.setAnimation(mTransforma);
    }
    private void startAnim(){
        mMarkerC.startAnimation();
    }


    private void initView() {
        mMapView =  findViewById(R.id.bmapView);
        mIvAdd = findViewById(R.id.iv_add);
        hide();
        QMUIStatusBarHelper.translucent(this);

        team=  findViewById(R.id.iv_team);
        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeforeMainActivity.this, ChatActivity.class));
            }
        });
    }
    private void initData() {
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);

    }


    private void initListener() {
        /**
         *定位
         */
        mLocationClient = new LocationClient(getApplicationContext());
        mBDLocationListener = new MyBDLocationListener();
        mLocationClient.registerLocationListener(mBDLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        option.setCoorType("bd09ll");
        option.setScanSpan(10000);// 设置发起定位请求的时间间隔 单位ms
        option.setIsNeedAddress(true);
        option.setNeedDeviceDirect(true);
        option.setIsNeedLocationPoiList(true); //POI
        mLocationClient.setLocOption(option);
        mLocationClient.start();


    }

    /**
     * 隐藏百度地图的logo 放大缩小按钮 比例尺 指南针
     */
    private void hide() {
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)){
            child.setVisibility(View.INVISIBLE);
        }
        mMapView.showScaleControl(false);
        mMapView.showZoomControls(false);
    }

    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            mIvAdd.setImageResource(R.mipmap.add_nochecked);
            return;
        } else {
            initPopuptWindow();
        }
    }


    protected void initPopuptWindow() {

        View popupWindow_view = getLayoutInflater().inflate(R.layout.dialog_mapitem, null,
                false);

        popupWindow = new PopupWindow(popupWindow_view, 90, 420, true);

//        popupWindow.setAnimationStyle(R.style.AnimationFade2);

        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (popupWindow != null && popupWindow.isShowing()) {
                    mIvAdd.setImageResource(R.mipmap.add_nochecked);
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;

            }
        });

        ImageView mIvNavigation =popupWindow_view.findViewById(R.id.iv_navigation);
        mIvNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Toast.makeText(BeforeMainActivity.this,"1111111",Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse("baidumap://map/direction?destination=latlng:"+ StaticInfo.gatherLocation.getLatLng().latitude+","+ StaticInfo.gatherLocation.getLatLng().longitude+"|name:"+ StaticInfo.gatherLocation.getName()+"&mode=driving");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

            }
        });
        ImageView mIvGather = popupWindow_view.findViewById(R.id.iv_gather);
        mIvGather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 集合点的dialog
                 */
                DialogGather dialogGather = new DialogGather(BeforeMainActivity.this,() -> {
//                    UiUtils.showMsg(BeforeMainActivity.this,"hhhh");

                    View v_temp = LayoutInflater.from(BeforeMainActivity.this).inflate(R.layout.item_volunteer_marker, null);//加载自定义的布局
                    ImageView imageView = v_temp.findViewById(R.id.volunteer);
                    imageView.setImageResource(R.mipmap.volunteer_jihe);
                    BaiduMapUtils.createMarker(v_temp,new LatLng(myLatLng.latitude+0.006446,myLatLng.longitude+0.010344),null,mBaiduMap);

                });
                dialogGather.showDialog();
            }
        });
        ImageView mIvSignIn = popupWindow_view.findViewById(R.id.iv_signin);
        mIvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeforeMainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.e("PublishActivity","权限申请成功" + perms.size());

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.e("PublishActivity","权限申请失败" );

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);//将第三方权限框架与activity绑定
    }
    public void request() {
        if (EasyPermissions.hasPermissions(BeforeMainActivity.this, permissions)) {
           // Toast.makeText(MainActivity.this, "已授权 ", Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, "请授予以下权限，以便您有更好的体验", scu, permissions);
        }
    }




    public class MyBDLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();

            mBaiduMap.setMyLocationData(locData);


            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            if (myLatLng==null){
                myLatLng = new LatLng(location.getLatitude(),location.getLongitude());
            }

            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(latLng).zoom(15f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            if (isFirstin) {
                InActionUtils.drawBackGround3(new LatLng(myLatLng.latitude-0.01,myLatLng.longitude),mBaiduMap);
                InActionUtils.drawTeamMatesOnMap(BeforeMainActivity.this,myLatLng.latitude,myLatLng.longitude,mBaiduMap);

                isFirstin = false;
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();

        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMapView.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(mBDLocationListener);
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

                UiUtils.showMsg(BeforeMainActivity.this,"latitude"+Double.toString(marker.getPosition().latitude)+
                        "longitude"+Double.toString(marker.getPosition().longitude));
                return false;
            }
        });




    }



}
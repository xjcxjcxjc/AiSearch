package com.example.aisearch;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
        private MapView mMapView = null;
        private BaiduMap mBaiduMap = null;
        private Context context;

        /* 设置定位代码*/
        //定位相关
        private double mLatitude;
        private double mLongtitude;

        //方向传感器
        private MyOrientationListener mMyOrientationListener;
        private float mCurrentX;
        //自定义图标
        private BitmapDescriptor mIconLocation;
        //定义SDK核心类
        private LocationClient mLocationClient;
        public BDAbstractLocationListener myListener;
        private LatLng mLastLocationData;
        //判断是否是首次定位
        private boolean isFirstin = true;
        /* 设置定位代码 */

        //设置变量target，表示默认定位的地方 默认地点华理
        protected LatLng target = new LatLng(30.83673,121.510342);

        //路线规划相关
        private RoutePlanSearch mSearch = null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            SDKInitializer.initialize(getApplicationContext());
            setContentView(R.layout.activity_test);
            SDKInitializer.setCoordType(CoordType.BD09LL);

            //没有这个HTTP申请无法实现路线规划
            SDKInitializer.setHttpsEnable(true);

            /* 设置定位代码 */
            this.context = this;
            //获取百度地图控件
            mMapView = (MapView) findViewById(R.id.bmapView);
            //获取地图控件对象
            mBaiduMap = mMapView.getMap();
            initMyLocation(); //定位方法
            /* 设置定位代码 */

            //设置地图默认定位位置，地图中心位target的经纬度
            MapStatusUpdate mapStatusUpdate= MapStatusUpdateFactory.newLatLng(target);
            mBaiduMap.setMapStatus(mapStatusUpdate);
            //设置地图缩放比例为15
            mapStatusUpdate = MapStatusUpdateFactory.zoomTo(18);
            mBaiduMap.setMapStatus(mapStatusUpdate);

            //路线规划方法
            initPoutePlan();

            button();
        }

        private void button() {
            //按钮
            ImageView mImage_Loc = (ImageView) findViewById(R.id.image_loc);
            Button mbut_RoutrPlan = (Button) findViewById(R.id.but_RoutrPlan);

            //按钮事件处理
            mImage_Loc.setOnClickListener(this);
            mbut_RoutrPlan.setOnClickListener(this);
        }
        public void onClick(View v){
            SDKInitializer.initialize(getApplicationContext());
            switch (v.getId()){
                case R.id.image_loc:{
                    centerToMyLocation(mLatitude,mLongtitude);
                    break;
                }
                case R.id.but_RoutrPlan:{
                    StarRoute();
                }
            }
        }

        protected void onStart() {
            super.onStart();
            //开启定位图层
            mBaiduMap.setMyLocationEnabled(true);
            if (!mLocationClient.isStarted())
                mLocationClient.start();
            //开启方向传感器
            mMyOrientationListener.start();
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
        protected void onStop() {
            super.onStop();
            //停止定位
            mBaiduMap.setMyLocationEnabled(false);
            mLocationClient.stop();
            //停止方向传感器
            mMyOrientationListener.stop();
        }
        @Override
        protected void onDestroy() {
            super.onDestroy();
            //关闭定位图层
            mBaiduMap.setMyLocationEnabled(false);
            mMapView.onDestroy();
            mMapView = null;
        }

        //定位  通过继承抽象类BDAbstractListener并重写其onReceieveLocation方法来获取定位数据，并将其传给MapView。 1
        private class MyLocationListener extends BDAbstractLocationListener {
            @Override
            public void onReceiveLocation(BDLocation location) {
                //mapView 销毁后不在处理新接收的位置
                if (location == null || mMapView == null) {
                    return;
                }
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(mCurrentX).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                mBaiduMap.setMyLocationData(locData);
                //设置自定义图标
                MyLocationConfiguration config = new MyLocationConfiguration(
                        MyLocationConfiguration.LocationMode.NORMAL, true, mIconLocation);
                mBaiduMap.setMyLocationConfiguration(config);

                //更新经纬度
                mLatitude = location.getLatitude();
                mLongtitude = location.getLongitude();
                //设置起点
                mLastLocationData = new LatLng(mLatitude, mLongtitude);
                if (isFirstin) {
                    centerToMyLocation(location.getLatitude(), location.getLongitude());

                    if (location.getLocType() == BDLocation.TypeGpsLocation) {
                        // GPS定位结果
                        Toast.makeText(context, "定位:" + location.getAddrStr(), Toast.LENGTH_SHORT).show();
                    } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                        // 网络定位结果
                        Toast.makeText(context, "定位:" + location.getAddrStr(), Toast.LENGTH_SHORT).show();
                    } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                        // 离线定位结果
                        Toast.makeText(context, "定位:" + location.getAddrStr(), Toast.LENGTH_SHORT).show();
                    } else if (location.getLocType() == BDLocation.TypeServerError) {
                        Toast.makeText(context, "定位:服务器错误", Toast.LENGTH_SHORT).show();
                    } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                        Toast.makeText(context, "定位:网络错误", Toast.LENGTH_SHORT).show();
                    } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                        Toast.makeText(context, "定位:手机模式错误，请检查是否飞行", Toast.LENGTH_SHORT).show();
                    }
                    isFirstin = false;
                }
            }
        }

        //初始化定位，实现当前位置的定位 发起定位 2
        private void initMyLocation() {
            //缩放地图
            MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
            mBaiduMap.setMapStatus(msu);
            //开启定位图层
            mBaiduMap.setMyLocationEnabled(true);
            //声明LocationClient类 LocationClient类必须在主线程中声明。需要Context类型的参数
            mLocationClient = new LocationClient(getApplicationContext());
            /**
             * 设置定位参数包括：定位模式（高精度定位模式，低功耗定位模式和仅用设备定位模式），
             * 返回坐标类型，是否打开GPS，是否返回地址信息、位置语义化信息、POI信息等等。
             * 高精度定位模式：这种定位模式下，会同时使用网络定位和GPS定位，优先返回最高精度的定位结果；
             * 低功耗定位模式：这种定位模式下，不会使用GPS，只会使用网络定位（Wi-Fi和基站定位）；
             * 仅用设备定位模式：这种定位模式下，不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位。
             */
            //通过LocationClientOption设置LocationClient相关参数
            LocationClientOption option = new LocationClientOption();
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
            );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
            int span = 10000;
            option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            option.setOpenGps(true);//可选，默认false,设置是否使用gps
            option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
            option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
            //设置locationClientOption
            mLocationClient.setLocOption(option);
            myListener = new MyLocationListener();
            //注册监听函数 获取到位置信息时会回调该定位监听器
            mLocationClient.registerLocationListener(myListener);
            //解决定位到几内亚湾的问题
            List<String> permissionList = new ArrayList<>();
//            if (ContextCompat.checkSelfPermission(TestActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
//            }
//            if (ContextCompat.checkSelfPermission(TestActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.READ_PHONE_STATE);
//            }
//            if (ContextCompat.checkSelfPermission(TestActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            }
            if (!permissionList.isEmpty()) {
                String[] permissions = permissionList.toArray(new String[permissionList.size()]);
                ActivityCompat.requestPermissions(TestActivity.this, permissions, 1);
            } else {
                requestLocation();
            }
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
            //初始化图标
//            mIconLocation = BitmapDescriptorFactory.fromResource(R.drawable.navi_map_gps);
            initOrientation();
            //开始定位
            mLocationClient.start();
        }

        //回到当前位置的方法 3
        private void centerToMyLocation(double latitude, double longtitude) {
            mBaiduMap.clear();
            mLastLocationData = new LatLng(latitude, longtitude);
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(mLastLocationData);
            mBaiduMap.animateMapStatus(msu);
        }

        //传感器 4
        private void initOrientation() {
            //传感器
            mMyOrientationListener = new MyOrientationListener(context);
            mMyOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
                @Override
                public void onOrientationChanged(float x) {
                    mCurrentX = x;
                }
            });
        }

        //initMyLocation 中调用的方法，同样用于定位。 5
        private void requestLocation() {
            //开始定位，定位结果会回调到前面注册的监听器中
            mLocationClient.start();
        }

        //路线规划初始化
        private void initPoutePlan() {
            mSearch = RoutePlanSearch.newInstance();
            mSearch.setOnGetRoutePlanResultListener(listener);
        }

        // 路线规划模块
        public OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//                    Toast.makeText(MainActivity.this, "路线规划:未找到结果,检查输入", Toast.LENGTH_SHORT).show();
                    //禁止定位
                    isFirstin = false;
                }
                assert result != null;
                if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                    result.getSuggestAddrInfo();
                    return;
                }
                if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                    mBaiduMap.clear();
                    Toast.makeText(TestActivity.this, "路线规划:搜索完成", Toast.LENGTH_SHORT).show();
//                    WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaiduMap);
//                    overlay.setData(result.getRouteLines().get(0));
//                    overlay.addToMap();
//                    overlay.zoomToSpan();
                }
                //禁止定位
                isFirstin = false;
            }
            @Override
            public void onGetTransitRouteResult(TransitRouteResult var1) {
            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult var1) {
            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult result) {
            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult var1) {
            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult var1) {
            }
        };

        //开始规划，这里实现多种不同的路线规划方式。
        private void StarRoute() {
            SDKInitializer.initialize(getApplicationContext());
            // 设置起、终点信息
            PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "西二旗地铁站");
            PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "百度科技园");
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode)
                    .to(enNode));
        }
    }
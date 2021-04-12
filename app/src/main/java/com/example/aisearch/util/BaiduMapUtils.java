package com.example.aisearch.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomControls;

import androidx.annotation.DrawableRes;

import com.baidu.mapapi.animation.Transformation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.aisearch.R;

import java.io.File;
import java.util.List;

/*
 * @Author : XJC
 * @Time : 2021/2/11 12:43
 * @Description :
 *
 */
public class BaiduMapUtils {



    public static final double mylatitude=29.876018;
    public static final double mylongtitude=121.551318;
    private static PoiSearch mPoiSearch = PoiSearch.newInstance();

    /**
     *
     * @param centerOfCircle 圆心
     * @param radius 半径
     * @param key 关键词
     * @param listener 监听器
     */
    public static void startAround(LatLng centerOfCircle,int radius,String key,OnGetPoiSearchResultListener listener){
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        mPoiSearch.searchNearby(new PoiNearbySearchOption()
                .location(centerOfCircle)
                .radius(radius)
        //支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
        .keyword(key)
                .pageNum(10));
    }

    /**
     *
     * @param city 城市
     * @param key 关键词
     * @param listener 监听器
     */
    public static void startInCity(String city,String key,OnGetPoiSearchResultListener listener){
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        mPoiSearch.searchInCity(new PoiCitySearchOption()
                .city(city) //必填
                .keyword(key) //必填
                .pageNum(10));
    }

    public static Marker createMarker(View v, LatLng latLng, Bundle bundle, BaiduMap mBaiduMap){
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(v);
        OverlayOptions option = new MarkerOptions()
                .position(latLng) //必传参数
                .icon(bitmap) //必传参数
                .draggable(true)
                .zIndex(9)
                .draggable(true)
                .extraInfo(bundle);
        //在地图上添加Marker，并显示
        return (Marker) mBaiduMap.addOverlay(option);
    }

    public static Marker DrawMarker(Context context,@DrawableRes int resId, LatLng latLng, BaiduMap mBaiduMap){
        View v_temp = LayoutInflater.from(context).inflate(R.layout.item_volunteer_marker, null);//加载自定义的布局
        ImageView imageView = v_temp.findViewById(R.id.volunteer);
        imageView.setImageResource(resId);
        return createMarker(v_temp,latLng,null,mBaiduMap);
    }



    /**
     * 打开手机上百度地图开始导航
     */
    public static void startNavigation(Context context,LatLng latLng,String name){
        if (isInstalled()){
            Uri uri = Uri.parse("baidumap://map/direction?destination=latlng:"+
                    latLng.latitude +","+
                    latLng.longitude +"|name:"+
                    name+"&mode=driving");
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }else
            UiUtils.showMsg(context,"没有安装百度地图app");
    }


    /**
     * 根据包名检测某个APP是否安装
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/6/27,13:02
     * <h3>UpdateTime</h3> 2016/6/27,13:02
     * <h3>CreateAuthor</h3>
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     * @return true 安装 false 没有安装
     */
    public static boolean isInstalled() {
        return new File("/data/data/com.baidu.BaiduMap").exists();
    }


    public static void transaction(double x, double y, Marker marker, List<LatLng> latLngs){
        Transformation mTransforma = new Transformation(
                latLngs.get(0),
                latLngs.get(1),
                latLngs.get(2),
                latLngs.get(3),
                latLngs.get(4)
        );
        //动画执行时间
        mTransforma.setDuration(120000);

        //根据开发需要设置动画监听
        //设置动画
        marker.setAnimation(mTransforma);
        //开启动画
        marker.startAnimation();
    }


    //回到定位中心
    public static  void centerToMyLocation(BaiduMap mBaiduMap,double latitude, double longtitude) {
        LatLng mLastLocationData = new LatLng(latitude, longtitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(mLastLocationData);
        mBaiduMap.animateMapStatus(msu);
    }

    /**
     * 隐藏百度地图的logo 放大缩小按钮 比例尺 指南针
     */
    public static void hide(MapView mMapView) {
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)){
            child.setVisibility(View.INVISIBLE);
        }
        mMapView.showScaleControl(false);
        mMapView.showZoomControls(false);
    }

    /**
     *
     * @param key 检索内容
     */
    private void searchBulid(Context context,LatLng centerOfCircle,String key){
        BaiduMapUtils.startAround(centerOfCircle, 300000,key,new OnGetPoiSearchResultListener(){

            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                List<PoiInfo> lists=poiResult.getAllPoi();
                //检索到内容后，开始在地图上画图标
                if (lists!=null){
                    UiUtils.showMsg(context,"已显示周围"+key);
                }else
                    UiUtils.showMsg(context,"周围无检索内容");
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });
    }

    public static void initSearchNoticeMap(Context context, LatLng mLastLocationData, BaiduMap mBaiduMap){
        LatLng center1 = new LatLng(mLastLocationData.latitude-0.0576690,mLastLocationData.longitude-0.11254665);
        LatLng center2 = new LatLng(mLastLocationData.latitude-0.06622078,mLastLocationData.longitude-0.01235463 );
        LatLng center3 = new LatLng(mLastLocationData.latitude+0.06615329,mLastLocationData.longitude-0.02422222);
        LatLng center4 = new LatLng(mLastLocationData.latitude-0.0270635,mLastLocationData.longitude+0.11326923);
        LatLng center5 = new LatLng(mLastLocationData.latitude-0.098580697,mLastLocationData.longitude+0.1135585);

        DrawMarker(context,R.mipmap.marker3, center1,mBaiduMap);
        DrawMarker(context,R.mipmap.marker3, center2,mBaiduMap);
        DrawMarker(context,R.mipmap.marker3, center3,mBaiduMap);
        DrawMarker(context,R.mipmap.marker3, center4,mBaiduMap);
        DrawMarker(context,R.mipmap.marker3, center5,mBaiduMap);
    }


    public static void initActionMap(Context context, LatLng mLastLocationData, BaiduMap mBaiduMap){

        LatLng center1 = new LatLng(mLastLocationData.latitude+0.039117432,mLastLocationData.longitude-0.13638154);
        LatLng center2 = new LatLng(mLastLocationData.latitude-0.088011,mLastLocationData.longitude+0.05390830);
        LatLng center3 = new LatLng(mLastLocationData.latitude+0.002585311,mLastLocationData.longitude+0.0946422482);
        DrawMarker(context,R.mipmap.marker1, center1,mBaiduMap);
        DrawMarker(context,R.mipmap.marker2, center2,mBaiduMap);
        DrawMarker(context,R.mipmap.marker1, center3,mBaiduMap);



        //构造CircleOptions对象
        CircleOptions mCircleOptions = new CircleOptions().center(center1)
                .radius(7000)
                .fillColor(0x36d81e06); //边框宽和边框颜色
        //构造CircleOptions对象
        CircleOptions mCircleOptions2 = new CircleOptions().center(center2)
                .radius(7000)
                .fillColor(0x36d81e06); //边框宽和边框颜色
        //构造CircleOptions对象
        CircleOptions mCircleOptions3= new CircleOptions().center(center3)
                .radius(7000)
                .fillColor(0x36df8226); //边框宽和边框颜色
        mBaiduMap.addOverlay(mCircleOptions);
        mBaiduMap.addOverlay(mCircleOptions2);
        mBaiduMap.addOverlay(mCircleOptions3);
    }



}

package com.example.aisearch.ui.volunteer.home.inaction;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.animation.Transformation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polygon;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.aisearch.R;
import com.example.aisearch.util.BaiduMapUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author : XJC
 * @Time : 2021/3/9 18:25
 * @Description :
 *
 */
public class InActionUtils {

    private static Context mcontext;
    private static BaiduMap baiduMap;


    private static final String TAG = "InActionUtils";
    /**
     * 画出地图上的志愿者
     */
    public static List<Marker> drawVolunteerOnMap(Context context, double center_la, double center_lg, BaiduMap mBaiduMap){
        List markers=new ArrayList();

        mcontext=context;
        baiduMap=mBaiduMap;

        Marker marker1=drawmarkerOnMap(new LatLng(center_la+0.004,center_lg+0.03), R.mipmap.volunteer_bluesky);
        Marker marker2=drawmarkerOnMap(new LatLng(center_la+0.06,center_lg+0.017586),R.mipmap.volunteer_sharp);

        Marker marker3=drawmarkerOnMap(new LatLng(center_la+0.04065,center_lg-0.04596334),R.mipmap.bear);
        Marker marker7=drawmarkerOnMap(new LatLng(center_la-0.024209,center_lg-0.0374307),R.mipmap.volunteer_bigbear);

        Marker marker4=drawmarkerOnMap(new LatLng(center_la-0.05,center_lg-0.03),R.mipmap.volunteer_sun);
        Marker marker5=drawmarkerOnMap(new LatLng(center_la-0.015762,center_lg+0.055254),R.mipmap.volunteer_eagle);
        Marker marker6=drawmarkerOnMap(new LatLng(center_la+0.02854,center_lg+0.054855),R.mipmap.volunteer_cike);
        Marker marker8= drawmarkerOnMap(new LatLng(center_la-0.05,center_lg+0.03),R.mipmap.volunteer_shadow);

        View v_temp = LayoutInflater.from(context).inflate(R.layout.item_marker_notsearch, null);//加载自定义的布局
        ImageView imageView = v_temp.findViewById(R.id.item_img);
        imageView.setImageResource(R.mipmap.marker7);
        BaiduMapUtils.createMarker(v_temp,new LatLng(center_la-0.016773,center_lg-0.0783877),null,mBaiduMap);


//        drawTransaction(marker1,center_la+0.004,center_lg+0.03);
//        drawTransaction(marker2,center_la+0.06,center_lg+0.017586);
//        drawTransaction(marker3,center_la+0.04065,center_lg-0.04596334);
//        drawTransaction(marker4,center_la-0.05,center_lg-0.03);
//
//        drawTransaction(marker5,center_la-0.015762,center_lg+0.055254);
//        drawTransaction(marker6,center_la+0.02854,center_lg+0.054855);
//        drawTransaction(marker7,center_la-0.024209,center_lg-0.0374307);
//        drawTransaction(marker8,center_la-0.05,center_lg+0.03);
        markers.add(marker1);
        markers.add(marker2);
        markers.add(marker3);
        markers.add(marker4);
        markers.add(marker5);
        markers.add(marker6);
        markers.add(marker7);
        markers.add(marker8);

        return markers;
    }

    /**
     * 画出志愿者marker
     */
    private static Marker drawmarkerOnMap(LatLng latLng,int mipresource) {
//        Context context,BaiduMap mBaiduMap
        View v_temp = LayoutInflater.from(mcontext).inflate(R.layout.item_volunteer_marker, null);//加载自定义的布局
        ImageView imageView = v_temp.findViewById(R.id.volunteer);
        imageView.setImageResource(mipresource);

        return BaiduMapUtils.createMarker(v_temp,latLng,null,baiduMap);
    }

    public static Marker drawmarkerOnMap(Context mcontext, BaiduMap baiduMap, LatLng latLng, int mipresource, String text) {
        View v_temp = LayoutInflater.from(mcontext).inflate(R.layout.item_team_marker, null);//加载自定义的布局
        ImageView imageView = v_temp.findViewById(R.id.volunteer);
        imageView.setImageResource(mipresource);
        TextView team_nub= v_temp.findViewById(R.id.team_nub);
        team_nub.setText(text);

        return BaiduMapUtils.createMarker(v_temp,latLng,null,baiduMap);

    }
    /**
     * 画出队伍
     * @param context
     * @param mBaiduMap
     * @return
     */
    public static List<Marker> drawTeamOnMapInAction(Context context, double a, double l, BaiduMap mBaiduMap) {
        List markers = new ArrayList();
        mcontext=context;
        baiduMap=mBaiduMap;

        double center_la=a+0.01;
        double center_lg=l-0.01;

//        markers.add(drawTeamOnMap2(context,center_la+0.0520684,center_lg-0.0088351,mBaiduMap,0,
//                R.mipmap.team_icon2,"  4"));
//        markers.add(drawTeamOnMap2(context,center_la-0.0109709,center_lg-0.06979302,mBaiduMap,1,
//                R.mipmap.team_icon2,"  7"));
//        markers.add(drawTeamOnMap2(context,center_la+0.0026073,center_lg+0.0771302,mBaiduMap,2,
//                R.mipmap.team_icon2,"  1"));
//        markers.add(drawTeamOnMap2(context,center_la-0.07006845,center_lg+0.028351,mBaiduMap,3,
//                R.mipmap.team_icon2,"  5"));
//        markers.add(drawTeamOnMap2(context,center_la,center_lg,mBaiduMap,4,R.mipmap.team_icon1,"  3"));
        markers.add(drawTeamOnMap2(context,center_la+0.0520684,center_lg-0.0088351,mBaiduMap,0,
                R.mipmap.team_icon2,""));
        markers.add(drawTeamOnMap2(context,center_la-0.0109709,center_lg-0.06979302,mBaiduMap,1,
                R.mipmap.team_icon2,"  "));
        markers.add(drawTeamOnMap2(context,center_la+0.0026073,center_lg+0.0771302,mBaiduMap,2,
                R.mipmap.team_icon2,"  "));
        markers.add(drawTeamOnMap2(context,center_la-0.07006845,center_lg+0.028351,mBaiduMap,3,
                R.mipmap.team_icon2,"  "));
        markers.add(drawTeamOnMap2(context,center_la,center_lg,mBaiduMap,4,R.mipmap.team_icon1,"  "));
        return markers;
    }

    /**
     * 画出队伍
     * @param context
     * @param center_la
     * @param center_lg
     * @param mBaiduMap
     * @return
     */
    public static Marker drawTeamOnMap(Context context, double center_la, double center_lg, BaiduMap mBaiduMap) {
        List markers = new ArrayList();
        mcontext=context;
        baiduMap=mBaiduMap;

        Marker marker=drawTeamOnMap2(context,center_la+0.0520684,center_lg-0.0088351,mBaiduMap,0,
                R.mipmap.team_icon1,"4/7");

        markers.add(drawTeamOnMap2(context,center_la-0.0109709,center_lg-0.06979302,mBaiduMap,1,
                R.mipmap.team_icon2,"7/9"));
        markers.add(drawTeamOnMap2(context,center_la+0.0026073,center_lg+0.0771302,mBaiduMap,2,
                R.mipmap.team_icon2,"1/5"));
        markers.add(drawTeamOnMap2(context,center_la-0.06006845,center_lg-0.0058351,mBaiduMap,3,
                R.mipmap.team_icon2,"5/6"));
        drawTeamOnMap2(context,center_la,center_lg,mBaiduMap,4,R.mipmap.team_icon2,"2/4");

        return marker;
    }

    public static Marker drawTeamOnMap2(Context context, double center_la, double center_lg, BaiduMap mBaiduMap,
                                        int position,int Resource,String text) {

        View v_temp = LayoutInflater.from(context).inflate(R.layout.item_volunteer_marker, null);//加载自定义的布局
        ImageView imageView = v_temp.findViewById(R.id.volunteer);
        imageView.setImageResource(Resource);
        TextView team_nub= v_temp.findViewById(R.id.team_nub);
        team_nub.setText(text);
        Bundle bundle=new Bundle();
        bundle.putDouble("latitude",center_la);
        bundle.putDouble("longtitude",center_lg);
        bundle.putString("type","team");


        return BaiduMapUtils.createMarker(v_temp,new LatLng(center_la, center_lg),bundle,baiduMap);


    }



    /**
     * 画出地图上的队友
     */
    public static List<Marker> drawTeamMatesOnMap(Context context, double center_la, double center_lg, BaiduMap mBaiduMap){
        List markers=new ArrayList();

        mcontext=context;
        baiduMap=mBaiduMap;

        Marker marker1=drawmarkerOnMap(new LatLng(center_la+0.01060,center_lg+0.011548), R.mipmap.volunteer_sun);
        Marker marker2=drawmarkerOnMap(new LatLng(center_la-0.00318684,center_lg+0.015726),R.mipmap.volunteer_ck);
        Marker marker3=drawmarkerOnMap(new LatLng(center_la+0.013364,center_lg+0.028026),R.mipmap.volunteer_sharp);
        Marker marker7=drawmarkerOnMap(new LatLng(center_la-0.0137279,center_lg-0.004041),R.mipmap.volunteer_eagle);


        return markers;
    }


    /**
     * 开始动画
     */
    public static boolean drawTransaction(Marker marker,double x,double y){
        int a=0;
        //2<=a<=9
        while(a<2)
            a= (int) (Math.random()*10);
        a+=3;

        // 0.5<=la<=1.2
        double la=0.001*a;
        la*=Math.pow(-1,a);
        a=0;
        while(a<2)
            a= (int) (Math.random()*10);
        a+=3;
        double lg=0.001*a;
        lg*=Math.pow(-1,a);

        double x2=x+la;
        double y2=y+lg;

        startMarkerTransform(x,y,marker,x2,y2);
        return new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawTransaction(marker,x2,y2);
            }
        }, 60000);

    }

    /**
     * 根据两点做出动画
     * @param x
     * @param y
     * @param marker
     * @param x2
     * @param y2
     */
    private static void startMarkerTransform(double x,double y,Marker marker,double x2,double y2){
        double tempx,tempy;
        Transformation mTransforma = new Transformation(
                new LatLng(x,y),
                new LatLng(x2,y2)
                );
        //动画执行时间
        mTransforma.setDuration(60000);
        //根据开发需要设置动画监听
        mTransforma.setRepeatCount(0);
        //设置动画
        marker.setAnimation(mTransforma);
        //开启动画
        marker.startAnimation();
    }

    /**
     * 画出出入点之间的路线
     * @param mBaiduMap
     * @param latLngs
     * @return
     */
    public static Overlay drawRoad(BaiduMap mBaiduMap,List<LatLng> latLngs){
        OverlayOptions mOverlayOptions = new PolylineOptions()
                .width(8)
                .color(0xAA000000)
                .points(latLngs)
                .zIndex(8);
        //在地图上绘制折线
        //mPloyline 折线对象
        Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
        return mPolyline;
    }


    /**
     * 画背景，用图片
     *
     */
    public static void drawBackGround(Context context, double center_la, double center_lg, BaiduMap mBaiduMap){
        View v_temp = LayoutInflater.from(context).inflate(R.layout.item_imgwarp, null);//加载自定义的布局
        ImageView imageView = v_temp.findViewById(R.id.item_img);
        imageView.setImageResource(R.mipmap.background35);



        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(v_temp);
        OverlayOptions option = new MarkerOptions()
                .position(new LatLng(center_la-0.08,center_lg+0.012)) //必传参数
                .icon(bitmap) //必传参数
                .draggable(true)
                .zIndex(1)
                .draggable(true)
                .perspective(true)
                .extraInfo(null);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }


    /**
     * 画背景，用圆
     *
     */
    public static void drawBackGround2(Context context, double center_la, double center_lg, BaiduMap mBaiduMap){

        LatLng location=new LatLng(center_la,center_lg+0.012);

        //构造CircleOptions对象
        CircleOptions mCircleOptions = new CircleOptions().center(location)
                .radius(10000)
                .fillColor(0xAA679cf8);

        //在地图上显示圆
        Overlay mCircle = mBaiduMap.addOverlay(mCircleOptions);

    }

    /**
     * 画背景，用画扇形的方法
     *
     */
    public static void drawBackGround3(LatLng location, BaiduMap mBaiduMap){
        List list =new ArrayList();
        list.addAll(Sector1(30,120,location,10000,6000,5000,0xCC679cf8,mBaiduMap));
        list.addAll(Sector1(120,230,location,10000,5000,7000,0x9f679cf8,mBaiduMap));
        list.addAll(Sector1(230,290,location,10000,7000,6000,0x8d679cf8,mBaiduMap));
        list.addAll(Sector1(290,390,location,10000,6000,6000,0x7e679cf8,mBaiduMap));


        OverlayOptions ooPolygon1= new PolygonOptions().points(list).fillColor(0x66679cf8).zIndex(2);
        Polygon marker = (Polygon)mBaiduMap.addOverlay(ooPolygon1);


    }



    //以画多边形区域的方法画扇形区域 画出以point2点为圆心，半径为radius，夹角从sDegree到eDegree的扇形
    static  List<LatLng> Sector1(float s,float e,LatLng p,double r,double r2,double r3,int color,BaiduMap mBaiduMap) {
        List<LatLng> points = new ArrayList<LatLng>(); //创建构成多边形的点数组
        List<LatLng> corner = new ArrayList<LatLng>(); //内侧的两个点

        float step = (float)((e - s)/50); //根据扇形的总夹角确定每步夹角度数，最大为10
        LatLng p1=EOffsetBearing(p,r2,s);
        points.add(p1); //内侧的第一个点
        corner.add(p1);
        for (float i = s; i < e + 0.001; i += step) { //循环获取每步的圆弧上点的坐标，存入点数组
            points.add(EOffsetBearing(p, r, i));
        }

        LatLng p2=EOffsetBearing(p,r3,e);
        points.add(p2);
        corner.add(p2);
        //根据构成的点数组以及其他参数画多边形
        OverlayOptions ooPolygon1= new PolygonOptions().points(points).fillColor(color).zIndex(2);
        Polygon marker = (Polygon)mBaiduMap.addOverlay(ooPolygon1);


//        Polygonlist.add(marker);
        return corner;
    }

    //使用数学的方法计算需要画扇形的圆弧上的点坐标
    private static LatLng EOffsetBearing(LatLng point3, double dist, float bearing) {
        double  latConv= DistanceUtil.getDistance(point3, new LatLng(point3.latitude + 0.1 , point3.longitude)) * 10;   //计算1经度与原点的距离
        double  lngConv= DistanceUtil.getDistance(point3, new LatLng(point3.latitude , point3.longitude + 0.1)) * 10;  //计算1纬度与原点的距离
        double lat = dist * Math.cos(bearing * Math.PI / 180) / latConv;  //正弦计算待获取的点的纬度与原点纬度差
        double lng = dist * Math.sin(bearing * Math.PI / 180) / lngConv;  //余弦计算待获取的点的经度与原点经度差
        return new LatLng (point3.latitude + lat,point3.longitude + lng);
    }




}

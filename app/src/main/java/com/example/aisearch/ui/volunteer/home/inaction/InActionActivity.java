package com.example.aisearch.ui.volunteer.home.inaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ZoomControls;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.example.aisearch.MyOrientationListener;
import com.example.aisearch.R;
import com.example.aisearch.bean.util.volunteer.SOSBulid;
import com.example.aisearch.bean.util.volunteer.TeamMates;
import com.example.aisearch.ui.volunteer.home.ClueActivity;
import com.example.aisearch.ui.volunteer.home.index.adapter.TeamMatesAdapter;
import com.example.aisearch.ui.volunteer.home.zwactivity.ChooseFunctionActivity2;
import com.example.aisearch.ui.volunteer.home.zwactivity.PersonReIdentificationActivity;
import com.example.aisearch.ui.volunteer.home.zwactivity.SearchPeopleByImageActivity;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.ChatActivity;
import com.example.aisearch.util.BaiduMapUtils;
import com.example.aisearch.util.ImageSaveUtil;
import com.example.aisearch.util.UiUtils;
import com.google.common.collect.Lists;
import com.qmuiteam.qmui.util.QMUIDirection;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;

/**
 *
 * ??????????????????????????????
 * ???????????????????????????????????????????????????????????????????????????
 *
 */
public class InActionActivity  extends Activity implements View.OnClickListener {


    private static final String TAG = "InActionActivity";
    private static final int STATUS_FOCUS=111;
    private static final int STATUS_NOTFOCUS=222;
    private static final int BULID_COP=330;
    private static final int BULID_HOSPITAL=331;
    private static final int BULID_SHELTER=332;


    private MapView mMapView = null;
    public BaiduMap mBaiduMap = null;
    private Context context;


    //???????????????
    private MyOrientationListener mMyOrientationListener;
    private float mCurrentX;

    //???????????????
    private LocationClient mLocationClient;
    public MyLocationListener myListener;
    protected LatLng mLastLocationData;
    private boolean isFirstin = true;

    //???????????????
    private double initmLatitude;
    private  double initmLongtitude;


    //???
    Overlay mCircle;

    private double radius=0.063;
    List<Marker> markers;
    Map<Marker,List<LatLng>> volunteer=new HashMap<>();

    ArrayList<SOSBulid> cops;
    ArrayList<SOSBulid> hospitals;

    private MapStatus mapStatus;


    /**
     * UI
     */
    private ImageView cop;
    private TextView coptv;
    private ImageView hospital;
    private TextView hospitaltv;
    private ImageView shelter;
    private TextView sheltertv;
    private RelativeLayout team;
    private CardView cv_Identify;
//    CardView cv_Identify;

    @BindView(R.id.imggg)
    ImageView imggg;

    @BindView(R.id.personmsg)
    RelativeLayout personmsg;

    List<Marker> teams=null;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_inaction);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        ButterKnife.bind(this);
        this.context = this;
        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.setOnClickListener(this);
        //????????????????????????
        mBaiduMap = mMapView.getMap();
        initMyLocation();
        initMarkerListener();
        initView();
        button();
        initEditText();
        QMUIStatusBarHelper.translucent(this);
        initMapStatuListener();



        JMessageClient.registerEventReceiver(this);
    }


    int falg=0;
    public void onEvent(MessageEvent event) {
        cn.jpush.im.android.api.model.Message msg = event.getMessage();

        System.out.println("text??????");
        runOnUiThread(
                new Runnable() {
                    @Override
                    public void run(){
                        //????????????????????????
//                        finish();
                        if (falg==0){
                            new FaceDIalog(InActionActivity.this,() -> {
                                for (Marker marker1:teams)
                                    marker1.remove();


                                View v_temp = LayoutInflater.from(context).inflate(R.layout.item_volunteer_marker, null);//????????????????????????
                                ImageView imageView = v_temp.findViewById(R.id.volunteer);
                                imageView.setImageResource(R.mipmap.volunteer_sun);
                                BaiduMapUtils.createMarker(v_temp,new LatLng(mLastLocationData.latitude+0.0281197,
                                        mLastLocationData.longitude+0.0129636679),null,mBaiduMap);


                                View v_temp2 = LayoutInflater.from(context).inflate(R.layout.item_volunteer_marker, null);//????????????????????????
                                ImageView imageView2 = v_temp2.findViewById(R.id.volunteer);
                                imageView2.setImageResource(R.mipmap.background68);
                                BaiduMapUtils.createMarker(v_temp2,new LatLng(mLastLocationData.latitude+0.01687527,
                                        mLastLocationData.longitude+0.00608337),null,mBaiduMap);


                                MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15f);
                                mBaiduMap.setMapStatus(msu);
                            },
                                    "????????????????????????????????????????????????????????????????????????").show();
                            falg=1;
                        }

                    }
                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();

        //?????????????????????
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();

            //?????????????????????
            mMyOrientationListener.start();
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
    protected void onStop() {
        super.onStop();
        //????????????
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        //?????????????????????
        mMyOrientationListener.stop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }

    private void initView() {
        cv_Identify=findViewById(R.id.cv_identify);
        cv_Identify.setOnClickListener(this);
        cop=findViewById(R.id.cop);
        coptv=findViewById(R.id.coptv);

        hospital=findViewById(R.id.hospital);
        hospitaltv=findViewById(R.id.hospitaltv);

        shelter=findViewById(R.id.shelter);
        sheltertv=findViewById(R.id.sheltertv);
//        drawer1=findViewById(R.id.drawer1);


        /**
         * ?????????????????????
         */
//        drawer1.setOnDrawerCloseListener(() -> {});
//        drawer1.setOnDrawerOpenListener(() -> {});


        team=findViewById(R.id.inaction_team);
        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InActionActivity.this, ChatActivity.class));
            }
        });
        tvAudioAll = findViewById(R.id.tv_audio_all);
        tvAudioCancel = findViewById(R.id.tv_audio_cancel);
        tvAudioTeem = findViewById(R.id.tv_audio_teem);
        rlAudioBg = findViewById(R.id.rl_audio_bg);
    }


    private TextView tvAudioCancel;
    private TextView tvAudioTeem;
    private TextView tvAudioAll;
    RelativeLayout rlAudioBg;
    //????????????
    @SuppressLint("ClickableViewAccessibility")
    private void button() {
        Button copBtn=findViewById(R.id.incation_copbtn);
        copBtn.setOnClickListener(this);
        Button hsptBtn=findViewById(R.id.incation_hsptbtn);
        hsptBtn.setOnClickListener(this);
        Button shelterBtn=findViewById(R.id.incation_shelterbtn);
        shelterBtn.setOnClickListener(this);
        Button incation_cluebtn=findViewById(R.id.incation_cluebtn);;
        incation_cluebtn.setOnClickListener(this);
        Button incation_msgbtn=findViewById(R.id.incation_msgbtn);;
        incation_msgbtn.setOnClickListener(this);

        ImageView imageView=findViewById(R.id.inaction_stop);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        final int i=0;
        CardView speak=findViewById(R.id.inaction_speak);
        speak.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        rlAudioBg.setVisibility(View.VISIBLE);
                        tvAudioAll.setVisibility(View.VISIBLE);
                        tvAudioTeem.setVisibility(View.VISIBLE);
                        tvAudioCancel.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(-285<event.getY()&&event.getY()<-70&&event.getX()<-130&&event.getX()>-406)
                        {
                            show(1);
                        }
                        else  if(-400<event.getY()&&event.getY()<-160&&event.getX()<220&&event.getX()>33)
                        {
                            show(2);
                        }
                        else if(-320<event.getY()&&event.getY()<-70&&event.getX()<570&&event.getX()>330)
                        {
                            show(3);
                        }else{
                            show(4);
                        }
                        Log.e("test","event.getX():"+event.getX());
                        Log.e("test","event.getY():"+event.getY());
                        System.out.println("event.getX():"+event.getX());
                        System.out.println("event.getY():"+event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        rlAudioBg.setVisibility(View.GONE);
                        tvAudioAll.setVisibility(View.GONE);
                        tvAudioTeem.setVisibility(View.GONE);
                        tvAudioCancel.setVisibility(View.GONE);
                        if (event.getY()<0){
//                            Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();
                        }else{
//                            Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();
                        }



                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        personmsg.setOnClickListener(v -> {
            getPersonPopupWindow();
            popupWindow.showAtLocation(v, Gravity.LEFT,0,0);
        });

        CardView face=findViewById(R.id.inaction_faceidentify);

        face.setOnClickListener(this);


        Button report=findViewById(R.id.incation_reportbtn);
        report.setOnClickListener(this);

//        Button sos=findViewById(R.id.incation_sosbtn);
//        sos.setOnClickListener(this);

    }

    /**
     * ????????????????????????????????????,????????????ui???????????????
     */
    private int copclick=1;
    private int hospitalclick=1;
    private int sheltercopclick=1;
    @Override
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.incation_copbtn:
                if(copclick==1){
                    initbulidview(STATUS_FOCUS,BULID_COP);
                    copclick=0;
                }else{
                    initbulidview(STATUS_NOTFOCUS,BULID_COP);
                    copclick=1;
                }
                break;
            case R.id.incation_hsptbtn:
                if(hospitalclick==1){
                    initbulidview(STATUS_FOCUS,BULID_HOSPITAL);
                    hospitalclick=0;
                }else{
                    initbulidview(STATUS_NOTFOCUS,BULID_HOSPITAL);
                    hospitalclick=1;
                }
                break;
            case R.id.incation_shelterbtn:
                if(sheltercopclick==1){
                    initbulidview(STATUS_FOCUS,BULID_SHELTER);
                    sheltercopclick=0;
                }else{
                    initbulidview(STATUS_NOTFOCUS,BULID_SHELTER);
                    sheltercopclick=1;
                }
                break;
            case R.id.incation_reportbtn:
                startActivity(new Intent(InActionActivity.this, SearchPeopleByImageActivity.class));
                break;
            case R.id.inaction_faceidentify:
                startActivity(new Intent(InActionActivity.this, ChooseFunctionActivity2.class));
//                startActivity(new Intent(InActionActivity.this, IdentifyActivity.class));???
                break;
            case R.id.cv_identify:
                startActivity(new Intent(InActionActivity.this, PersonReIdentificationActivity.class));

                break;
//            case R.id.incation_sosbtn:
//                Intent intent=new Intent(InActionActivity.this, SOSActivity.class);
//                intent.putExtra("latitude",mLastLocationData.latitude);
//                intent.putExtra("longitude",mLastLocationData.longitude);
//                intent.putExtra("?????????",cops);
//                intent.putExtra("??????",hospitals);
//                startActivity(intent);
//                break;
            case R.id.incation_cluebtn:
                startActivity(new Intent(InActionActivity.this, ClueActivity.class));
                break;
            case R.id.incation_msgbtn:
//                getPersonPopupWindow();
//                popupWindow.showAtLocation(v, Gravity.LEFT,0,0);

                break;
        }



    }



    private void initEditText(){
        EditText editText=findViewById(R.id.inaction_input);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_UP) {
                    //????????????
                    hideKeyBoard();
                    searchAddressInCity("??????",v.getText().toString());

                }
                return true;
            }
        });
    }

    /**
     * ???????????????
     */
    private InputMethodManager mInputMethodManager;
    protected void hideKeyBoard() {
        if (mInputMethodManager == null) {
            mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        mInputMethodManager.hideSoftInputFromWindow(
                Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
    }



    private void initMarkerListener(){
        /**
         * ??????marker???????????????
         */
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            //??????????????????????????????true???????????????false
            //????????????false
            @Override
            public boolean onMarkerClick(Marker marker) {
//                UiUtils.showMsg(InActionActivity.this,"latitude"+Double.toString(marker.getPosition().latitude-29.881895)+
//                        "longitude"+Double.toString(marker.getPosition().longitude-121.488723));

//                UiUtils.showMsg(InActionActivity.this,"latitude"+Double.toString(mLastLocationData.latitude-marker.getPosition().latitude)+
//                        "longitude"+Double.toString(mLastLocationData.longitude-marker.getPosition().longitude));

//                UiUtils.showMsg(InActionActivity.this,"latitude"+Double.toString(marker.getPosition().latitude)+
//                        "longitude"+Double.toString(marker.getPosition().longitude));

//                UiUtils.showMsg(InActionActivity.this,"latitude"+Double.toString(mLastLocationData.latitude)+
//                        "longitude"+Double.toString(mLastLocationData.longitude));

//                MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15f);
//                mBaiduMap.setMapStatus(msu);


                String type= marker.getExtraInfo().getString("type");


                Bundle markerinfo=marker.getExtraInfo();
                if ("team".equals(type)){
                    initBack();

                }else
                    bulidMarkerClick(marker,markerinfo);

                return false;
            }
        });
    }



    /**
     * ????????????????????????
     */
    private void initBack(){

//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15f);
//        mBaiduMap.setMapStatus(msu);
        for (Marker marker1:teams)
            marker1.remove();
        initRoad();
        initTeamMates();

    }

    protected void initRoad(){
        View v_temp = LayoutInflater.from(context).inflate(R.layout.item_imgwarp, null);//????????????????????????
        ImageView imageView = v_temp.findViewById(R.id.item_img);
        imageView.setImageResource(R.mipmap.background62);
        BaiduMapUtils.createMarker(v_temp,new LatLng(mLastLocationData.latitude-0.014014,
                mLastLocationData.longitude+0.012185),null,mBaiduMap);

    }

    protected void initTeamMates(){

    }

    /**
     * ???????????????marker???????????????????????????????????????
     */
    private void bulidMarkerClick(Marker marker,Bundle markerinfo){
        LatLng loc=marker.getPosition();
        int whickView=markerinfo.getInt("whichView");

        /**
         * ???????????????marker:??????marker
         * ???????????????marker:navigate
         * ???????????????marker:call
         * ???????????????marker:??????marker
         *
         */
        switch (whickView){
            case 0:
                marker.remove();
                //???????????????marker????????????
                markerinfo.putInt("whichView",2);
                createView1(loc,markerinfo);
                break;
            case 1:
                marker.remove();
                markerinfo.putInt("whichView",0);
                createView2(loc,markerinfo);
                break;
            case 2:
                markerinfo.putInt("whichView",3);
                marker.setExtraInfo(markerinfo);
//                UiUtils.showMsg(getApplicationContext(),"navigate");
                navigate(marker.getPosition(),markerinfo.getString("name"));
                break;
            case 3:
                markerinfo.putInt("whichView",1);
                marker.setExtraInfo(markerinfo);
//                UiUtils.showMsg(getApplicationContext(),"callPhone");
                callPhone(markerinfo.getString("phone"));
                break;
            default:
                break;
        }
    }


    /**
     * ???????????????marker
     */
    @SuppressLint("SetTextI18n")
    private void createView1(LatLng latLng, Bundle markerinfo){
        String type=markerinfo.getString("type");
        String phone=markerinfo.getString("phone");
        String address=markerinfo.getString("address");

        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_marker_bulidclick, null);//????????????????????????
        TextView addressTv=v.findViewById(R.id.address);
        addressTv.setText("??????:  "+address);
        TextView phoneTv=v.findViewById(R.id.phone);
        phoneTv.setText("??????:  "+phone);
        LinearLayout backgroud =v.findViewById(R.id.marker_backgroud);
        ImageView imageView=v.findViewById(R.id.location);
//        Button navigate=v.findViewById(R.id.marker_navigate);
//        Button call=v.findViewById(R.id.marker_call);

        switch (type){
            case "?????????":
                backgroud.setBackgroundResource(R.mipmap.copclic);
                imageView.setImageResource(R.mipmap.cop3);
                break;
            case "??????":
                backgroud.setBackgroundResource(R.mipmap.hospital_click);
                imageView.setImageResource(R.mipmap.hospital3);
                break;
            case "?????????":
                backgroud.setBackgroundResource(R.mipmap.sheter_click);
                imageView.setImageResource(R.mipmap.house3);
                break;
        }

//        markerinfo.putInt("whichView",0);
        Marker marker= BaiduMapUtils.createMarker(v,latLng,markerinfo,mBaiduMap);
        markers.add(marker);
    }

    /**
     * ??????????????????marker
     */
    @SuppressLint("SetTextI18n")
    private void createView2(LatLng latLng,Bundle markerinfo){
        String type=markerinfo.getString("type");
        String name=markerinfo.getString("name");

        int mipresource=0;
        switch (type){
            case "?????????":
                mipresource=R.mipmap.cop3;
                break;
            case "??????":
                mipresource=R.mipmap.hospital3;
                break;
            case "?????????":
                mipresource=R.mipmap.house3;
                break;
        }
        View v_temp = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_searchbulid_marker, null);//????????????????????????
        ImageView imageView = v_temp.findViewById(R.id.location);
        TextView locationName=v_temp.findViewById(R.id.location_tv);
        imageView.setImageResource(mipresource);
        locationName.setText("  "+name+"  ");

        Marker marker= BaiduMapUtils.createMarker(v_temp,latLng,markerinfo,mBaiduMap);
        markers.add(marker);
    }

    //????????????
    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {

            //mapView ???????????????????????????????????????
            if (location == null || mMapView == null){
                return;
            }

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // ?????????????????????????????????????????????????????????0-360
                    .direction(mCurrentX).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();


            mBaiduMap.setMyLocationData(locData);


            //??????????????????
            MyLocationConfiguration config = new
                    MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.NORMAL, true, null);
            mBaiduMap.setMyLocationConfiguration(config);

            //???????????????
            //????????????
            mLastLocationData = new LatLng(location.getLatitude(), location.getLongitude());

            if (isFirstin) {
                initmLatitude=location.getLatitude();
                initmLongtitude=location.getLongitude();
                mapStatus=mBaiduMap.getMapStatus();
//                View v_temp = LayoutInflater.from(context).inflate(R.layout.item_volunteer_marker, null);//????????????????????????
//                ImageView imageView = v_temp.findViewById(R.id.volunteer);
//                imageView.setImageResource(R.mipmap.volunteer_bigbear);
//                BaiduMapUtils.createMarker(v_temp,new LatLng(mLastLocationData.latitude,
//                        mLastLocationData.longitude),null,mBaiduMap);
//                initmLatitude=29.86928;
//                initmLongtitude=121.54374;

//        121.54374
//        ??????:29.86928


//                drawBackground3(new LatLng(center_la,center_lg),1000,30,60);


                BaiduMapUtils.centerToMyLocation(mBaiduMap,location.getLatitude(), location.getLongitude());
//                List<Marker> list=InActionUtils.drawVolunteerOnMap(getApplicationContext(),center_la,center_lg,mBaiduMap);
//                for (Marker m:list){
//                    volunteer.put(m, Lists.newArrayList(m.getPosition()));
//                }


                InActionUtils.drawBackGround3(new LatLng(initmLatitude,initmLongtitude),mBaiduMap);
                teams=InActionUtils.drawTeamOnMapInAction(InActionActivity.this,initmLatitude,initmLongtitude,mBaiduMap);


//                InActionUtils.drawBackGround2(InActionActivity.this,center_la,center_lg,mBaiduMap);
//                UiUtils.showMsg(InActionActivity.this, InActionUtils.EOffsetBearing(new LatLng(center_la,center_lg),1000,5,mBaiduMap).toString());


                initBuliding();
//                initengine();
//                ListenRoad(null);

                isFirstin = false;
            }
        }
    }


    //???????????????
    private void initMyLocation() {
        //????????????
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(13.5f);
        mBaiduMap.setMapStatus(msu);

        // ???????????????LOGO
        View child = mMapView.getChildAt(1);
        if (null!=child  && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        // ???????????????????????????
        mMapView.showScaleControl(false);

        // ????????????????????????????????????????????????
        mMapView.showZoomControls(false);

        //????????????
        mBaiduMap.setMyLocationEnabled(true);

        //??????LocationClient???
        mLocationClient = new LocationClient(this);

        //??????LocationClientOption??????LocationClient????????????
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // ??????gps
        option.setCoorType("bd09ll"); // ??????????????????
        option.setIsNeedAddress(true);//??????????????????????????????
        option.setScanSpan(1000);


        //??????locationClientOption
        mLocationClient.setLocOption(option);
        myListener = new MyLocationListener();
        //??????????????????
        mLocationClient.registerLocationListener(myListener);


        initOrientation();
        //????????????
        mLocationClient.start();
    }


    //?????????
    private void initOrientation() {
        //?????????
        mMyOrientationListener = new MyOrientationListener(context);
        mMyOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
    }


    /**
     * tools
     * ??????????????????????????????POI??????
     */
    private int viewflag=0;
    private void initbulidview(int status,int buliding){
        if(markers==null)
            markers=new ArrayList<>();
        if(status==STATUS_FOCUS){
            if (viewflag==0)
                mBaiduMap.clear();
            viewflag++;
            switch (buliding){
                case BULID_COP:
                    cop.setImageResource(R.mipmap.cop2);
                    coptv.setTextColor(getResources().getColor(R.color.title_blue));
                    searchBulid("?????????");
                    break;
                case BULID_HOSPITAL:
                    hospital.setImageResource(R.mipmap.hospital2);
                    hospitaltv.setTextColor(getResources().getColor(R.color.hospital_red));
                    searchBulid("??????");
                    break;
                case BULID_SHELTER:
                    shelter.setImageResource(R.mipmap.house2);
                    sheltertv.setTextColor(getResources().getColor(R.color.house_yellow));
                    /**
                     * ????????????????????????marker
                     * ???????????????
                     */
//                    searchBulid("?????????");
                    Bundle bundle= new Bundle();
                    bundle.putString("type","?????????");
                    bundle.putString("phone","0574-87871751");
                    bundle.putString("address","????????????????????????????????????200???");
                    bundle.putString("name","??????????????????");
                    bundle.putInt("whichView",0);



                    LatLng latLng=new LatLng(initmLatitude-0.03,initmLongtitude-0.02);

                    createView2(latLng,bundle);

//
                    break;
                default:
                    break;
            }
        }else if(status==STATUS_NOTFOCUS){
            viewflag--;
            switch (buliding){
                case BULID_COP:
                    cop.setImageResource(R.mipmap.cop1);
                    coptv.setTextColor(getResources().getColor(R.color.text_gray1));
                    clearBulidMarker("?????????");
                    break;
                case BULID_HOSPITAL:
                    hospital.setImageResource(R.mipmap.hospital1);
                    hospitaltv.setTextColor(getResources().getColor(R.color.text_gray1));
                    clearBulidMarker("??????");
                    break;
                case BULID_SHELTER:
                    shelter.setImageResource(R.mipmap.house1);
                    sheltertv.setTextColor(getResources().getColor(R.color.text_gray1));
                    clearBulidMarker("?????????");
                    break;
                default:
                    break;
            }
            if (viewflag==0)
                isFirstin=true;
        }
    }

    /**
     * ????????????????????????
     * @param type
     */
    private void clearBulidMarker(String type){

        for (Marker marker:markers){
            if (marker.getExtraInfo().getString("type").equals(type)){
                marker.remove();
            }
        }
    }


    /**
     *
     * @param key ????????????
     */
    private void searchBulid(String key){
            BaiduMapUtils.startAround(mLastLocationData, 300000,key,new OnGetPoiSearchResultListener(){


                @Override
                public void onGetPoiResult(PoiResult poiResult) {
                    List<PoiInfo> lists=poiResult.getAllPoi();
                    //????????????????????????????????????????????????
                    if (lists!=null){
                        for (int i=0;i<lists.size();i++){
                            PoiInfo info=lists.get(i);
                            drawSearchMarker(info,key);
                        }
                        UiUtils.showMsg(getApplicationContext(),"???????????????"+key);
                    }else
                        UiUtils.showMsg(getApplicationContext(),"?????????????????????");
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



    /**
     * ????????????????????????????????????
     * ????????????key?????????type
     *
     */
    private void drawSearchMarker(PoiInfo info,String type){
        LatLng point = info.getLocation();
        Bundle bundle= new Bundle();
        bundle.putString("type",type);
        bundle.putString("phone",info.getPhoneNum());
        bundle.putString("address",info.getAddress());
        bundle.putString("name",info.getName());
        bundle.putInt("whichView",0);

        createView2(point,bundle);

    }

    /**
     * ??????????????????????????????
     * @param latLng
     * @param name
     */
    private void navigate(LatLng latLng,String name){
        BaiduMapUtils.startNavigation(InActionActivity.this,latLng,name);
    }

    /**
     * ????????????
     * @param phoneNum
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    /**
     * ????????????????????????
     * @param key
     */
    List<Marker> searchMarkers;
    private void searchAddressInCity(String city,String key){

        if (searchMarkers==null)
            searchMarkers=new ArrayList<>();
        else{
            for (Marker marker:searchMarkers){
                marker.remove();
            }
        }

        BaiduMapUtils.startInCity(city,key,new OnGetPoiSearchResultListener(){
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                List<PoiInfo> lists=poiResult.getAllPoi();
                //????????????????????????????????????????????????
                if (lists!=null){
                    for (int i=0;i<lists.size();i++){
                        View v_temp = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_marker_bulidclick3, null);//????????????????????????
                        searchMarkers.add(BaiduMapUtils.createMarker(v_temp,lists.get(i).getLocation(),null,mBaiduMap));
                    }
                    UiUtils.showMsg(getApplicationContext(),"?????????????????????");
                }else
                    UiUtils.showMsg(getApplicationContext(),"?????????");
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

    /**
     * ????????????1
     */
    private void stop(){
        StopDialog dialog = new StopDialog(InActionActivity.this, new StopDialog.ClickCallBack() {
            @Override
            public void onStop() {
                share();
            }

            @Override
            public void onCancel() {
            }
        });
        dialog.show();
    }


    /**
     * ????????????2
     */
    private void share(){
        ShareDialog dialog = new ShareDialog(InActionActivity.this, new ShareDialog.ClickCallBack() {
            @Override
            public void onShare() {
                Bitmap bitmap;
                BitmapDescriptor bitmapdes = BitmapDescriptorFactory.fromResource(R.mipmap.share_photoes);
                bitmap=bitmapdes.getBitmap();
                ImageSaveUtil.saveAlbum(InActionActivity.this,bitmap, Bitmap.CompressFormat.JPEG,100,true);
                UiUtils.openOthers(InActionActivity.this,"com.tencent.mm");
                mBaiduMap.clear();
                finish();
            }




            @Override
            public void onCancel() {

                mBaiduMap.clear();
                finish();
            }
        });
        dialog.show();

    }
    private void initBuliding(){
        initBuliding(mLastLocationData, "?????????");
        initBuliding(mLastLocationData, "??????");
    }

    private List<SOSBulid> initBuliding(LatLng mLastLocationData, String key){
        ArrayList<SOSBulid> sosBulids=new ArrayList<>();
        BaiduMapUtils.startAround(mLastLocationData, 300000,key,new OnGetPoiSearchResultListener(){

            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                List<PoiInfo> lists=poiResult.getAllPoi();
                if(null!=lists){
                    for (PoiInfo info:lists){
                        SOSBulid sosBulid=new SOSBulid(info.getName(),info.getAddress(),info.getPhoneNum(),info.getDistance());
                        sosBulids.add(sosBulid);
                    }
                }
                initBuliding(sosBulids,key);
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
        return sosBulids;
    }

    private void initBuliding(ArrayList<SOSBulid> bulidings, String key){
        if("?????????".equals(key)){
            this.cops=bulidings;
        }else if("??????".equals(key)){
            this.hospitals=bulidings;
        }
    }

    /**
     * ??????voluntter?????????
     */
    private boolean ListenRoad(List<Overlay> Ovs){



        List<Overlay> nowOverlay=new ArrayList<>();
        for (Map.Entry<Marker, List<LatLng>> entry:volunteer.entrySet()){
            Marker marker= entry.getKey();
            List<LatLng> list= entry.getValue();
            LatLng latLng= marker.getPosition();
            list.add(latLng);
            nowOverlay.add(InActionUtils.drawRoad(mBaiduMap,list));
        }

        try {
            if (null!=Ovs)
                for (Overlay v:Ovs)
                    v.remove();
        }catch (Exception e){
            System.out.println(e.toString());
        }


        return new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ListenRoad(nowOverlay);
            }
        }, 1000);

    }



    /**
     * ?????????????????????????????????
     *
     */
    private void initMapStatuListener(){
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

//                Log.e(TAG, "onMapStatusChangeStart: "+mapStatus.zoom);
            }


            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {
//                Log.e(TAG, "onMapStatusChangeStart: "+mapStatus.zoom);
            }


            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
//                Log.e(TAG, "onMapStatusChange: "+mapStatus.zoom);


            }


            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
            }

        });
    }




    View popipWindow_view;
    PopupWindow popupWindow;
    protected  void  initPopupWindow(Context context){
        //???????????????????????????activity_pop_left.xml ????????????
        popipWindow_view = getLayoutInflater().inflate(R.layout.pop_actiondetail,null,false);
        //??????Popupwindow ?????????200???LayoutParams.MATCH_PARENT ??????????????? ViewGroup.LayoutParams.WRAP_CONTENT 1500
        popupWindow = new PopupWindow(popipWindow_view,720, ViewGroup.LayoutParams.WRAP_CONTENT,true);


        //??????????????????
//        popupWindow.setAnimationStyle(R.anim.close_in);
        QMUIViewHelper.slideIn(popipWindow_view,300,null,true, QMUIDirection.LEFT_TO_RIGHT);
        RecyclerView recyclerview =popipWindow_view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        recyclerview.setAdapter(new TeamMatesAdapter(InActionActivity.this,new TeamMates(
                Lists.newArrayList("??????","??????"),
                2,2
        )));

        //????????????????????????
        popipWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popipWindow_view != null && popipWindow_view.isShown()) {
                    popupWindow.dismiss();
//                    QMUIViewHelper.slideOut(popipWindow_view,500,null,true,QMUIDirection.RIGHT_TO_LEFT);
                    imggg.setVisibility(View.VISIBLE);


                    popupWindow = null;
                }
                return false;
            }
        });
    }





    private  void  getPersonPopupWindow(){
        if (null!=popupWindow){
            QMUIViewHelper.slideIn(popipWindow_view,300,null,true, QMUIDirection.LEFT_TO_RIGHT);
            popupWindow.dismiss();
            return;
        }else {
            initPopupWindow(context);
        }
    }





    private boolean suoxiao(){

//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(zoom0);
//        tmBaiduMap.setMapStatus(msu);

//        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(mapStatus.zoom+1));
//
//        return new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                suoxiao();
//            }
//        }, 1000);

        return true;
    }







    public void show(int num){
        if(num==1){
            tvAudioAll.getBackground().setTint(Color.parseColor("#dddddd"));
            tvAudioAll.setTextColor(Color.parseColor("#333333"));
            tvAudioCancel.getBackground().setTint(Color.parseColor("#333333"));
            tvAudioCancel.setTextColor(Color.parseColor("#ffffff"));
            tvAudioTeem.getBackground().setTint(Color.parseColor("#333333"));
            tvAudioTeem.setTextColor(Color.parseColor("#ffffff"));
        }else if(num==2){
            tvAudioCancel.getBackground().setTint(Color.parseColor("#dddddd"));
            tvAudioCancel.setTextColor(Color.parseColor("#333333"));
            tvAudioAll.getBackground().setTint(Color.parseColor("#333333"));
            tvAudioAll.setTextColor(Color.parseColor("#ffffff"));
            tvAudioTeem.getBackground().setTint(Color.parseColor("#333333"));
            tvAudioTeem.setTextColor(Color.parseColor("#ffffff"));
        }else if(num==3){
            tvAudioTeem.getBackground().setTint(Color.parseColor("#dddddd"));
            tvAudioTeem.setTextColor(Color.parseColor("#333333"));
            tvAudioAll.getBackground().setTint(Color.parseColor("#333333"));
            tvAudioAll.setTextColor(Color.parseColor("#ffffff"));
            tvAudioCancel.getBackground().setTint(Color.parseColor("#333333"));
            tvAudioCancel.setTextColor(Color.parseColor("#ffffff"));
        }else{
            tvAudioAll.getBackground().setTint(Color.parseColor("#333333"));
            tvAudioAll.setTextColor(Color.parseColor("#ffffff"));
            tvAudioCancel.getBackground().setTint(Color.parseColor("#333333"));
            tvAudioCancel.setTextColor(Color.parseColor("#ffffff"));
            tvAudioTeem.getBackground().setTint(Color.parseColor("#333333"));
            tvAudioTeem.setTextColor(Color.parseColor("#ffffff"));
        }

    }

}

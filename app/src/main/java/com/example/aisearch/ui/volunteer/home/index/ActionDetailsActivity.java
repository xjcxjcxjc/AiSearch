package com.example.aisearch.ui.volunteer.home.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;
import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.example.aisearch.bean.util.Person;
import com.example.aisearch.ui.volunteer.home.ClueActivity;
import com.example.aisearch.ui.volunteer.home.inaction.InActionUtils;
import com.example.aisearch.ui.volunteer.home.index.fragments.DetailsHostoryFragmet;
import com.example.aisearch.ui.volunteer.home.index.fragments.DetailsImptFragment;
import com.example.aisearch.ui.volunteer.home.index.fragments.DetailsTeamFragmet;
import com.example.aisearch.util.BaiduMapUtils;
import com.example.aisearch.util.UiUtils;
import com.example.aisearch.util.datautil.DataUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionDetailsActivity extends AppCompatActivity {


    @BindView(R.id.actiondetails_rg)
    RadioGroup rg;
    @BindView(R.id.actiondetails_rb1)
    RadioButton rb1;
    @BindView(R.id.actiondetails_rb2)
    RadioButton rb2;
    @BindView(R.id.actiondetails_rb3)
    RadioButton rb3;
    @BindView(R.id.actiondetails_rb4)
    RadioButton rb4;

    @BindView(R.id.share)
    ImageView share;

    @BindView(R.id.bmapView)
    MapView mMapView;

    @BindView(R.id.actionlist_finish)
    Button actionlist_finish;

    BaiduMap mBaiduMap=null;

    DetailsTeamFragmet fragment_Team;
    Fragment fragment_History;
    Fragment fragment_Impt;

    @BindView(R.id.detail_lost_time_detailed)
    TextView pd_losttime;
    @BindView(R.id.pd_name)
    TextView pd_name;
    @BindView(R.id.pd_age)
    TextView pd_age;
    @BindView(R.id.pd_lostplace)
    TextView pd_lostplace;
    @BindView(R.id.pd_feature)
    TextView pd_feature;
    @BindView(R.id.pd_clothes)
    TextView pd_clothes;
    @BindView(R.id.pd_actiontime)
    TextView pd_actiontime;

    LatLng myLastLatin;

    int lastmarker=4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_details2);
        SDKInitializer.setCoordType(CoordType.BD09LL);

        ButterKnife.bind(this);
        mBaiduMap=mMapView.getMap();
        initMap();
        QMUIStatusBarHelper.translucent(this);
        initFragment1();
        initBtn();
        initMsg();
        initMarkerListener();




    }


    private void initBtn(){
        rb1.setOnClickListener(v -> startActivity(new Intent(ActionDetailsActivity.this, ClueActivity.class)));

        actionlist_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTaskRoot())
                    finish();
                else{
                    startActivity(new Intent(ActionDetailsActivity.this, LodingActivity.class));
                    finish();
                }
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.ShareToOthers( ActionDetailsActivity.this);
            }
        });


    }


    /**
     * initMsg of lostPerson
     */
    private void initMsg(){
        Person person = DataUtil.getNoticePerson(this);
//        pd_losttime.setText(person.getTime());
//        pd_name.setText(person.getName());
//        pd_age.setText(person.getAge()+"???");
//        pd_lostplace.setText(person.getLocation());
//        if (null!= person.getFeatures())
//            pd_feature.setText(person.getFeatures());
//        if (null!= person.getClothes())
//            pd_clothes.setText(person.getClothes());
//        pd_actiontime.setText(UiUtils.Action_Time_detailed);


    }



    @Override
    protected void onResume() {
        super.onResume();
        //???activity??????onResume?????????mMapView. onResume ()?????????????????????????????????
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //???activity??????onPause?????????mMapView. onPause ()?????????????????????????????????
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //???activity??????onDestroy?????????mMapView.onDestroy()?????????????????????????????????
        mMapView.onDestroy();
    }

    //??????????????????
    private void centerToMyLocation(double latitude, double longtitude) {
        LatLng mLastLocationData = new LatLng(latitude, longtitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(mLastLocationData);
        mBaiduMap.animateMapStatus(msu);
    }

    @OnClick(R.id.actiondetails_rb2)
    //???????????????fragment
    protected void initFragment1(){
        mBaiduMap.clear();
        initMarker1();
        //???????????????fragment?????????????????????????????????
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //??????????????????add???????????????fragment?????????????????????????????????null???new??????
        if(fragment_Team == null){
            fragment_Team = new DetailsTeamFragmet();
            transaction.add(R.id.actiondetails_fl, fragment_Team);
        }
        //????????????fragment
        hideFragment(transaction);
        //?????????????????????fragment
        transaction.show(fragment_Team);

        //????????????
        transaction.commit();
    }
    @OnClick(R.id.actiondetails_rb3)
    //???????????????fragment
    protected void initFragment2(){
        mBaiduMap.clear();
        initMarker2();
        //???????????????fragment?????????????????????????????????
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //??????????????????add???????????????fragment?????????????????????????????????null???new??????
        if(fragment_History == null){
            fragment_History = new DetailsHostoryFragmet();
            transaction.add(R.id.actiondetails_fl, fragment_History);
        }

        //????????????fragment
        hideFragment(transaction);
        //?????????????????????fragment
        transaction.show(fragment_History);

        //????????????
        transaction.commit();
    }

    @OnClick(R.id.actiondetails_rb4)
    //???????????????fragment
    protected void initFragment3(){
        mBaiduMap.clear();
        initMarker3();

        //???????????????fragment?????????????????????????????????
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //??????????????????add???????????????fragment?????????????????????????????????null???new??????
        if(fragment_Impt == null){
            fragment_Impt = new DetailsImptFragment();
            transaction.add(R.id.actiondetails_fl, fragment_Impt);
        }

        //????????????fragment
        hideFragment(transaction);
        //?????????????????????fragment
        transaction.show(fragment_Impt);

        //????????????
        transaction.commit();
    }

    //???????????????fragment
    private void hideFragment(FragmentTransaction transaction){
        if(fragment_Team != null){
            transaction.hide(fragment_Team);
        }
        if(fragment_History != null){
            transaction.hide(fragment_History);
        }
        if(fragment_Impt != null){
            transaction.hide(fragment_Impt);
        }
    }


    private void initMap(){
        centerToMyLocation(BaiduMapUtils.mylatitude,BaiduMapUtils.mylongtitude);


        //????????????
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(13f);
        mBaiduMap.setMapStatus(msu);
        BaiduMapUtils.hide(mMapView);
    }


    Marker marker1;
    private void initMarker1(){

//        View v_temp = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_detail_marker, null);//????????????????????????
//        ImageView imageView = v_temp.findViewById(R.id.marder);
//        imageView.setImageResource(R.mipmap.marker4 );
//        BaiduMapUtils.createMarker(v_temp,new LatLng(BaiduMapUtils.mylatitude,BaiduMapUtils.mylongtitude),null,mBaiduMap);

        myLastLatin=new LatLng(BaiduMapUtils.mylatitude,BaiduMapUtils.mylongtitude);
        InActionUtils.drawBackGround3(myLastLatin,mBaiduMap);
        marker1= InActionUtils.drawTeamOnMap(this,BaiduMapUtils.mylatitude,BaiduMapUtils.mylongtitude,mBaiduMap);




    }



    private void initMarker2(){
        View v_temp = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_detail_marker, null);//????????????????????????
        ImageView imageView = v_temp.findViewById(R.id.marder);
        imageView.setImageResource(R.mipmap.marker5);
        Marker marker=BaiduMapUtils.createMarker(v_temp,new LatLng(BaiduMapUtils.mylatitude,BaiduMapUtils.mylongtitude),null,mBaiduMap);

    }



    private void initMarker3(){
        View v_temp = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_marker_history, null);//????????????????????????
        ImageView imageView = v_temp.findViewById(R.id.marder);
        imageView.setImageResource(R.mipmap.marker6);
        BaiduMapUtils.createMarker(v_temp,new LatLng(BaiduMapUtils.mylatitude+0.02,BaiduMapUtils.mylongtitude),null,mBaiduMap);
    }


    private void initMarkerListener(){

        /**
         * ??????marker???????????????
         */
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            //??????????????????????????????true???????????????false
            //????????????false

            /**
             * marker0?????????????????????marker???marker????????????marker
             * @param marker
             * @return
             */
            @Override
            public boolean onMarkerClick(Marker marker) {

//                UiUtils.showMsg(DetailsActivity.this,"latitude"+Double.toString(myLastLatin.latitude-marker.getPosition().latitude)+
//                        "longitude"+Double.toString(myLastLatin.longitude-marker.getPosition().longitude));


                double latitude=marker.getExtraInfo().getDouble("latitude");
                double longtitude=marker.getExtraInfo().getDouble("longtitude");

                double latitude1=marker1.getPosition().latitude;
                double longtitude1=marker1.getPosition().longitude;
                marker1.remove();
                marker.remove();



                InActionUtils.drawTeamOnMap2(ActionDetailsActivity.this, latitude1,longtitude1, mBaiduMap,
               0,R.mipmap.team_icon2,"4/7");

                InActionUtils.drawTeamOnMap2(ActionDetailsActivity.this, latitude,longtitude, mBaiduMap,
                        0,R.mipmap.team_icon1,"2/4");

                fragment_Team.changeData();


                return true;
            }
        });


    }



}
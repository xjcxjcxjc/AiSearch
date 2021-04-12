package com.example.aisearch.ui.family.take_a_hand;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.util.TakePhotoUtils;
import com.example.aisearch.util.UiUtils;
import com.google.common.collect.Lists;
import com.qmuiteam.qmui.widget.QMUILoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ComparisonActivity extends BaseActivity {

    @BindView(R.id.camparison_recycle)
    RecyclerView camparison_recycle;

    @BindView(R.id.help_view1card4)
    CardView help_view1card4;

    @BindView(R.id.finish)
    Button finish;


    @BindView(R.id.help_takephoto)
    RelativeLayout help_takephoto;

    @BindView(R.id.help_photo)
    ImageView help_photo;

    @BindView(R.id.loadView)
    QMUILoadingView qmuiLoadingView;

    @BindView(R.id.my_scrollview)
    ScrollView my_scrollview;

    //比对图片的adpter
    PhotoesAdapter photoesAdapter;
    TakePhotoUtils takePhotoUtils;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_comparison;
    }

    @Override
    protected void init() {
        photoesAdapter=new PhotoesAdapter( this,new ArrayList<>(),new ArrayList<>());
        camparison_recycle.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        camparison_recycle.setAdapter(photoesAdapter);
        takePhotoUtils=new TakePhotoUtils(this);
        help_takephoto.setOnClickListener( takePhotoUtils);
    }

    @Override
    protected void initBtn() {

        /**
         * 点击请求数据
         */
        help_view1card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qmuiLoadingView.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        qmuiLoadingView.setVisibility(View.INVISIBLE);
                        photoesAdapter.setBitmaps(initData());
                        photoesAdapter.setSimilar(initData2());

                        photoesAdapter.notifyDataSetChanged();
//                        my_scrollview.smoothScrollTo(600,600);
//                        my_scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                        my_scrollview.post(new Runnable(){
                            @Override
                            public void run(){
                                //滚动到底部
                                my_scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                                //滚动到顶部
                                //scrollview.fullScroll(ScrollView.FOCUS_UP);
                            }
                        });
                    }
                }, 1000);

            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private List initData(){
        List list= Lists.newArrayList(
                UiUtils.resourceToBitmap(this, R.mipmap.lostperson_img7),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img8),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img9),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img10),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img11),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img12),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img13),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img14),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img15),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img16),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img17),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img18),
                UiUtils.resourceToBitmap( this, R.mipmap.lostperson_img19)
        );
        return list;
    }

    private List initData2(){
        List list= Lists.newArrayList(
                "86",
                "86",
                "86",
                "86",
                "85",
                "85",
                "85",
                "85",
                "84",
                "84",
                "83",
                "83",
                "83",
                "82",
                "82"
        );
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        help_photo.setImageBitmap(takePhotoUtils.PhotoActivityResult(requestCode, resultCode, data));
    }


}
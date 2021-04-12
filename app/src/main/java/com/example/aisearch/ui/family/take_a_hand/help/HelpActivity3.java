package com.example.aisearch.ui.family.take_a_hand.help;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.family.take_a_hand.PhotoesAdapter;
import com.example.aisearch.util.UiUtils;
import com.google.common.collect.Lists;

import java.util.List;

import butterknife.BindView;

public class HelpActivity3 extends BaseActivity {

    @BindView(R.id.success_submit)
    CardView success_submit;

    @BindView(R.id.success_recycle)
    RecyclerView success_recycle;

    @BindView(R.id.success_submittv)
    TextView success_submittv;

    @BindView(R.id.finish)
    Button finish;

    @BindView(R.id.share)
    ImageView share;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_success;
    }

    @Override
    protected void init() {
        success_recycle.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        success_recycle.setAdapter(new PhotoesAdapter(HelpActivity3.this,initData(),null));


    }

    @Override
    protected void initBtn() {

        countDown(3);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.ShareToOthers(HelpActivity3.this);
            }
        });
    }

    /**
     * 倒计时
     * @param time
     */
    private void countDown(int time){
        if (time==-1){
            success_submittv.setText("提交");
            success_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HelpActivity3.this, HelpActivity4.class));
                    finish();
                }
            });
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    success_submittv.setText(time+"秒后可点击");
                    countDown(time-1);
                }
            }, 1000);
        }
    }


    private List initData(){
        List list= Lists.newArrayList(
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img7),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img8),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img9),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img10),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img11),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img12),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img13),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img14),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img15),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img16),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img17),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img18),
                UiUtils.resourceToBitmap(HelpActivity3.this,R.mipmap.lostperson_img19)
        );

        return list;
    }


}
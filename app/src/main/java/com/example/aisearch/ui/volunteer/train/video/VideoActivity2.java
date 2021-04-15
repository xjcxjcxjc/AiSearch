package com.example.aisearch.ui.volunteer.train.video;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.aisearch.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;

/**
 * 视频界面
 */
public class VideoActivity2 extends GSYBaseActivityDetail<AiSearchVideoPlayer> {

    AiSearchVideoPlayer detailPlayer;

    //传入地址  Environment.getExternalStorageDirectory()：/storage/emulated/0
//    private String url = "https://v.qq.com/x/page/e082722rdxf.html";
//    private String url = "https://cz-video-view.oss-cn-beijing.aliyuncs.com/20200130/09a1594d54d1c743b05d65efcc17ee1d.mp4";
    private String url = "https://cz-video-view.oss-cn-beijing.aliyuncs.com/20191207/d6c580883815a8920e01c3bec7187914.mp4";
    //    private String url = Environment.getExternalStorageDirectory()+"/林允儿.mp4";


    int testtime=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        detailPlayer = (AiSearchVideoPlayer) findViewById(R.id.detail_player);
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);
        detailPlayer.setFullHideActionBar(false);
        detailPlayer.setGSYVideoProgressListener(new GSYVideoProgressListener() {
            //progress是百分比
            @Override
            public void onProgress(int progress, int secProgress, int currentPosition, int duration) {
                if(progress>=20&&testtime==0){
                    startActivity(new Intent(VideoActivity2.this,VideoTestActivity.class));
                    testtime++;
                }
            }
        });

        detailPlayer.setBackFromFullScreenListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initVideoBuilderMode();
        QMUIStatusBarHelper.translucent(this);
        detailPlayer.getFullscreenButton().performClick();
        detailPlayer.release();
        initBtn();
    }

    private void initBtn(){
        ImageView back2=findViewById(R.id.back2);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public AiSearchVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }


    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.icon_wechart);
        //loadCover(imageView, url);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(url)
                .setCacheWithPlay(true)
                //title
                .setVideoTitle("水上求生")
                .setIsTouchWiget(true)
                //.setAutoFullWithSize(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)//打开动画
                .setNeedLockFull(true)
                .setSeekRatio(1)
                .setAutoFullWithSize(true);
    }

    @Override
    public void clickForFullScreen() {

    }


    /**
     * 是否启动旋转横屏，true表示启动
     */
    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

}
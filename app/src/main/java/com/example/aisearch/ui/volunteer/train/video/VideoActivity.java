package com.example.aisearch.ui.volunteer.train.video;


import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.aisearch.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;

/**
 * 视频界面
 */
public class VideoActivity extends GSYBaseActivityDetail<AiSearchVideoPlayer> {

    AiSearchVideoPlayer detailPlayer;

    //传入地址  Environment.getExternalStorageDirectory()：/storage/emulated/0
//    private String url = "https://v.qq.com/x/page/e082722rdxf.html";
//    private String url = "https://cz-video-view.oss-cn-beijing.aliyuncs.com/20200130/09a1594d54d1c743b05d65efcc17ee1d.mp4";
    private String url = "https://cz-video-view.oss-cn-beijing.aliyuncs.com/20191207/d6c580883815a8920e01c3bec7187914.mp4";



    //activity_study_details activity_video
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_details);
        detailPlayer = (AiSearchVideoPlayer) findViewById(R.id.detail_player);

        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);
        detailPlayer.setFullHideActionBar(false);
        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailPlayer.setBackFromFullScreenListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        detailPlayer.hidenode1();
        detailPlayer.getBackButton().setFitsSystemWindows(true);


        initVideoBuilderMode();
        QMUIStatusBarHelper.translucent(this);
//        detailPlayer.getFullscreenButton().performClick();
//        detailPlayer.release();

        fullscreen(true);
    }


    @Override
    public AiSearchVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }


    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.background38);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);


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

    private void fullscreen(boolean enable) {
        if (enable) { //显示状态栏
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else { //隐藏状态栏
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(lp);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }


}
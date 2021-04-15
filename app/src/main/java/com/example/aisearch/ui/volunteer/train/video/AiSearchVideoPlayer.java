package com.example.aisearch.ui.volunteer.train.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aisearch.R;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIDirection;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundLinearLayout;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.shuyu.gsyvideoplayer.utils.CommonUtil.hideNavKey;


/**
 * 带封面
 * Created by guoshuyu on 2017/9/3.
 */

public class AiSearchVideoPlayer extends StandardGSYVideoPlayer {

    int  mCoverOriginId = 0;
    int mDefaultRes;

    /**
     * 自定义的内容
     */
    ImageView mCoverImage;
    ImageView back2;
    TextView title;
    Activity activity;
    String mCoverOriginUrl;
    QMUIAlphaImageButton videolist;
    PopupWindow popupWindow;

    @BindView(R.id.video_node1)
    ImageView video_node1;
    @BindView(R.id.video_node2)
    ImageView video_node2;
    @BindView(R.id.video_node3)
    ImageView video_node3;

    @BindView(R.id.fullscreen2)
    ImageView fullscreen;


    //打开侧边栏的按钮布局
    QMUIRoundLinearLayout qmuiRoundLinearLayout;
    View popipWindow_view;
    Context acContext;

    public AiSearchVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }
    public AiSearchVideoPlayer(Context context) {
        super(context);
    }
    public AiSearchVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        ButterKnife.bind(this);

        mCoverImage = (ImageView) findViewById(R.id.thumbImage);
        qmuiRoundLinearLayout=findViewById(R.id.video_list_layout);
        if (mThumbImageViewLayout != null &&
                (mCurrentState == -1 || mCurrentState == CURRENT_STATE_NORMAL || mCurrentState == CURRENT_STATE_ERROR)) {
            mThumbImageViewLayout.setVisibility(VISIBLE);
        }
        activity=(Activity)context;
        acContext=context;
        initBtn(context);
        initView();
        ButterKnife.bind(this);
    }

    private void initBtn(Context context){
        back2=findViewById(R.id.back2);
        back2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        videolist=findViewById(R.id.video_list);
        videolist.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //在视频左边弹出侧边栏
                getPopupWindow(context);
                popupWindow.showAtLocation(v, Gravity.RIGHT,0,0);
                qmuiRoundLinearLayout.setVisibility(INVISIBLE);
//                QMUIViewHelper.fadeOut(videolist,1000,null,true);
            }
        });

        fullscreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                acContext.startActivity(new Intent(acContext,VideoActivity2.class));
            }
        });
    }

    private void initView(){

    }
    protected  void  initPopupWindow(Context context){
        //获取自定义布局文件activity_pop_left.xml 布局文件
        popipWindow_view = activity.getLayoutInflater().inflate(R.layout.item_video_popwindows,null,false);
        //创建Popupwindow 实例，200，LayoutParams.MATCH_PARENT 分别是宽高
        popupWindow = new PopupWindow(popipWindow_view,600, ViewGroup.LayoutParams.MATCH_PARENT,true);

        //设置动画效果
//        popupWindow.setAnimationStyle(R.anim.close_in);
        QMUIViewHelper.slideIn(popipWindow_view,300,null,true, QMUIDirection.RIGHT_TO_LEFT);

        //点击其他地方消失
        popipWindow_view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popipWindow_view != null && popipWindow_view.isShown()) {
                    popupWindow.dismiss();
                    popupWindow = null;

                }
                return false;
            }
        });
    }


    private  void  getPopupWindow(Context context){
        if (null!=popupWindow){
            QMUIViewHelper.slideIn(popipWindow_view,300,null,true, QMUIDirection.RIGHT_TO_LEFT);
            popupWindow.dismiss();
            return;
        }else {
            initPopupWindow(context);
        }
    }

//    }
    protected QMUIStickySectionAdapter<SectionHeader, SectionItem, QMUIStickySectionAdapter.ViewHolder> createAdapter() {
        return new QDListSectionAdapter(true);
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(acContext) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
    }

    private static ArrayList<QMUISection<SectionHeader, SectionItem>> getList() {
        ArrayList<QMUISection<SectionHeader, SectionItem>> list = new ArrayList<>();

        ArrayList<SectionItem> contents = new ArrayList<>();

        contents.add(new SectionItem("水中拖救的重要性  100%","",""));
        contents.add(new SectionItem("习题  0%","",""));
        contents.add(new SectionItem("动作的优点  0%","",""));
        contents.add(new SectionItem("习题  0%","",""));
        contents.add(new SectionItem("动作的细节  0%","",""));
        contents.add(new SectionItem("习题  0%","",""));
        contents.add(new SectionItem("总结动作要点  0%","",""));
        SectionHeader header = new SectionHeader("第1节 - 水中拖救姿势");
        QMUISection<SectionHeader, SectionItem> section = new QMUISection<>(header, contents, false);
        list.add(section);

        return list;
    }

    @Override
    public void onClick(View v) {
        qmuiRoundLinearLayout.setVisibility(VISIBLE);
        int i = v.getId();
        if (mHideKey && mIfCurrentIsFullscreen) {
            hideNavKey(mContext);
        }
        if (i == R.id.start) {
            clickStartIcon();
        } else if (i == R.id.surface_container && mCurrentState == CURRENT_STATE_ERROR) {
            if (mVideoAllCallBack != null) {
                Debuger.printfLog("onClickStartError");
                mVideoAllCallBack.onClickStartError(mOriginUrl, mTitle, this);
            }
            prepareVideo();
        } else if (i == R.id.thumb) {
            if (!mThumbPlay) {
                return;
            }
            if (TextUtils.isEmpty(mUrl)) {
                Debuger.printfError("********" + getResources().getString(R.string.no_url));
                return;
            }
            if (mCurrentState == CURRENT_STATE_NORMAL) {
                if (isShowNetConfirm()) {
                    showWifiDialog();
                    return;
                }
                startPlayLogic();
            } else if (mCurrentState == CURRENT_STATE_AUTO_COMPLETE) {
                onClickUiToggle();
            }
        } else if (i == R.id.surface_container) {
            if (mVideoAllCallBack != null && isCurrentMediaListener()) {
                if (mIfCurrentIsFullscreen) {
                    Debuger.printfLog("onClickBlankFullscreen");
                    mVideoAllCallBack.onClickBlankFullscreen(mOriginUrl, mTitle,  AiSearchVideoPlayer.this);
                } else {
                    Debuger.printfLog("onClickBlank");
                    mVideoAllCallBack.onClickBlank(mOriginUrl, mTitle, AiSearchVideoPlayer.this);
                }
            }
            startDismissControlViewTimer();
        }
    }


    @Override
    protected void hideAllWidget() {
        super.hideAllWidget();


    }


    @Override
    public int getLayoutId() {
        return R.layout.item_video_layout;
    }

    public void loadCoverImage(String url, int res) {
        mCoverOriginUrl = url;
        mDefaultRes = res;
        Glide.with(getContext().getApplicationContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000)
                                .centerCrop()
                                .error(res)
                                .placeholder(res))
                .load(url)
                .into(mCoverImage);
    }

    public void loadCoverImageBy(int id, int res) {
        mCoverOriginId = id;
        mDefaultRes = res;
        mCoverImage.setImageResource(id);
    }


    @Override
    public void setIfCurrentIsFullscreen(boolean ifCurrentIsFullscreen) {
        super.setIfCurrentIsFullscreen(ifCurrentIsFullscreen);
    }



    @Override
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        GSYBaseVideoPlayer gsyBaseVideoPlayer = super.startWindowFullscreen(context, actionBar, statusBar);
        AiSearchVideoPlayer aiSearchVideoPlayer = (AiSearchVideoPlayer) gsyBaseVideoPlayer;
        if(mCoverOriginUrl != null) {
            aiSearchVideoPlayer.loadCoverImage(mCoverOriginUrl, mDefaultRes);
        } else  if(mCoverOriginId != 0) {
            aiSearchVideoPlayer.loadCoverImageBy(mCoverOriginId, mDefaultRes);
        }

        return gsyBaseVideoPlayer;
    }

    @Override
    protected void resolveNormalVideoShow(View oldF, ViewGroup vp, GSYVideoPlayer gsyVideoPlayer) {
        super.resolveNormalVideoShow(oldF, vp, gsyVideoPlayer);

    }

    @Override
    public GSYBaseVideoPlayer showSmallVideo(Point size, boolean actionBar, boolean statusBar) {
        //下面这里替换成你自己的强制转化
        AiSearchVideoPlayer aiSearchVideoPlayer = (AiSearchVideoPlayer) super.showSmallVideo(size, actionBar, statusBar);
        aiSearchVideoPlayer.mStartButton.setVisibility(GONE);
        aiSearchVideoPlayer.mStartButton = null;
        return aiSearchVideoPlayer;
    }

    @Override
    protected void cloneParams(GSYBaseVideoPlayer from, GSYBaseVideoPlayer to) {
        super.cloneParams(from, to);
        AiSearchVideoPlayer sf = (AiSearchVideoPlayer) from;
        AiSearchVideoPlayer st = (AiSearchVideoPlayer) to;
        st.mShowFullAnimation = sf.mShowFullAnimation;
    }


    /**
     * 退出window层播放全屏效果
     */
    @SuppressWarnings("ResourceType")
    @Override
    protected void clearFullscreenLayout() {
        if (!mFullAnimEnd) {
            return;
        }
        mIfCurrentIsFullscreen = false;
        int delay = 0;
        if (mOrientationUtils != null) {
            delay = mOrientationUtils.backToProtVideo();
            mOrientationUtils.setEnable(false);
            if (mOrientationUtils != null) {
                mOrientationUtils.releaseListener();
                mOrientationUtils = null;
            }
        }

        if (!mShowFullAnimation) {
            delay = 0;
        }

        final ViewGroup vp = (CommonUtil.scanForActivity(getContext())).findViewById(Window.ID_ANDROID_CONTENT);
        final View oldF = vp.findViewById(getFullId());
        if (oldF != null) {
            //此处fix bug#265，推出全屏的时候，虚拟按键问题
            AiSearchVideoPlayer gsyVideoPlayer = (AiSearchVideoPlayer) oldF;
            gsyVideoPlayer.mIfCurrentIsFullscreen = false;
        }

        if (delay == 0) {
            backToNormal();
        } else {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    backToNormal();
                }
            }, delay);
        }

    }


    /******************* 下方两个重载方法，在播放开始前不屏蔽封面，不需要可屏蔽 ********************/
    @Override
    public void onSurfaceUpdated(Surface surface) {
        super.onSurfaceUpdated(surface);
        if (mThumbImageViewLayout != null && mThumbImageViewLayout.getVisibility() == VISIBLE) {
            mThumbImageViewLayout.setVisibility(INVISIBLE);
        }
    }

    @Override
    protected void setViewShowState(View view, int visibility) {
        if (view == mThumbImageViewLayout && visibility != VISIBLE) {
            return;
        }
        super.setViewShowState(view, visibility);
    }

    @Override
    public void onSurfaceAvailable(Surface surface) {
        super.onSurfaceAvailable(surface);
        if (GSYVideoType.getRenderType() != GSYVideoType.TEXTURE) {
            if (mThumbImageViewLayout != null && mThumbImageViewLayout.getVisibility() == VISIBLE) {
                mThumbImageViewLayout.setVisibility(INVISIBLE);
            }
        }
    }

    /******************* 下方重载方法，在播放开始不显示底部进度和按键，不需要可屏蔽 ********************/

    protected boolean byStartedClick;

    @Override
    protected void onClickUiToggle() {
        if (mIfCurrentIsFullscreen && mLockCurScreen && mNeedLockFull) {
            setViewShowState(mLockScreen, VISIBLE);
            return;
        }
        byStartedClick = true;
        super.onClickUiToggle();

    }

    @Override
    protected void changeUiToNormal() {
        super.changeUiToNormal();
        byStartedClick = false;
    }

    @Override
    protected void changeUiToPreparingShow() {
        super.changeUiToPreparingShow();
        Debuger.printfLog("Sample changeUiToPreparingShow");
        setViewShowState(mBottomContainer, INVISIBLE);
        setViewShowState(mStartButton, INVISIBLE);
    }

    @Override
    protected void changeUiToPlayingBufferingShow() {
        super.changeUiToPlayingBufferingShow();
        Debuger.printfLog("Sample changeUiToPlayingBufferingShow");
        if (!byStartedClick) {
            setViewShowState(mBottomContainer, INVISIBLE);
            setViewShowState(mStartButton, INVISIBLE);
        }
    }

    @Override
    protected void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
        Debuger.printfLog("Sample changeUiToPlayingShow");
        if (!byStartedClick) {
            setViewShowState(mBottomContainer, INVISIBLE);
            setViewShowState(mStartButton, INVISIBLE);
        }
    }

    @Override
    public void startAfterPrepared() {
        super.startAfterPrepared();
        Debuger.printfLog("Sample startAfterPrepared");
        setViewShowState(mBottomContainer, INVISIBLE);
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mBottomProgressBar, VISIBLE);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        byStartedClick = true;
        super.onStartTrackingTouch(seekBar);
    }

    public void hidenode1(){
        video_node1.setVisibility(INVISIBLE);
    }

}
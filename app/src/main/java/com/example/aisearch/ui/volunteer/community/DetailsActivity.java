package com.example.aisearch.ui.volunteer.community;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.util.volunteer.Comment;
import com.example.aisearch.bean.util.volunteer.CommunityItems;
import com.example.aisearch.ui.volunteer.community.adpter.CommentAdapter;
import com.example.aisearch.util.UiUtils;
import com.qmuiteam.qmui.util.QMUIDirection;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.details_comment)
    ImageView details_comment;
    @BindView(R.id.details_commentbtn)
    Button details_commentbtn;

    @BindView(R.id.details_star)
    ImageView details_star;
    @BindView(R.id.details_wrting)
    ImageView details_wrting;
    PopupWindow popupWindow;
    View popipWindow_view;
    RecyclerView dialogrecyclerView;
    @BindView(R.id.cmnt_frgment_backgroung)
    LinearLayout cmnt_frgment_backgroung;

    @BindView(R.id.finish)
    Button button;


    @BindView(R.id.community_detail_name)
    TextView name;
    @BindView(R.id.community_detail_location)
    TextView location;
    @BindView(R.id.community_detail_title)
    TextView title;
    @BindView(R.id.community_detail_describe)
    TextView describe;
    @BindView(R.id.community_detail_likenub)
    TextView likeNub;
    @BindView(R.id.community_detail_collectionNub)
    TextView collectionNub;
    @BindView(R.id.community_detail_commentNub)
    TextView commetnNub;
    @BindView(R.id.community_detail_advertisementimg)
    ImageView adverticeImg;

    @BindView(R.id.community_detail_share)
    ImageView community_detail_share;

    private CommunityItems communityItem;



    @Override
    public int getLayoutRes() {
        return R.layout.activity_details;
    }

    @Override
    protected void init() {

        QMUIStatusBarHelper.translucent(this);
        details_commentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPopupWindow(DetailsActivity.this);
                popupWindow.showAtLocation(v, Gravity.BOTTOM,0,130);

            }
        });
        initBackGrd();
        initData();
    }

    @Override
    protected void initBtn() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        community_detail_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.ShareToOthers(DetailsActivity.this);
            }
        });
    }

    /**
     * 评论
     * @param context
     */
    protected  void  initPopupWindow(Context context){
        //获取自定义布局文件activity_pop_left.xml 布局文件
        popipWindow_view = getLayoutInflater().inflate(R.layout.dialog_comment,null,false);
        dialogrecyclerView=popipWindow_view.findViewById(R.id.dialog_comment_recycle);
        dialogrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        dialogrecyclerView.setAdapter(new CommentAdapter(DetailsActivity.this,initData2()));
        //创建Popupwindow 实例，200，LayoutParams.MATCH_PARENT 分别是宽高
        popupWindow = new PopupWindow(popipWindow_view,ViewGroup.LayoutParams.MATCH_PARENT, 670,true);

        //设置动画效果
//        popupWindow.setAnimationStyle(R.anim.close_in);
        QMUIViewHelper.slideIn(popipWindow_view,300,null,true, QMUIDirection.BOTTOM_TO_TOP);

        //点击其他地方消失
        popipWindow_view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popipWindow_view != null && popipWindow_view.isShown()) {
                    QMUIViewHelper.slideOut(popipWindow_view,500,null,true,QMUIDirection.TOP_TO_BOTTOM);
                    popupWindow = null;
                }

                return false;
            }
        });

    }



    private  void  getPopupWindow(Context context){
        if (null!=popupWindow){
            QMUIViewHelper.slideIn(popipWindow_view,300,null,true, QMUIDirection.BOTTOM_TO_TOP);
            popupWindow.dismiss();
            return;
        }else {
            initPopupWindow(context);
        }
    }

    private List<Comment> initData2(){
        List list= new ArrayList();
        list.add(new Comment(UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg8),
                "xjc","志愿者牛啊！","8:30",56));
        list.add(new Comment(UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg9),
                "刘恒","好漂亮呀","8:30",21));
        list.add(new Comment(UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg10),
                "常燕","正是有这些平凡的英雄，世界才更美好啊","8:30",97));
        list.add(new Comment(UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg11),
                "志愿刘哥","可以买个走失手环，就不怕走丢了","8:30",147));
        list.add(new Comment(UiUtils.resourceToBitmap(this,R.mipmap.orgnization_headimg12),
                "spark","为了志愿事业努力吧","8:30",3));



        return list;
    }


    private void initBackGrd(){
        SharedPreferences sharedPreferences;
        sharedPreferences= getSharedPreferences("data", MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML){
            cmnt_frgment_backgroung.setBackgroundColor( getResources().getColor(R.color.title_red));
            details_comment.setImageResource(R.mipmap.comment2);
            details_star.setImageResource(R.mipmap.star3);
            details_wrting.setImageResource(R.mipmap.writing2);
        }
        else{
            cmnt_frgment_backgroung.setBackgroundColor( getResources().getColor(R.color.title_blue));
            details_comment.setImageResource(R.mipmap.comment);
            details_star.setImageResource(R.mipmap.star);
            details_wrting.setImageResource(R.mipmap.writing);

        }
    }


    private void initData(){


    }


}
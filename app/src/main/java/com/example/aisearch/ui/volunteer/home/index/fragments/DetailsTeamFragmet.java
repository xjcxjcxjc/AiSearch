package com.example.aisearch.ui.volunteer.home.index.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;
import com.example.aisearch.bean.volunteer.TeamMates;
import com.example.aisearch.ui.volunteer.home.JoinActivity;
import com.example.aisearch.ui.volunteer.home.index.adapter.TeamMatesAdapter;
import com.example.aisearch.ui.volunteer.home.zwactivity.SearchPeopleByImageActivity;
import com.google.common.collect.Lists;

import butterknife.BindView;
import butterknife.ButterKnife;
import hollowsoft.slidingdrawer.OnDrawerCloseListener;
import hollowsoft.slidingdrawer.OnDrawerOpenListener;
import hollowsoft.slidingdrawer.OnDrawerScrollListener;
import hollowsoft.slidingdrawer.SlidingDrawer;

/*
 * @Author : XJC
 * @Time : 2021/2/16 16:01
 * @Description :
 *
 */
public class DetailsTeamFragmet extends BaseFragment implements OnDrawerScrollListener, OnDrawerOpenListener, OnDrawerCloseListener {

    @BindView(R.id.team_arrow)
    ImageView arrow;
    @BindView(R.id.indexdetails_joinbtn)
    CardView join;
    @BindView(R.id.team_drawer)
    SlidingDrawer drawer;

    @BindView(R.id.detail_team_help)
    CardView  detail_team_help;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.action_details_name)
    TextView action_details_name;

    TeamMatesAdapter teamMatesAdapter;
    @Override
    protected void init(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        drawer.setOnDrawerScrollListener(this);
        drawer.setOnDrawerOpenListener(this);
        drawer.setOnDrawerCloseListener(this);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(JoinActivity.class,false);
            }
        });
        drawer.open();
        teamMatesAdapter=new TeamMatesAdapter(getContext(),new TeamMates(
                Lists.newArrayList("影子","大熊","狮子","",""),
                3,6
        ));
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        recyclerview.setAdapter(teamMatesAdapter);

        action_details_name.setText("蓝天");
    }


    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {
        detail_team_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchPeopleByImageActivity.class));
            }
        });
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_indexdetails_team;
    }

    @Override
    public void onScrollStarted() {
    }

    @Override
    public void onScrollEnded() {
    }

    @Override
    public void onDrawerOpened() {
        arrow.setImageResource(R.mipmap.arrow3);
    }

    @Override
    public void onDrawerClosed() {
        arrow.setImageResource(R.mipmap.arrow1);
    }


    public void changeData(){
        title.setText("海曙老人救援行动--1队");
        action_details_name.setText("尖刀");

        teamMatesAdapter.setData(new TeamMates(
                Lists.newArrayList("太阳","蓝鹰"),
                1,3
        ));
        teamMatesAdapter.notifyDataSetChanged();
    }

}

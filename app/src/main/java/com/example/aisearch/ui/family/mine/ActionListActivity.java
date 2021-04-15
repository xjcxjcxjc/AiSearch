package com.example.aisearch.ui.family.mine;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.family.ActionStatus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActionListActivity extends BaseActivity implements RadioButton.OnCheckedChangeListener,
    RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.actionlist_recycle)
    RecyclerView actionlist_recycle;

    @BindView(R.id.actionlist_rg)
    RadioGroup actionlist_rg;
    @BindView(R.id.actionlist_rb1)
    RadioButton actionlist_rb1;
    @BindView(R.id.actionlist_rb2)
    RadioButton actionlist_rb2;
    @BindView(R.id.actionlist_rb3)
    RadioButton actionlist_rb3;
    @BindView(R.id.actionlist_rb4)
    RadioButton actionlist_rb4;
    @BindView(R.id.actionlist_img1)
    ImageView actionlist_img1;
    @BindView(R.id.actionlist_img2)
    ImageView actionlist_img2;
    @BindView(R.id.actionlist_img3)
    ImageView actionlist_img3;

    @BindView(R.id.actionlist_img4)
    ImageView actionlist_img4;

    private List<ActionStatus> statuses;
    ActionListAdapter actionListAdapter;

    @BindView(R.id.finish)
    Button finish;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_action_list2;
    }

    @Override
    protected void init() {

        int type=getIntent().getExtras().getInt("type");
        actionlist_recycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        if (ActionStatus.LISTTYPE_EXAMINE==type) {
            actionlist_rg.check(R.id.actionlist_rb2);
            actionlist_rb2.setTextColor(getResources().getColor(R.color.title_red));
            actionlist_img2.setVisibility(View.VISIBLE);
            actionListAdapter= new ActionListAdapter(ActionListActivity.this,initData1());

        }else if (ActionStatus.LISTTYPE_ACTION==type) {
            actionlist_rg.check(R.id.actionlist_rb3);
            actionlist_rb3.setTextColor(getResources().getColor(R.color.title_red));
            actionlist_img3.setVisibility(View.VISIBLE);
            actionListAdapter= new ActionListAdapter(ActionListActivity.this,initData2());
        }

        actionlist_recycle.setAdapter(actionListAdapter);
    }

    @Override
    protected void initBtn() {
        actionlist_rb1.setOnCheckedChangeListener(this);
        actionlist_rb2.setOnCheckedChangeListener(this);
        actionlist_rb3.setOnCheckedChangeListener(this);
        actionlist_rb4.setOnCheckedChangeListener(this);
        actionlist_rg.setOnCheckedChangeListener(this);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked ){
            buttonView.setTextColor(getResources().getColor(R.color.title_red));
        }else
            buttonView.setTextColor(getResources().getColor(R.color.text_gray1));

        switch (buttonView.getId()){
            case R.id.actionlist_rb1:
                if(isChecked ){
                    actionlist_img1.setVisibility(View.VISIBLE);
                }else
                    actionlist_img1.setVisibility(View.INVISIBLE);
                break;
            case R.id.actionlist_rb2:
                if(isChecked ){
                    actionlist_img2.setVisibility(View.VISIBLE);
                }else
                    actionlist_img2.setVisibility(View.INVISIBLE);
                break;
            case R.id.actionlist_rb3:
                if(isChecked ){
                    actionlist_img3.setVisibility(View.VISIBLE);
                }else
                    actionlist_img3.setVisibility(View.INVISIBLE);
                break;
            case R.id.actionlist_rb4:
                if(isChecked ){
                    actionlist_img4.setVisibility(View.VISIBLE);
                }else
                    actionlist_img4.setVisibility(View.INVISIBLE);
                break;
        }


    }


    private List<ActionStatus> initData1(){
        if (null==statuses)
            statuses=new ArrayList<>();
        else
            statuses.clear();
        statuses.add(new ActionStatus(ActionStatus.LISTTYPE_EXAMINE));

        return statuses;
    }

    private List<ActionStatus> initData2(){
        if (null==statuses)
            statuses=new ArrayList<>();
        else
            statuses.clear();
        statuses.add(new ActionStatus(ActionStatus.LISTTYPE_ACTION));
        return statuses;
    }

    private List<ActionStatus> initData3(){
        if (null==statuses)
            statuses=new ArrayList<>();
        else
            statuses.clear();
        statuses.add(new ActionStatus(ActionStatus.LISTTYPE_END));
        return statuses;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.actionlist_rb1:
                break;
            case R.id.actionlist_rb2:
                initData1();
                actionListAdapter.notifyDataSetChanged();
                break;
            case R.id.actionlist_rb3:
                initData2();
                actionListAdapter.notifyDataSetChanged();
                break;
            case R.id.actionlist_rb4:
                initData3();
                actionListAdapter.notifyDataSetChanged();
                break;
        }
    }
}
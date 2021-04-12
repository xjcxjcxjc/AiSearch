package com.example.aisearch.ui.volunteer.mine;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.ActionListItem;
import com.example.aisearch.ui.volunteer.action.beforeaction.BeforeMainActivity;
import com.example.aisearch.ui.volunteer.action.index.adapter.ActionCenterAdapter;
import com.example.aisearch.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyActionActivity extends BaseActivity {


    @BindView(R.id.mine_action_recycle)
    RecyclerView recyclerView;

    @BindView(R.id.finish)
    Button finish;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_my_action;
    }

    @Override
    protected void init() {
        initRecycleView();
    }

    @Override
    protected void initBtn() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecycleView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ActionCenterAdapter(this, BeforeMainActivity.class,initActionList()));

    }


    private List<ActionListItem> initActionList(){
        List<ActionListItem> actionListItems=new ArrayList<>();
        actionListItems.add(new ActionListItem(UiUtils.resourceToBitmap(this,R.mipmap.clipboard), "海曙老人救援行动",
                "宁波市海曙区学院路附近", "2021年3月9日8:00——3月10日8:00", "城市寻人", "志愿保险", "低风险", 5, 25));



        return actionListItems;
    }



}
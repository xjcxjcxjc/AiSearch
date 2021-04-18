package com.example.aisearch.ui.family.home;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.util.Message.MessageListItem;
import com.example.aisearch.ui.volunteer.home.message.MessageAdapter;
import com.example.aisearch.util.datautil.DataUtil;
import com.example.aisearch.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MsgActivity extends BaseActivity {

    @BindView(R.id.msg_recycleview)
    RecyclerView recyclerView;

    @BindView(R.id.finish)
    Button finish;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_msg;
    }


    @Override
    protected void init() {
        initrecycle();
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

    private void initrecycle() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MessageAdapter(this,initdata()));
    }
    private List<MessageListItem> initdata(){
        List<MessageListItem> messageListItems=new ArrayList<>();
        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(this, R.mipmap.mine2_urgentimg1),
                "系统消息","您提交的老人走失已经开始行动，去完善信息吧",1,"9:12"));
        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(this, R.mipmap.background32),
                "蓝天救援队","欢迎大家加入蓝天大家庭，希望大家能够同心协力，一起为",3,"12:20"));
        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(this, R.mipmap.background33),
                "海曙老人救援行动","[语言]",16,"刚刚"));



        return DataUtil.getFmlData(this);
    }
}
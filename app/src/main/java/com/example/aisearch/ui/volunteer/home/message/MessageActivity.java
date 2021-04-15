package com.example.aisearch.ui.volunteer.home.message;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.Message.MessageListItem;
import com.example.aisearch.util.datautil.DataUtil;

import java.util.List;

import butterknife.BindView;

public class MessageActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.msg_recycleview)
    RecyclerView recyclerView;


    @BindView(R.id.finish)
    Button button;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_message;
    }

    @Override
    protected void init() {


        initrecycle();
    }

    @Override
    protected void initBtn() {


        button.setOnClickListener(new View.OnClickListener() {
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.msg_btn1:
//                startActivity(OrgnizationActivity.class);
//                break;
//            case R.id.msg_btn2:
//                startActivity(MyActionActivity.class);
//                break;
//            case R.id.msg_btn3:
//                startActivity(ReplyActivity.class);
//                break;


        }
    }


    private List<MessageListItem> initdata(){
        return DataUtil.getValData(this);
    }


}
package com.example.aisearch.ui.volunteer.home.message;

import android.view.View;
import android.widget.Button;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;

import butterknife.BindView;

public class ReplyActivity extends BaseActivity {

    @BindView(R.id.finish)
    Button finish;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_reply;
    }

    @Override
    protected void init() {

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


}
package com.example.aisearch.ui.volunteer.mine;

import android.view.View;
import android.widget.Button;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;

import butterknife.BindView;

/**
 * 等级
 */
public class GradeActivity extends BaseActivity {

    @BindView(R.id.finish)
    Button finish;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_grade;
    }

    @Override
    protected void init() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initBtn() {

    }
}
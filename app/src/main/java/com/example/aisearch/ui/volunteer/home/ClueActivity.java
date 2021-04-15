package com.example.aisearch.ui.volunteer.home;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.volunteer.train.dialog.SubmitDialog2;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClueActivity extends BaseActivity {

    @BindView(R.id.finish)
    Button finish;

    @BindView(R.id.tv_submit)
    TextView tv_submit;

    SubmitDialog2 lockDialog;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_clue;
    }



    @Override
    protected void init() {
        QMUIStatusBarHelper.translucent(this);
        ButterKnife.bind(this);

        tv_submit.setOnClickListener(v -> {
            showSuccess();

        });

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



    private void showSuccess(){
        new SubmitDialog2(this, new SubmitDialog2.ClickCallBack() {
            @Override
            public void onStop() {
                finish();
            }
        },
                "反馈已提交").show();

    }

}
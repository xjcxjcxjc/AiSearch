package com.example.aisearch.ui.volunteer.action;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aisearch.R;
import com.example.aisearch.ui.volunteer.practice.dialog.SubmitDialog2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubmitActivity extends AppCompatActivity {

    @BindView(R.id.tv_submit2)
    TextView mTvSubmit;

    SubmitDialog2 lockDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit2);
        ButterKnife.bind(this);
        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SubmitActivity.this,"!11",Toast.LENGTH_LONG).show();
                showSuccess();
            }
        });
    }

    private void showSuccess(){
        lockDialog=new SubmitDialog2(this, new SubmitDialog2.ClickCallBack() {
            @Override
            public void onStop() {
                finish();
            }
        },
                "反馈已提交");
        lockDialog.show();

    }
}
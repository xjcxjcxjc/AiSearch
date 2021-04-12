package com.example.aisearch.ui.volunteer.action.beforeaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private RecyclerView rv;
    private Button mBtnSignIn;
    private TextView mTvFirst;
    private List<SignInfo> signInfoList;
    private SignAdapter adapter;
    private int status = -1;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initData();

        QMUIStatusBarHelper.translucent(this);

        initView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signInfoList.get(2).setStatus("已签到");
                adapter.notifyDataSetChanged();
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signInfoList.get(1).setStatus("已签到");
                adapter.notifyDataSetChanged();
            }
        }, 5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signInfoList.get(4).setStatus("已签到");
                adapter.notifyDataSetChanged();
            }
        }, 7000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signInfoList.get(0).setStatus("已签到");
                adapter.notifyDataSetChanged();
            }
        }, 10000);


        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status==-1){
                    mBtnSignIn.setBackgroundResource(R.drawable.shape_button_signing);
                    mBtnSignIn.setText("结束签到");
                    status = 1;

                    rv.setVisibility(View.VISIBLE);
                }else {

                    mBtnSignIn.setBackgroundResource(R.drawable.shape_button_signend);
                    mBtnSignIn.setText("签到已结束");
                    mTvFirst.setText("缺勤优先");
                    for (int i = 0; i <signInfoList.size(); i++) {
                       if (signInfoList.get(i).getStatus()!="已签到"){
                           index = i;
                           signInfoList.set(index,new SignInfo(1004,"影子","轿车","齐全","缺勤"));
                       }
                    }


                    adapter.notifyDataSetChanged();
                }


            }
        });
        mTvFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collator sortChina = Collator.getInstance(java.util.Locale.CHINA);
                Collections.sort(signInfoList,(a,b)->sortChina.compare(a.getStatus(),b.getStatus()));
                rv.setAdapter(adapter);
            }
        });
    }

    private void initData() {
        signInfoList = new ArrayList<>();
        signInfoList.add(new SignInfo(1001,"蓝鹰","轿车","齐全","未签到"));
        signInfoList.add(new SignInfo(1002,"尖刀","步行","齐全","未签到"));
        signInfoList.add(new SignInfo(1003,"刺客","步行","齐全","未签到"));
        signInfoList.add(new SignInfo(1004,"影子","轿车","齐全","未签到"));
        signInfoList.add(new SignInfo(1005,"太阳","自行车","齐全","未签到"));

    }
    private void initView() {
        rv = findViewById(R.id.rv_sign);
        mBtnSignIn = findViewById(R.id.btn_signin);
        mTvFirst = findViewById(R.id.tv_first);
         adapter = new SignAdapter(SignInActivity.this,signInfoList);
        rv.setLayoutManager(new LinearLayoutManager(SignInActivity.this));
        rv.setVisibility(View.INVISIBLE);
        rv.setAdapter(adapter);


        ImageView iv_return=findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
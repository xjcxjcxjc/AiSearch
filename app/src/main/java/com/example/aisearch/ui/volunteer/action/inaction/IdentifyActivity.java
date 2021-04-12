package com.example.aisearch.ui.volunteer.action.inaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.aisearch.R;
import com.example.aisearch.ui.volunteer.action.zwactivity.ChooseFunctionActivity2;
import com.example.aisearch.ui.volunteer.action.zwactivity.PersonReIdentificationActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdentifyActivity extends AppCompatActivity {

    @BindView(R.id.faceidentify)
    CardView faceidentify;

    @BindView(R.id.titaishibie)
    CardView titaishibie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        ButterKnife.bind(this);
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);

    }


    @OnClick({R.id.faceidentify,R.id.titaishibie})
    public void initBtn(View v){
        switch (v.getId()){
            case R.id.faceidentify:
                startActivity(new Intent(IdentifyActivity.this, ChooseFunctionActivity2.class));
                break;
            case R.id.titaishibie:
                startActivity(new Intent(IdentifyActivity.this, PersonReIdentificationActivity.class));
                break;
        }



    }

}
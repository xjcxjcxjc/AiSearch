package com.example.aisearch.ui.volunteer.home.message;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.Message.OrgnizationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrgnizationActivity extends BaseActivity {


    @BindView(R.id.organization2_recycle)
    RecyclerView organization2_recycle;

    @BindView(R.id.finish)
    Button button;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_orgnization;
    }

    @Override
    protected void init() {

        organization2_recycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        organization2_recycle.setAdapter(new OrgnizationListAdapter(this,initdata()));

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


    private List<OrgnizationItem> initdata(){
        List<OrgnizationItem> orgnizationItems=new ArrayList<>();
        orgnizationItems.add(new OrgnizationItem());



        return orgnizationItems;

    }

}
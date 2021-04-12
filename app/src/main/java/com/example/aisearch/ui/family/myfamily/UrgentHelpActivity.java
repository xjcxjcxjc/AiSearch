package com.example.aisearch.ui.family.myfamily;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;

import butterknife.BindView;

public class UrgentHelpActivity extends BaseActivity {


    @BindView(R.id.urgent_recycle)
    RecyclerView urgent_recycle;

    UrgentHelpAdapter urgentHelpAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_urgent_help;
    }

    @Override
    protected void init() {
        initRecycle();
    }

    @Override
    protected void initBtn() {

    }

    private void initRecycle() {
        urgentHelpAdapter =new UrgentHelpAdapter(UrgentHelpActivity.this);

        urgent_recycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        urgent_recycle.setAdapter(urgentHelpAdapter);

//        adapter.notifyDataSetChanged();
    }



}
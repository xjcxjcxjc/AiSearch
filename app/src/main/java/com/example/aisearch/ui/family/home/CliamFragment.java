package com.example.aisearch.ui.family.home;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;
import com.example.aisearch.bean.util.Person;
import com.example.aisearch.ui.volunteer.home.index.fragments.FullyLinearLayoutManager;
import com.example.aisearch.util.datautil.DataUtil;

import java.util.List;

import butterknife.BindView;

/*
 * @Author : XJC
 * @Time : 2021/4/6 22:43
 * @Description :
 *
 */
public class CliamFragment  extends BaseFragment {

    @BindView(R.id.hand_recycleview)
    RecyclerView recyclerView;
    private CliamAdapter cliamAdapter;


    @Override
    protected void init(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_cliamperson;
    }


    private void initRecycleView(View view){
        cliamAdapter=new CliamAdapter(getContext(),initData2(),0);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        recyclerView.setAdapter(cliamAdapter);
    }
    private List<Person> initData2(){
        return DataUtil.initClaimNoticeData(getContext());
    }


}

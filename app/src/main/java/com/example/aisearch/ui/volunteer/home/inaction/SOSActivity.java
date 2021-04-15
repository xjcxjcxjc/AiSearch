package com.example.aisearch.ui.volunteer.home.inaction;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.volunteer.SOSBulid;
import com.example.aisearch.ui.volunteer.home.inaction.SosAdapter.CopAdapter;
import com.example.aisearch.ui.volunteer.home.inaction.SosAdapter.HsptAdapter;
import com.example.aisearch.ui.volunteer.home.inaction.SosAdapter.ShelterAdapter;
import com.example.aisearch.ui.volunteer.home.inaction.SosAdapter.TeamAdapter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SOSActivity extends BaseActivity {


    @BindView(R.id.sos_teamphonerv)
    RecyclerView teamrecycle;
    @BindView(R.id.sos_coprv)
    RecyclerView copecycle;
    @BindView(R.id.sos_hsptrv)
    RecyclerView hosptrecycle;
    @BindView(R.id.sos_shlterrv)
    RecyclerView shelterrecycle;
    @BindView(R.id.finish)
    Button finish;



    CopAdapter copAdapter;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_s_o_s;
    }

    @Override
    protected void init() {
        QMUIStatusBarHelper.translucent(this);
        ButterKnife.bind(this);
        initRecycle();
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


    private void initRecycle(){

        copAdapter=new CopAdapter(SOSActivity.this,initBuliding("派出所"));

        teamrecycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        teamrecycle.setAdapter(new TeamAdapter(SOSActivity.this));

        copecycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        copecycle.setAdapter(copAdapter);

        hosptrecycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        hosptrecycle.setAdapter(new HsptAdapter(SOSActivity.this,initBuliding("医院")));

        shelterrecycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        shelterrecycle.setAdapter(new ShelterAdapter(SOSActivity.this));
    }

    /**
     * 从inActionActivity得到List
     * @param key
     * @return
     */
    private List<SOSBulid> initBuliding(String key){
        List<SOSBulid> sosBulids = (List<SOSBulid>) getIntent().getSerializableExtra(key);

        if (null==sosBulids)
            sosBulids=new ArrayList<>();

        return sosBulids;
    }


}
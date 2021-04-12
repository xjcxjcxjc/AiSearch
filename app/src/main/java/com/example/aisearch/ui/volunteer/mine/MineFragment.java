package com.example.aisearch.ui.volunteer.mine;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;
import com.example.aisearch.ui.volunteer.mine.adapter.MineVideoAdapter;
import com.example.aisearch.util.datautil.PracticeDataUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.mine_setting)
    Button mine_settingimg;
    @BindView(R.id.mine_thankwall_morebtn)
    RelativeLayout mine_thankwall_morebtn;
    @BindView(R.id.mine_studyvideo_morebtn)
    Button mine_studyvideo_morebtn;
    @BindView(R.id.mine_actionbook_morebtn)
    RelativeLayout mine_actionbook_morebtn;
    @BindView(R.id.mine_studyvideo_recycle)
    RecyclerView mine_studyvideo_recycle;
    @BindView(R.id.mine_headimg)
    ImageView mine_headimg;
    @BindView(R.id.mine_lvnubcard)
    CardView mine_lvnubcard;
    @BindView(R.id.mine_badgenubcard)
    CardView mine_badgenubcard;
    @BindView(R.id.mine_certificatenubcard)
    CardView mine_certificatenubcard;
    @BindView(R.id.mine_organization_morebtn)
    Button mine_organization_morebtn;


    @Override
    protected void init(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        initRecycleView();
    }

    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {
        mine_settingimg.setOnClickListener(this);
        mine_headimg.setOnClickListener(this);
        mine_lvnubcard.setOnClickListener(this);
        mine_badgenubcard.setOnClickListener(this);
        mine_certificatenubcard.setOnClickListener(this);
        mine_studyvideo_morebtn.setOnClickListener(this);
        mine_thankwall_morebtn.setOnClickListener(this);
        mine_actionbook_morebtn.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }


    @OnClick(R.id.mine_organization_morebtn)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_setting:
                startActivity(SettingActivity.class,false);
                break;
            case R.id.mine_headimg:
                startActivity(PersonPrivacyActivity.class,false);
                break;
            case R.id.mine_lvnubcard:
                startActivity(GradeActivity.class,false);
                break;
            case R.id.mine_badgenubcard:
                startActivity(BadgeActivity.class,false);
                break;
            case R.id.mine_certificatenubcard:
                startActivity(CertificateActivity.class,false);
                break;
            case R.id.mine_studyvideo_morebtn:
                startActivity(MineVideoActivity.class,false);
                break;
            case R.id.mine_thankwall_morebtn:
                startActivity(ThankWallActivity.class,false);
                break;
            case R.id.mine_actionbook_morebtn:
                startActivity(MyActionActivity.class,false);
                break;
            case R.id.mine_organization_morebtn:
                startActivity(OrganizationActivity.class,false);
                break;
        }
    }


    private void initRecycleView(){
        mine_studyvideo_recycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        mine_studyvideo_recycle.setAdapter(new MineVideoAdapter(getContext(),initVideos()));

    }

    private List initVideos(){
        return PracticeDataUtils.getMineVideos();
    }


}

package com.example.aisearch.ui.volunteer.train;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.volunteer.Subject_Single;
import com.example.aisearch.ui.volunteer.train.adapter.PracticeSubjectAdapter;
import com.example.aisearch.ui.volunteer.train.adapter.ProgressBarAdapter;
import com.google.common.collect.Lists;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PracticeTestActivity extends BaseActivity {

    @BindView(R.id.practicetest_subjectrecycle)
    RecyclerView subjectrecycle;
    @BindView(R.id.practicetest_test_progressbar)
    RecyclerView progressbar;
    @BindView(R.id.practicetest_backimg)
    ImageView back;
    @BindView(R.id.practicetest_submitcard)
    CardView submit;
    @BindView(R.id.practicetest_title)
    TextView title;

    PracticeSubjectAdapter practiceSubjectAdapter;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_practice_test;
    }

    @Override
    protected void init() {
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);

        ButterKnife.bind(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                practiceSubjectAdapter.submit();
            }
        });

        initRecycleView();
    }

    @Override
    protected void initBtn() {

    }


    private void initRecycleView() {
        practiceSubjectAdapter=new PracticeSubjectAdapter(PracticeTestActivity.this,initData());
        subjectrecycle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        subjectrecycle.setAdapter(practiceSubjectAdapter);

        progressbar.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        progressbar.setAdapter(new ProgressBarAdapter(PracticeTestActivity.this));
    }


    private List initData(){
        List<Subject_Single> singles= Lists.newArrayList(
                new Subject_Single("关于水上求生哪些说法正确（  ）","在冷水中为了防止体温降低，应该持续的运动",
                        "成人在10摄氏度水温中可以生存4小时","缺乏淡水资源","海上缺乏参照物",1),
                new Subject_Single("水生求生的第一要素是（  ）","救生设备",
                        "求生知识和技能","求生信念和意志 ","完善的海上救助体系",1),
                new Subject_Single("救生圈附带的自亮浮灯发光时间应持续（  ）","15分钟",
                        "1小时","42分钟","20分钟",1),
                new Subject_Single("下列不属于求生三要素的是（  ）","自身保护",
                        "求生意志","救生设备","救生知识",1),
                new Subject_Single("为维持生命最低限度，每天进水不得少于（  ）","1.5升",
                        "1升","0.5升","0.3升",1),
                new Subject_Single("人体浸泡在水中，散热速度要比在空气中快（  ）","10倍",
                        "20倍","26倍","30倍",1),
                new Subject_Single("遇难者跳入水中后，遇到的首要危险是（  ）","溺水",
                        "食物","淡水","生物袭击",1),

                new Subject_Single("弃船后，导致船员死亡的主要原因是（  ）","溺水",
                "饥饿","身体暴露在水中","容易丧失意志",1),
                new Subject_Single("在热带水域保持体内水分的方法是（  ）","下海浸泡",
                        "多喝水","避免太阳直射","B+C",1),
                new Subject_Single("海上遇难求生者要有（  ）","坚强的意志和毅力",
                        "克服绝望恐惧的心理","良好的身体素质","以上都对",1)
        );

        return singles;
    }

}
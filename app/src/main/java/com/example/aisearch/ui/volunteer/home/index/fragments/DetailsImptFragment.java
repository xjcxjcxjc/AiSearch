package com.example.aisearch.ui.volunteer.home.index.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import hollowsoft.slidingdrawer.OnDrawerCloseListener;
import hollowsoft.slidingdrawer.OnDrawerOpenListener;
import hollowsoft.slidingdrawer.SlidingDrawer;

/*
 * @Author : XJC
 * @Time : 2021/2/16 16:01
 * @Description :
 *
 */
public class DetailsImptFragment extends BaseFragment implements OnDrawerOpenListener, OnDrawerCloseListener {

    @BindView(R.id.impt_arrow)
    ImageView arrow;

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        SlidingDrawer drawer =  view.findViewById(R.id.impt_drawer);
        drawer.setOnDrawerOpenListener(this);
        drawer.setOnDrawerCloseListener(this);

        ButterKnife.bind(this,view);
        drawer.open();
    }

    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_indexdetails_impt;
    }


    @Override
    public void onDrawerOpened() {
        arrow.setImageResource(R.mipmap.arrow3);
    }

    @Override
    public void onDrawerClosed() {
        arrow.setImageResource(R.mipmap.arrow1);
    }
}
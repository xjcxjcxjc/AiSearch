package com.example.aisearch.ui.volunteer.community.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseFragment;
import com.example.aisearch.bean.util.volunteer.CommunityItems;
import com.example.aisearch.ui.volunteer.community.adpter.CommunityFragmentAdapter;

import java.util.List;

import butterknife.BindView;

public class CommunityItemFragment extends BaseFragment {

    @BindView(R.id.community_item_recycleview)
    RecyclerView community_item_recycleview;

    private List<CommunityItems> communityItems;
    CommunityFragmentAdapter communityFragmentAdapter;

    public CommunityItemFragment() {
    }

    public CommunityItemFragment(List<CommunityItems> communityItems) {
        this.communityItems = communityItems;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        initRecycleView( );
    }

    @Override
    protected void initBtn(View view, Bundle savedInstanceState) {

    }

    private void initRecycleView( ){
        communityFragmentAdapter=new CommunityFragmentAdapter(getContext(),communityItems);
        community_item_recycleview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        community_item_recycleview.setAdapter(communityFragmentAdapter);

    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_communnity_item;
    }


    public void addContent(CommunityItems communityItem){
        communityItems.add(0,communityItem);
        communityFragmentAdapter.notifyDataSetChanged();
    }

}
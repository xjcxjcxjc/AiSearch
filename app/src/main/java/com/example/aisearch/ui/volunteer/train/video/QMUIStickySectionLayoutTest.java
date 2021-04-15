package com.example.aisearch.ui.volunteer.train.video;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;

import java.util.ArrayList;


/**
 * QMUIStickySectionLayoutTest
 *
 */
public class QMUIStickySectionLayoutTest extends AppCompatActivity {


    private QMUIStickySectionLayout mSectionLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    protected static QMUIStickySectionAdapter<SectionHeader, SectionItem, QMUIStickySectionAdapter.ViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSectionLayout=findViewById(R.id.section_layout);
        initAdapter();
        initAdapterLogicalOperation();

    }

    private void initAdapter() {
        mLayoutManager = createLayoutManager();
        mSectionLayout.setLayoutManager(mLayoutManager);
        mAdapter = createAdapter();
        mSectionLayout.setAnimation(AnimationUtils.loadAnimation(QMUIStickySectionLayoutTest.this, R.anim.close_in));
    }

    private void initAdapterLogicalOperation() {
        mAdapter.setCallback(new QMUIStickySectionAdapter.Callback<SectionHeader, SectionItem>() {
            @Override
            public void loadMore(final QMUISection<SectionHeader, SectionItem> section, final boolean loadMoreBefore) {
            }
            //点击事件
            @Override
            public void onItemClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                if (holder.getItemViewType() != 1) {
                    int pos = holder.isForStickyHeader ? position : holder.getAdapterPosition();
                    mAdapter.toggleFold(pos, false);
                }
                //  Toast.makeText(getContext(), "click item " + mAdapter.getItemIndex(position), Toast.LENGTH_SHORT).show();
            }
            //长按事件
            @Override
            public boolean onItemLongClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                int FirstPos = mAdapter.getSectionIndex(position);
                int SecondPos =mAdapter.getItemIndex(position);
                //  ItemPopupWindow.show(getContext(), holder.itemView, holder.getItemViewType(), FirstPos, SecondPos);
                // Toast.makeText(getContext(), "一级条目" +FirstPos+ "二级"+ SecondPos, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mSectionLayout.setAdapter(mAdapter, true);
        mAdapter.setData(getList());
    }
    protected QMUIStickySectionAdapter<SectionHeader, SectionItem, QMUIStickySectionAdapter.ViewHolder> createAdapter() {
        return new QDListSectionAdapter(true);
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(QMUIStickySectionLayoutTest.this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
    }

    private static ArrayList<QMUISection<SectionHeader, SectionItem>> getList() {
        ArrayList<QMUISection<SectionHeader, SectionItem>> list = new ArrayList<>();

        int n = 0;
        for (int i=0;i < 5;i++) {
            SectionHeader header = new SectionHeader("周末" );
            ArrayList<SectionItem> contents = new ArrayList<>();
            for (int j = 0; j <20/* 二级条目辅助数据*/; j++) {
                contents.add(new SectionItem("标签"+n, "备注"+n, "金额"+n));
                n++;
            }
            QMUISection<SectionHeader, SectionItem> section = new QMUISection<>(header, contents, false);
            list.add(section);
        }
        return list;
    }


}
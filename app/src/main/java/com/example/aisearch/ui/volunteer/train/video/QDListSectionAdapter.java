package com.example.aisearch.ui.volunteer.train.video;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.aisearch.R;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;

public class QDListSectionAdapter extends QMUIDefaultStickySectionAdapter<SectionHeader, SectionItem> {

    private TextView tvTag ,tvNote,tvAccount;

    @NonNull
    @Override
    protected ViewHolder onCreateSectionHeaderViewHolder(@NonNull ViewGroup viewGroup) {
        return new ViewHolder(new QDSectionHeaderView(viewGroup.getContext()));
    }
    public QDListSectionAdapter() {
    }
    public QDListSectionAdapter(boolean removeSectionTitleIfOnlyOneSection) {
        super(removeSectionTitleIfOnlyOneSection);
    }
    @NonNull
    @Override
    protected ViewHolder onCreateSectionItemViewHolder(@NonNull ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        //这里是通过xml绑定布局，当然你也可以用代码new出来，方法请看头部视图
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_stick_item, viewGroup, false);
        return new ViewHolder(itemView);
    }
    @Override
    protected void onBindSectionHeader(final ViewHolder holder, final int position, QMUISection<SectionHeader, SectionItem> section) {
        QDSectionHeaderView itemView = (QDSectionHeaderView) holder.itemView;
        itemView.render(section.getHeader(), section.isFold());
        //点击事件
        itemView.getArrowView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {                 
                    int pos = holder.isForStickyHeader ? position : holder.getAdapterPosition();
                    toggleFold(pos, false);
                }
            });
    }
    @Override
    protected void onBindSectionItem(ViewHolder holder, int position, QMUISection<SectionHeader, SectionItem> section, int itemIndex) {
        //获取id
        tvTag = holder.itemView.findViewById(R.id.Tag_text);
        //设置文本
        tvTag.setText(section.getItemAt(itemIndex).getTag());
        tvTag.setTextColor(Color.GRAY);
        tvNote = holder.itemView.findViewById(R.id.Note_text);
        tvNote.setText(section.getItemAt(itemIndex).getNote());
        tvNote.setTextColor(Color.GRAY);
        tvAccount = holder.itemView.findViewById(R.id.Account_text);
        tvAccount.setText(section.getItemAt(itemIndex).getAccount());
        tvAccount.setTextColor(Color.GRAY);
    }
}

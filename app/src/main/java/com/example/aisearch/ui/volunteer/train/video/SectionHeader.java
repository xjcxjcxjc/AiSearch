package com.example.aisearch.ui.volunteer.train.video;

import com.qmuiteam.qmui.widget.section.QMUISection;

public class SectionHeader implements QMUISection.Model<SectionHeader> {
    private String tvWeek;
    public SectionHeader(String name1 ) {
        this.tvWeek = name1;
    }


    public String getWeek() {
        return tvWeek;
    }
    @Override
    public SectionHeader cloneForDiff() {
        return new SectionHeader(getWeek() );
    }

    @Override
    public boolean isSameItem(SectionHeader other) {
        return tvWeek == other.tvWeek || (tvWeek != null && tvWeek.equals(other.tvWeek));
    }
    @Override
    public boolean isSameContent(SectionHeader other) {
        return true;
    }

    public String getText(){
        return tvWeek;
    }
}
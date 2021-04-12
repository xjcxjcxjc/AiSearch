package com.example.aisearch.ui.volunteer.practice.video;

import com.qmuiteam.qmui.widget.section.QMUISection;

public class SectionItem implements QMUISection.Model<SectionItem> {
    private String tvTag;
    private String tvNote;
    private String tvAccount;

    public SectionItem(String name1, String name2, String name3) {
        this.tvTag = name1;
        this.tvNote = name2;
        this.tvAccount = name3;
    }
    public String getTag() {
        return tvTag;
    }
    public String getNote() {
        return tvNote;
    }
    public String getAccount() {
        return tvAccount;
    }
    @Override
    public SectionItem cloneForDiff() {
        return new SectionItem(getTag(), getNote(), getAccount());
    }
    @Override
    public boolean isSameItem(SectionItem other) {
        return //tvTag == other.tvTag || (tvTag != null && tvTag.equals(other.tvTag))
            tvAccount == other.tvAccount || (tvAccount != null && tvAccount.equals(other.tvAccount));
    }
    @Override
    public boolean isSameContent(SectionItem other) {
        return true;
    }
}

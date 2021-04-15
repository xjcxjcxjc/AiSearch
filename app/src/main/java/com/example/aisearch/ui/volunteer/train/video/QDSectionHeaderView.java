package com.example.aisearch.ui.volunteer.train.video;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.aisearch.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;

public class QDSectionHeaderView extends RelativeLayout {

    private TextView tvWeek, tvDate, tvTotalAmount,mTitleTv;
    private ImageView mArrowView;
    private LinearLayout layout;

    private int headerHeight = QMUIDisplayHelper.dp2px(getContext(), 56);

    public QDSectionHeaderView(Context context) {
        this(context, null);
    }

    public QDSectionHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        layout=new LinearLayout(getContext());
//        layout.setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundColor(getResources().getColor(R.color.half_transparent));
        int paddingHor = QMUIDisplayHelper.dp2px(context, 24);
        mTitleTv = new TextView(getContext());
        mTitleTv.setTextSize(15);
        mTitleTv.setTextColor(Color.WHITE);
        mTitleTv.setPadding(paddingHor, 0, paddingHor, 0);
        mTitleTv.setText("Title");
        mTitleTv.setPadding(100,53,0,0);
        addView(mTitleTv, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));
        mArrowView = new AppCompatImageView(context);
        mArrowView.setImageDrawable(QMUIResHelper.getAttrDrawable(getContext(),
                R.attr.qmui_common_list_item_chevron));
        mArrowView.setScaleType(ImageView.ScaleType.CENTER);
        addView(mArrowView, new LinearLayout.LayoutParams(headerHeight, headerHeight));


    }

    public ImageView getArrowView() {
        return mArrowView;
    }

    public void render(SectionHeader header, boolean isFold) {
        mTitleTv.setText(header.getText());
        mArrowView.setRotation(isFold ? 0f : 90f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(headerHeight, MeasureSpec.EXACTLY));
    }

}
package com.example.aisearch.ui.family.take_a_hand.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.widget.ImageView;

import com.dingmouren.colorpicker.ColorPickerDialog;
import com.dingmouren.colorpicker.OnColorPickerListener;
import com.example.aisearch.R;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

/*
 * @Author : XJC
 * @Time : 2021/3/19 19:04
 * @Description :
 *
 */
public class ColorChangeUtils {


    private static boolean supportAlpha=true;//是否支持透明度

    public static void initColorChange(Context mContext, QMUIRoundButton qmuiRoundButton, ImageView clothes){
        showDialog(mContext,getmOnColorPickerListener(qmuiRoundButton,clothes));

    }



    private static void showDialog(Context mContext,OnColorPickerListener mOnColorPickerListener){

        ColorPickerDialog mColorPickerDialog = new ColorPickerDialog(
                mContext,
                mContext.getResources().getColor(R.color.black),
                supportAlpha,
                mOnColorPickerListener
        );
        mColorPickerDialog.getDialog().setTitle("选择颜色");
        mColorPickerDialog.show();
    }


    private static OnColorPickerListener getmOnColorPickerListener(QMUIRoundButton qmuiRoundButton, ImageView clothes){

        OnColorPickerListener mOnColorPickerListener = new OnColorPickerListener() {
            @Override
            public void onColorCancel(ColorPickerDialog dialog) {//取消选择的颜色

            }

            @Override
            public void onColorChange(ColorPickerDialog dialog, int color) {//实时监听颜色变化

            }

            @Override
            public void onColorConfirm(ColorPickerDialog dialog, int color) {//确定的颜色
                qmuiRoundButton.setBackgroundColor(color);
                clothes.setImageTintList(ColorStateList.valueOf(color));
            }
        };


        return mOnColorPickerListener;
    }


}

package com.example.aisearch.ui.family.take_a_hand.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.aisearch.R;

/*
 * @Author : XJC
 * @Time : 2021/3/19 14:32
 * @Description :
 *
 */
public class ColorDialog  extends Dialog {
    private Context mContext;
    private ClickCallBack mCallBack;


    public ColorDialog(@NonNull Context context, ClickCallBack mCallBack) {
        super(context);
        this.mContext = context;
        this.mCallBack = mCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_color, null);
        setContentView(view);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels; // 把对话框宽度设置为屏幕宽度的0.8
        dialogWindow.setAttributes(lp);
    }


    // 回调接口，执行具体的处理逻辑
    public interface ClickCallBack {
        public void onCancel();
    }


}
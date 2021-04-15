package com.example.aisearch.ui.volunteer.train.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.aisearch.R;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

/*
 * @Author : XJC
 * @Time : 2021/2/12 15:51
 * @Description : 弹出爱心传递dialog
 *
 */
public class PassDialog extends Dialog{
    private Context mContext;
    private ClickCallBack mCallBack;

    public PassDialog(@NonNull Context context, ClickCallBack mCallBack) {
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
        View view = inflater.inflate(R.layout.dialog_practice_pass, null);
        setContentView(view);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = (int) (dm.widthPixels * 0.8); // 把对话框宽度设置为屏幕宽度的0.8
        dialogWindow.setAttributes(lp);

        QMUIRoundButton stop=view.findViewById(R.id.lockdialog_dismiss);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onStop();
                dismiss();
            }
        });

    }

    // 回调接口，执行具体的处理逻辑
    public interface ClickCallBack {
        public void onStop();
    }


}

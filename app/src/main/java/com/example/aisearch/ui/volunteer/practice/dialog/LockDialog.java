package com.example.aisearch.ui.volunteer.practice.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

/*
 * @Author : XJC
 * @Time : 2021/2/12 15:51
 * @Description : 弹出爱心传递dialog
 *
 */
public class LockDialog extends Dialog{
    private Context mContext;
    private ClickCallBack mCallBack;
    private String content;

    public LockDialog(@NonNull Context context,ClickCallBack mCallBack, String content) {
        super(context);
        this.mContext = context;
        this.mCallBack = mCallBack;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }



    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_lock, null);
        setContentView(view);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = (int) (dm.widthPixels * 0.8); // 把对话框宽度设置为屏幕宽度的0.8
        dialogWindow.setAttributes(lp);

        QMUIRoundButton stop=view.findViewById(R.id.lockdialog_dismiss);
        TextView textView=view.findViewById(R.id.lockdialog_content);
        textView.setText(content);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onStop();
                dismiss();
            }
        });

        SharedPreferences sharedPreferences;
        sharedPreferences= mContext.getSharedPreferences("data",  mContext.MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML)
            stop.setBackgroundColor( mContext.getResources().getColor(R.color.title_red));
        else
            stop.setBackgroundColor( mContext.getResources().getColor(R.color.title_blue));


    }

    // 回调接口，执行具体的处理逻辑
    public interface ClickCallBack {
        public void onStop();
    }


}

package com.example.aisearch.ui.volunteer.home.inaction;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.aisearch.R;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

/*
 * @Author : XJC
 * @Time : 2021/2/12 15:51
 * @Description : 弹出爱心传递dialog
 *
 */
public class StopDialog extends Dialog{
    private Context mContext;
    private ClickCallBack mCallBack;

    public StopDialog(Context context, ClickCallBack callBack) {
        super(context, R.style.DialogStyle);
        mContext = context;
        mCallBack = callBack;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_stop, null);
        setContentView(view);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = (int) (dm.widthPixels * 0.8); // 把对话框宽度设置为屏幕宽度的0.8
        dialogWindow.setAttributes(lp);


        ImageView swich=view.findViewById(R.id.stopdialog_swich);
        Button button=view.findViewById(R.id.stopdialog_cancel);
        QMUIRoundButton stop=view.findViewById(R.id.stopdialog_stop);

        swich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swich.setBackgroundColor(mContext.getResources().getColor(R.color.qmui_config_color_gray_7));
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onCancel();
                dismiss();
            }
        });

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
        public void onCancel();
    }


}

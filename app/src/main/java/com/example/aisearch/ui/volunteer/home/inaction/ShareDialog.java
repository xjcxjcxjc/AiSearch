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

/*
 * @Author : XJC
 * @Time : 2021/2/12 15:51
 * @Description : 弹出爱心传递dialog
 *
 */
public class ShareDialog extends Dialog{
    private Context mContext;
    private ClickCallBack mCallBack;

    public ShareDialog(Context context, ClickCallBack callBack) {
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
        View view = inflater.inflate(R.layout.item_share, null);
        setContentView(view);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = (int) (dm.widthPixels * 0.8); // 把对话框宽度设置为屏幕宽度的0.8
        dialogWindow.setAttributes(lp);

        ImageView sharephotos=view.findViewById(R.id.sharedialog_share);
        Button button=view.findViewById(R.id.sharedialog_cancel);

        sharephotos.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mCallBack.onShare();
                dismiss();
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onCancel();
                dismiss();
            }
        });
    }

    // 回调接口，执行具体的处理逻辑
    public interface ClickCallBack {
        public void onShare();
        public void onCancel();
    }


}

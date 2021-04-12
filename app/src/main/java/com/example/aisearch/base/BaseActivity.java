package com.example.aisearch.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aisearch.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.Objects;

import butterknife.ButterKnife;

/**
 * @author hncboy
 * @date 2020/1/17 17:09
 * @description BaseActivity
 *
 * 项佳诚
 * 修改于  2021/1/23
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private ProgressDialog mProgressDialog;
    private InputMethodManager mInputMethodManager;
    Bundle savedInstanceState;

    public BaseActivity() {
    }

    /**
     * 广播关闭所有的activity    demon:
     *                 Intent intent = new Intent();
     *                 intent.setAction("action.exit");
     *                 sendBroadcast(intent);
     *                 finish();
     */
    private static final String EXITACTION = "action.exit";
    private ExitReceiver exitReceiver = new ExitReceiver();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        this.savedInstanceState=savedInstanceState;
        //黄油刀
        ButterKnife.bind(this);
        init();
        initBtn();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //用于注册关闭的广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(EXITACTION);
        registerReceiver(exitReceiver, filter);

        //沉浸式
        QMUIStatusBarHelper.translucent(this);


//        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5ebd7648");
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

    }


    protected Bundle getBundle(){
        return savedInstanceState;
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        unregisterReceiver(exitReceiver);
    }

    public abstract int getLayoutRes();
    protected abstract void init();
    /**
     * 初始化按钮
     */
    protected abstract void initBtn();

    /**
     * 跳转页面
     *
     * @param activity
     */
    protected void startActivity(Class activity) {
        startActivity(activity, false);
    }

    /**
     * 带有参数的跳转页面
     *
     * @param activity
     * @param key
     * @param extra
     */
    protected void startActivity(Class activity, String key, String extra) {
        Intent intent = new Intent(this, activity);
        intent.putExtra(key, extra);
        startActivity(intent);
        overridePendingTransition(R.anim.open_in, R.anim.open_out);
    }

    /**
     * 不带参数的跳转页面
     *
     * @param activity
     * @param finish   是否关闭当前页面
     */
    protected void startActivity(Class activity, boolean finish) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (finish) {
            finish();
        }
        overridePendingTransition(R.anim.open_in, R.anim.open_out);
    }

    /**
     * 延迟Runnable对象的调用
     *
     * @param runnable
     * @param millis
     */
    protected void postDelay(Runnable runnable, long millis) {
        mHandler.postDelayed(runnable, millis);
    }

    /**
     * 显示加载中
     *
     * @param msg
     */
    protected void showProgress(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }


    /**
     * 弹出 toast
     *
     * @param msg
     */
    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 隐藏输入法键盘
     */
    protected void hideKeyBoard() {
        if (mInputMethodManager == null) {
            mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        mInputMethodManager.hideSoftInputFromWindow(
                Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
    }


}


/**
 * 用于关闭所有activity的广播
 */
class ExitReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ((Activity) context).finish();
    }
}

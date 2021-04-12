package com.example.aisearch.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * @author hncboy
 * @date 2020/1/17 17:15
 * @description BaseFragment
 *
 * 项佳诚
 * 修改于  2021/1/23
 *
 */
public abstract class BaseFragment extends Fragment {

    private ProgressDialog mProgressDialog;

    public BaseFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), null);
        ButterKnife.bind(this,view);
        init(view,savedInstanceState);
        initBtn(view,savedInstanceState);
        return view;
    }


    /**
     * @param view fragment初始化的View
     * @param savedInstanceState onCreateView中的Bundle
     */
    protected abstract void init(View view,Bundle savedInstanceState);

    /**
     * @param view fragment初始化的Btn
     * @param savedInstanceState onCreateView中的Bundle
     */
    protected abstract void initBtn(View view,Bundle savedInstanceState);
    /**
     * @return 返回VIew的Resource
     */
    protected abstract int getLayoutRes();

    /**
     * 显示加载中
     *
     * @param msg
     */
    protected void showProgress(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 隐藏加载中
     */
    protected void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 带参数的页面跳转
     *
     * @param activity
     * @param key
     * @param extra
     */
    protected void startActivity(Class activity, String key, String extra) {
        Intent intent = new Intent(getContext(), activity);
        intent.putExtra(key, extra);
        startActivity(intent);
    }

    /**
     * 带参数的页面跳转
     *
     * @param activity
     * @param key
     * @param extra
     * @param finish   是否关闭当前页面
     */
    protected void startActivity(Class activity, String key, String extra, boolean finish) {
        Intent intent = new Intent(getContext(), activity);
        intent.putExtra(key, extra);
        startActivity(intent);
        if (finish) {
            requireActivity().finish();
        }
    }

    /**
     * 跳转页面
     *
     * @param activity
     * @param finish   是否关闭当前页面
     */
    protected void startActivity(Class activity, boolean finish) {
        Intent intent = new Intent(getContext(), activity);
        startActivity(intent);
        if (finish) {
            requireActivity().finish();
        }
    }

    /**
     * 弹出 taost
     *
     * @param msg
     */
    protected void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProgressDialog = null;
    }
}

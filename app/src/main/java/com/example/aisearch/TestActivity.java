package com.example.aisearch;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.family.GetConfigReq;
import com.example.aisearch.util.CommonPopWindow;
import com.example.aisearch.util.PickerScrollView;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

public class TestActivity extends BaseActivity implements CommonPopWindow.ViewClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<GetConfigReq.DatasBean> datasBeanList1;


    @BindView(R.id.btn)
    Button btn;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_test;
    }

    @Override
    protected void init() {

        

    }

    @Override
    protected void initBtn() {
        initData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAddressSelectorPopup(v);
            }
        });
    }
    /**
     * 将选择器放在底部弹出框
     * @param v
     */
    private void setAddressSelectorPopup(View v) {
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;

        CommonPopWindow.newBuilder()
                .setView(R.layout.pop_picker_selector_person_cloth)
//                .setAnimationStyle(R.style.AnimUp)
                .setBackgroundDrawable(new BitmapDrawable())
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(screenHeigh * 0.3f))
                .setViewOnClickListener(this)
                .setBackgroundDarkEnable(true)
                .setBackgroundAlpha(0.7f)
                .setBackgroundDrawable(new ColorDrawable(999999))
                .build(this)
                .showAsBottom(v);
    }

    private void initData() {
        //模拟请求后台返回数据
        String response = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"  0\",\"categoryName\":\"浙江\",\"state\":\"1\"}," +
                "{\"ID\":\"1\",\"categoryName\":\"上海\",\"state\":\"1\"}," +
                "{\"ID\":\"2\",\"categoryName\":\"江西\",\"state\":\"1\"}," +
                "{\"ID\":\"3\",\"categoryName\":\"湖南\",\"state\":\"1\"}," +
                "{\"ID\":\"4\",\"categoryName\":\"湖北\",\"state\":\"1\"}," +
                "{\"ID\":\"5\",\"categoryName\":\"北京\",\"state\":\"1\"}," +
                "{\"ID\":\"6\",\"categoryName\":\"福建\",\"state\":\"1\"}," +
                "{\"ID\":\"7\",\"categoryName\":\"四川\",\"state\":\"1\"}," +
                "{\"ID\":\"8\",\"categoryName\":\"南京\",\"state\":\"1\"}," +
                "{\"ID\":\"9\",\"categoryName\":\"河南\",\"state\":\"1\"}" +
                "]}";


        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        //0请求表示成功
        if (getConfigReq.getRet() == 0) {
            //滚动选择数据集合
            datasBeanList1 = getConfigReq.getDatas();
        }


    }

    @Override
    public void getChildView(PopupWindow mPopupWindow, View view, int mLayoutResId) {
        switch (mLayoutResId) {
            case R.layout.pop_picker_selector_person_cloth:
                TextView textViewBtn = view.findViewById(R.id.img_guanbi2);
                PickerScrollView addressSelector1 = view.findViewById(R.id.select);


                // 设置数据，默认选择第一条
                addressSelector1.setData(datasBeanList1);
                addressSelector1.setSelected(0);

                //滚动监听
                addressSelector1.setOnSelectListener(new PickerScrollView.onSelectListener() {
                    @Override
                    public void onSelect(GetConfigReq.DatasBean pickers) {
                    }
                });

                //完成按钮
                textViewBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                    }
                });
                break;
        }
    }
}
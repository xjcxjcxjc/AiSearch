package com.example.aisearch.ui.family.take_a_hand.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.aisearch.R;
import com.example.aisearch.bean.family.GetConfigReq;
import com.example.aisearch.ui.family.take_a_hand.dialog.PersonDialog;
import com.example.aisearch.util.CommonPopWindow;
import com.example.aisearch.util.PickerScrollView;
import com.example.aisearch.util.UiUtils;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.List;

/*
 * @Author : XJC
 * @Time : 2021/3/18 17:03
 * @Description : 改变衣服的工具类
 *
 */
public class ClothesChangeUtils  {


    public static void ClothesChange(PersonDialog.Clothes clothes, Context context, View v, SelectBack selectBack){
        switch (clothes){
            case HAT:
                setSelectorPopup(v,context,initListener(initHatData(),initHatBitmap(context),selectBack,"鸭舌帽"));
                break;
            case JACK:
                setSelectorPopup(v,context,initListener(initJackData(),initJackBitmap(context),selectBack,"短袖"));
                break;
            case TROUSES:
                setSelectorPopup(v,context,initListener(initTrouseData(),initTrousersBitmap(context),selectBack,"短裤"));
                break;
            case SHOES:
                setSelectorPopup(v,context,initListener(initShoesData(),initShoesBitmap(context),selectBack,"皮鞋"));
                break;
            default:
                break;
        }
    }



    /**
     * 将选择器放在底部弹出框
     * @param v
     */
    private static void setSelectorPopup(View v, Context mContext, CommonPopWindow.ViewClickListener listener) {
        int screenHeigh = mContext.getResources().getDisplayMetrics().heightPixels;


        CommonPopWindow.newBuilder()
                .setView(R.layout.pop_picker_selector_person_cloth)
                .setBackgroundDrawable(new BitmapDrawable())
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(screenHeigh * 0.3f))
                .setViewOnClickListener(listener)
                .setBackgroundDarkEnable(true)
                .setBackgroundAlpha(0.7f)
                .setBackgroundDrawable(new ColorDrawable(999999))
                .build(mContext)
                .showAsBottom(v);
    }

    /**
     * 传入不同List，返回listener
     * @param names
     */
    public static CommonPopWindow.ViewClickListener initListener(List<GetConfigReq.DatasBean> names, List<Bitmap> imgs, SelectBack selectBack,String firstName){


        CommonPopWindow.ViewClickListener viewClickListener= new CommonPopWindow.ViewClickListener() {
            String selectName;
            @Override
            public void getChildView(PopupWindow mPopupWindow, View view, int mLayoutResId) {
                TextView textViewBtn = view.findViewById(R.id.img_guanbi2);
                PickerScrollView addressSelector1 = view.findViewById(R.id.select);
                ImageView select_img=view.findViewById(R.id.select_img);

                // 设置数据，默认选择第一条
                addressSelector1.setData(names);
                addressSelector1.setSelected(0);
                select_img.setImageBitmap(imgs.get(0));
                selectName=firstName;

                //滚动监听
                addressSelector1.setOnSelectListener(new PickerScrollView.onSelectListener() {
                    @Override
                    public void onSelect(GetConfigReq.DatasBean pickers) {
                        selectName=pickers.getCategoryName();
                        int index=Integer.parseInt(pickers.getID());
                        select_img.setImageBitmap(imgs.get(index));
                    }
                });


                //完成按钮
                textViewBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        selectBack.complete(selectName,select_img.getDrawable());
                        selectName="";
                    }
                });
            }
        };

        return viewClickListener;
    }

    public static List initHatData() {
        List<GetConfigReq.DatasBean> datasBeanList1 = null;
        //模拟请求后台返回数据
        String response = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"0\",\"categoryName\":\"鸭舌帽\",\"state\":\"1\"}," +
                "{\"ID\":\"1\",\"categoryName\":\"针织帽\",\"state\":\"1\"}," +
                "{\"ID\":\"2\",\"categoryName\":\"钟形帽\",\"state\":\"1\"}," +
                "{\"ID\":\"3\",\"categoryName\":\"贝雷帽\",\"state\":\"1\"}," +
                "{\"ID\":\"4\",\"categoryName\":\"绅士帽\",\"state\":\"1\"}," +
                "{\"ID\":\"5\",\"categoryName\":\"前进帽\",\"state\":\"1\"}," +
                "{\"ID\":\"6\",\"categoryName\":\"披巾帽\",\"state\":\"1\"}," +
                "{\"ID\":\"7\",\"categoryName\":\"棉耳帽\",\"state\":\"1\"}," +
                "{\"ID\":\"8\",\"categoryName\":\"龙江帽\",\"state\":\"1\"}," +
                "{\"ID\":\"9\",\"categoryName\":\"瓜皮帽\",\"state\":\"1\"}" +
                "]}";
        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        //0请求表示成功
        if (getConfigReq.getRet() == 0) {
            //滚动选择数据集合
            datasBeanList1 = getConfigReq.getDatas();
        }

        return datasBeanList1;
    }

    public static List initJackData() {
        List<GetConfigReq.DatasBean> datasBeanList1 = null;
        //模拟请求后台返回数据
        String response = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"0\",\"categoryName\":\"短袖\",\"state\":\"1\"}," +
                "{\"ID\":\"1\",\"categoryName\":\"背心\",\"state\":\"1\"}," +
                "{\"ID\":\"2\",\"categoryName\":\"礼服\",\"state\":\"1\"}," +
                "{\"ID\":\"3\",\"categoryName\":\"卫衣\",\"state\":\"1\"}," +
                "{\"ID\":\"4\",\"categoryName\":\"西服\",\"state\":\"1\"}," +
                "{\"ID\":\"5\",\"categoryName\":\"衬衫\",\"state\":\"1\"}," +
                "{\"ID\":\"6\",\"categoryName\":\"冲锋衣\",\"state\":\"1\"}," +
                "{\"ID\":\"7\",\"categoryName\":\"风衣\",\"state\":\"1\"}," +
                "{\"ID\":\"8\",\"categoryName\":\"法兰绒\",\"state\":\"1\"}," +
                "{\"ID\":\"9\",\"categoryName\":\"羽绒服\",\"state\":\"1\"}" +
                "]}";
        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        //0请求表示成功
        if (getConfigReq.getRet() == 0) {
            //滚动选择数据集合
            datasBeanList1 = getConfigReq.getDatas();
        }

        return datasBeanList1;
    }

    public static List initTrouseData() {
        List<GetConfigReq.DatasBean> datasBeanList1 = null;
        //模拟请求后台返回数据
        String response = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"0\",\"categoryName\":\"短裤\",\"state\":\"1\"}," +
                "{\"ID\":\"1\",\"categoryName\":\"直筒裤\",\"state\":\"1\"}," +
                "{\"ID\":\"2\",\"categoryName\":\"羊绒裤\",\"state\":\"1\"}," +

                "{\"ID\":\"3\",\"categoryName\":\"牛仔裤\",\"state\":\"1\"}," +
                "{\"ID\":\"4\",\"categoryName\":\"西裤\",\"state\":\"1\"}," +
                "{\"ID\":\"5\",\"categoryName\":\"紧身裤\",\"state\":\"1\"}," +
                "{\"ID\":\"6\",\"categoryName\":\"打底裤\",\"state\":\"1\"}," +
                "{\"ID\":\"7\",\"categoryName\":\"阔腿裤\",\"state\":\"1\"}," +
                "{\"ID\":\"8\",\"categoryName\":\"喇叭裤\",\"state\":\"1\"}," +
                "{\"ID\":\"9\",\"categoryName\":\"工装裤\",\"state\":\"1\"}" +
                "]}";
        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        //0请求表示成功
        if (getConfigReq.getRet() == 0) {
            //滚动选择数据集合
            datasBeanList1 = getConfigReq.getDatas();
        }
        return datasBeanList1;
    }

    public static List initShoesData() {
        List<GetConfigReq.DatasBean> datasBeanList1 = null;
        //模拟请求后台返回数据
        String response = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"0\",\"categoryName\":\"皮鞋\",\"state\":\"1\"}," +
                "{\"ID\":\"1\",\"categoryName\":\"旅游鞋\",\"state\":\"1\"}," +
                "{\"ID\":\"2\",\"categoryName\":\"篮球鞋\",\"state\":\"1\"}," +
                "{\"ID\":\"3\",\"categoryName\":\"靴子\",\"state\":\"1\"}," +
                "{\"ID\":\"4\",\"categoryName\":\"拖鞋\",\"state\":\"1\"}," +
                "{\"ID\":\"5\",\"categoryName\":\"休闲鞋\",\"state\":\"1\"}," +
                "{\"ID\":\"6\",\"categoryName\":\"凉鞋\",\"state\":\"1\"}," +
                "{\"ID\":\"7\",\"categoryName\":\"棉鞋\",\"state\":\"1\"}," +
                "{\"ID\":\"8\",\"categoryName\":\"休闲鞋\",\"state\":\"1\"}," +
                "{\"ID\":\"9\",\"categoryName\":\"凉鞋\",\"state\":\"1\"}" +
                "]}";
        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        //0请求表示成功
        if (getConfigReq.getRet() == 0) {
            //滚动选择数据集合
            datasBeanList1 = getConfigReq.getDatas();
        }

        return datasBeanList1;
    }


    private static List initHatBitmap(Context context){
        List list= Lists.newArrayList(
                UiUtils.resourceToBitmap(context,R.mipmap.hat4),
                UiUtils.resourceToBitmap(context,R.mipmap.hat3),
                UiUtils.resourceToBitmap(context,R.mipmap.hat2),
                UiUtils.resourceToBitmap(context,R.mipmap.hat5),
                UiUtils.resourceToBitmap(context,R.mipmap.hat6),
                UiUtils.resourceToBitmap(context,R.mipmap.hat2),
                UiUtils.resourceToBitmap(context,R.mipmap.hat2),
                UiUtils.resourceToBitmap(context,R.mipmap.hat2),
                UiUtils.resourceToBitmap(context,R.mipmap.hat2),
                UiUtils.resourceToBitmap(context,R.mipmap.hat2)
        );
        return list;
    }

    private static List initJackBitmap(Context context){
        List list= Lists.newArrayList(
                UiUtils.resourceToBitmap(context,R.mipmap.clother2),
                UiUtils.resourceToBitmap(context,R.mipmap.clother3),
                UiUtils.resourceToBitmap(context,R.mipmap.clother4),
                UiUtils.resourceToBitmap(context,R.mipmap.clother5),
                UiUtils.resourceToBitmap(context,R.mipmap.clother6),
                UiUtils.resourceToBitmap(context,R.mipmap.clother7),
                UiUtils.resourceToBitmap(context,R.mipmap.clother8),
                UiUtils.resourceToBitmap(context,R.mipmap.clother2),
                UiUtils.resourceToBitmap(context,R.mipmap.clother2),
                UiUtils.resourceToBitmap(context,R.mipmap.clother2)
        );
        return list;
    }

    private static List initTrousersBitmap(Context context){
        List list= Lists.newArrayList(
                UiUtils.resourceToBitmap(context,R.mipmap.trousers2),
                UiUtils.resourceToBitmap(context,R.mipmap.trousers4),
                UiUtils.resourceToBitmap(context,R.mipmap.trousers2),
                UiUtils.resourceToBitmap(context,R.mipmap.trousers4),
                UiUtils.resourceToBitmap(context,R.mipmap.trousers2),
                UiUtils.resourceToBitmap(context,R.mipmap.trousers4),
                UiUtils.resourceToBitmap(context,R.mipmap.trousers2),
                UiUtils.resourceToBitmap(context,R.mipmap.trousers4),
                UiUtils.resourceToBitmap(context,R.mipmap.trousers2),
                UiUtils.resourceToBitmap(context,R.mipmap.trousers4)

        );
        return list;
    }



    private static List initShoesBitmap(Context context){
        List list= Lists.newArrayList(
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2),
                UiUtils.resourceToBitmap(context,R.mipmap.shoes2)

        );
        return list;
    }

    public interface SelectBack{

        void complete(String name,Drawable drawable);


    }

}

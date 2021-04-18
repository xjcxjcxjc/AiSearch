package com.example.aisearch.ui.family.home.help;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.util.family.GetConfigReq;
import com.example.aisearch.ui.family.home.dialog.PersonDialog;
import com.example.aisearch.ui.volunteer.train.dialog.LockDialog;
import com.example.aisearch.util.CommonPopWindow;
import com.example.aisearch.util.PickerScrollView;
import com.example.aisearch.util.TakePhotoUtils;
import com.example.aisearch.util.UiUtils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

public class HelpActivity extends BaseActivity implements View.OnClickListener,CommonPopWindow.ViewClickListener {

    @BindView(R.id.help_view1card4)
    CardView help_view1card4;
    @BindView(R.id.help_time)
    TextView help_time;
    @BindView(R.id.help_takephoto)
    RelativeLayout help_takephoto;
    @BindView(R.id.help_photo)
    ImageView help_photo;
    @BindView(R.id.help_person)
    ImageView help_person;
    @BindView(R.id.help_losttype)
    TextView click;



    @BindView(R.id.finish)
    Button finish;

    @BindView(R.id.persondialog_hatimg)
    ImageView persondialog_hatimg;
    @BindView(R.id.persondialog_shoesimg)
    ImageView persondialog_shoesimg;
    @BindView(R.id.persondialog_trousersimg)
    ImageView persondialog_trousersimg;
    @BindView(R.id.persondialog_clothimg)
    ImageView persondialog_clothimg;

    @BindView(R.id.check)
    ImageView check;

    @BindView(R.id.btn)
    Button btn;



    private List<GetConfigReq.DatasBean> datasBeanList;
    private String categoryName;


    TakePhotoUtils takePhotoUtils;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_help;
    }

    @Override
    protected void init() {

        takePhotoUtils=new TakePhotoUtils(HelpActivity.this);
        initData();
        initListener();
        initDialog();
//        help_photo.setImageResource(R.mipmap.clipboard);
    }



    @Override
    protected void initBtn() {
        help_view1card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HelpActivity2.class,true);
            }
        });
        help_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.ShowTimeSelector(HelpActivity.this,help_time);
            }
        });
        help_takephoto.setOnClickListener(takePhotoUtils);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /**
         * 弹出人
         */
        help_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPersonDialog();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check.setImageResource(R.mipmap.help_img4);


            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check.setImageResource(R.mipmap.help_img4);

            }
        });



    }



    private void initPersonDialog(){

        PersonDialog.ClickCallBack clickCallBack= new PersonDialog.ClickCallBack() {

            @Override
            public void onCancel(Drawable hat, Drawable jack, Drawable trouses, Drawable shoe) {
                persondialog_hatimg.setImageDrawable(hat);
                persondialog_clothimg.setImageDrawable(jack);
                persondialog_trousersimg.setImageDrawable(trouses);
                persondialog_shoesimg.setImageDrawable(shoe);
            }
        };

        PersonDialog personDialog=new PersonDialog(HelpActivity.this, clickCallBack);
        personDialog.show();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        help_photo.setImageBitmap(takePhotoUtils.PhotoActivityResult(requestCode, resultCode, data));


    }



    private void initListener() {
        click.setOnClickListener(this);
    }

    private void initData() {
        //模拟请求后台返回数据
        String response = "{\"ret\":0,\"msg\":\"succes,\",\"datas\":[{\"ID\":\"  0\",\"categoryName\":\"老人走失\",\"state\":\"1\"},{\"ID\":\"1\",\"categoryName\":\"离家出走\",\"state\":\"1\"},{\"ID\":\"2\",\"categoryName\":\"小孩拐卖\",\"state\":\"1\"}]}";
        GetConfigReq getConfigReq = new Gson().fromJson(response, GetConfigReq.class);
        //0请求表示成功
        if (getConfigReq.getRet() == 0) {
            //滚动选择数据集合
            datasBeanList = getConfigReq.getDatas();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.help_losttype:
                setAddressSelectorPopup(v);
                break;
        }
    }

    /**
     * 将选择器放在底部弹出框
     * @param v
     */
    private void setAddressSelectorPopup(View v) {
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;

        CommonPopWindow.newBuilder()
                .setView(R.layout.pop_picker_selector_bottom)
//                .setAnimationStyle(R.style.AnimationFade2)
                .setBackgroundDrawable(new BitmapDrawable())
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(screenHeigh * 0.3f))
                .setViewOnClickListener(this)
                .setBackgroundDarkEnable(true)
                .setBackgroundAlpha(0.7f)
                .setBackgroundDrawable(new ColorDrawable(999999))
                .build(this)
                .showAsBottom(v);
    }


    @Override
    public void getChildView(final PopupWindow mPopupWindow, View view, int mLayoutResId) {
        switch (mLayoutResId) {
            case R.layout.pop_picker_selector_bottom:
                TextView imageBtn = view.findViewById(R.id.img_guanbi);
                PickerScrollView addressSelector = view.findViewById(R.id.address);
                // 设置数据，默认选择第一条
                addressSelector.setData(datasBeanList);
                addressSelector.setSelected(0);

                //滚动监听
                addressSelector.setOnSelectListener(new PickerScrollView.onSelectListener() {
                    @Override
                    public void onSelect(GetConfigReq.DatasBean pickers) {
                        categoryName = pickers.getCategoryName();

                    }
                });
                //完成按钮
                imageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        click.setText(categoryName);
                    }
                });
                break;


        }
    }

    LockDialog lockDialog;
    private void initDialog(){

//        lockDialog=new LockDialog(this, new LockDialog.ClickCallBack() {
//            @Override
//            public void onStop() {
//                lockDialog.dismiss();
//            }
//        },
//                "*号内容为必填项");
//        lockDialog.show();


    }



}
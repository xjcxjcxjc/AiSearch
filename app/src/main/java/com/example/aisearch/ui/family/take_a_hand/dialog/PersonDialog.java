package com.example.aisearch.ui.family.take_a_hand.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import com.example.aisearch.R;
import com.example.aisearch.ui.family.take_a_hand.util.ClothesChangeUtils;
import com.example.aisearch.ui.family.take_a_hand.util.ColorChangeUtils;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * @Author : XJC
 * @Time : 2021/3/17 9:05
 * @Description :
 *
 */
public class PersonDialog  extends Dialog implements View.OnClickListener {
    private Context mContext;
    private ClickCallBack mCallBack;

    public enum Clothes {
        HAT, JACK, TROUSES, SHOES
    }

    @BindView(R.id.persondialog_hat)
    CardView persondialog_hat;
    @BindView(R.id.persondialog_cloth)
    CardView persondialog_cloth;
    @BindView(R.id.persondialog_trousers)
    CardView persondialog_trousers;
    @BindView(R.id.persondialog_shoe)
    CardView persondialog_shoe;


    @BindView(R.id.persondialog_hat_select)
    TextView persondialog_hat_select;
    @BindView(R.id.persondialog_cloth_select)
    TextView persondialog_cloth_select;
    @BindView(R.id.persondialog_trousers_select)
    TextView persondialog_trousers_select;
    @BindView(R.id.persondialog_shoe_select)
    TextView persondialog_shoe_select;

    @BindView(R.id.persondialog_clothimg)
    ImageView persondialog_clothimg;
    @BindView(R.id.persondialog_trousersimg)
    ImageView persondialog_trousersimg;
    @BindView(R.id.persondialog_hatimg)
    ImageView persondialog_hatimg;
    @BindView(R.id.persondialog_shoesimg)
    ImageView persondialog_shoesimg;

    @BindView(R.id.cancel)
    Button cancel;

    @BindView(R.id.persondialog_hatcolor)
    QMUIRoundButton persondialog_hatcolor;
    @BindView(R.id.persondialog_clothcolor)
    QMUIRoundButton persondialog_clothcolor;
    @BindView(R.id.persondialog_trouserscolor)
    QMUIRoundButton persondialog_trouserscolor;
    @BindView(R.id.persondialog_shoecolor)
    QMUIRoundButton persondialog_shoecolor;

    ClothesChangeUtils clothesChangeUtils;


    // 回调接口，执行具体的处理逻辑
    public interface ClickCallBack {
        public void onCancel(Drawable hat,Drawable jack,Drawable trouses,Drawable shoe);
    }

    public PersonDialog(Context context, ClickCallBack callBack) {
        super(context);
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
        View view = inflater.inflate(R.layout.dialog_help_person, null);
        setContentView(view);
        ButterKnife.bind(this,view);
        initBtn();
        clothesChangeUtils= new ClothesChangeUtils();

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = (int) (dm.widthPixels * 0.9); // 把对话框宽度设置为屏幕宽度的0.8
        dialogWindow.setAttributes(lp);
    }


    private void initBtn(){
        persondialog_hat.setOnClickListener(this);
        persondialog_cloth.setOnClickListener(this);
        persondialog_trousers.setOnClickListener(this);
        persondialog_shoe.setOnClickListener(this);
        persondialog_hatcolor.setOnClickListener(this);
        persondialog_clothcolor.setOnClickListener(this);
        persondialog_trouserscolor.setOnClickListener(this);
        persondialog_shoecolor.setOnClickListener(this);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mCallBack.onCancel(persondialog_hatimg.getDrawable(),persondialog_clothimg.getDrawable(),persondialog_trousersimg.getDrawable(),
                        persondialog_shoesimg.getDrawable());
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.persondialog_hat:
                ClothesChangeUtils.ClothesChange(Clothes.HAT,mContext,v, (name, drawable) -> {
                    persondialog_hat_select.setText(name);
                    persondialog_hatimg.setImageDrawable(drawable);
                });
                break;
            case R.id.persondialog_trousers:
                ClothesChangeUtils.ClothesChange(Clothes.TROUSES,mContext,v, (name, drawable) -> {
                    persondialog_trousers_select.setText(name);
                    persondialog_trousersimg.setImageDrawable(drawable);
                });
                break;
            case R.id.persondialog_cloth:
                ClothesChangeUtils.ClothesChange(Clothes.JACK,mContext,v, (name, drawable) -> {
                    persondialog_cloth_select.setText(name);
                    persondialog_clothimg.setImageDrawable(drawable);
                });
                break;
            case R.id.persondialog_shoe:
                ClothesChangeUtils.ClothesChange(Clothes.SHOES,mContext,v, (name, drawable) -> {
                    persondialog_shoe_select.setText(name);
                    persondialog_shoesimg.setImageDrawable(drawable);
                });
                break;
            /**
             * 改变颜色的点击事件
             */
            case R.id.persondialog_hatcolor:
                ColorChangeUtils.initColorChange(mContext,persondialog_hatcolor,persondialog_hatimg);
                break;
            case R.id.persondialog_clothcolor:
                ColorChangeUtils.initColorChange(mContext,persondialog_clothcolor,persondialog_clothimg);
                break;
            case R.id.persondialog_trouserscolor:
                ColorChangeUtils.initColorChange(mContext,persondialog_trouserscolor,persondialog_trousersimg);
                break;
            case R.id.persondialog_shoecolor:
                ColorChangeUtils.initColorChange(mContext,persondialog_shoecolor,persondialog_shoesimg);
                break;
        }
    }


}
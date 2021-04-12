package com.example.aisearch.ui.volunteer.action;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.volunteer.action.inaction.InActionActivity2;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
@SuppressLint("NonConstantResourceId")
public class JoinActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.join_finish)
    Button finish;
    @BindView(R.id.join_submit)
    CardView cardView;


    @BindView(R.id.join_rg1)
    RadioGroup rg1;
    @BindView(R.id.join_rg2)
    RadioGroup rg2;

    @BindView(R.id.join_rg1_rb1)
    RadioButton rg1_rb1;
    @BindView(R.id.join_rg1_rb2)
    RadioButton rg1_rb2;
    @BindView(R.id.join_rg1_rb3)
    RadioButton rg1_rb3;
    @BindView(R.id.join_rg1_rb4)
    RadioButton rg1_rb4;
    @BindView(R.id.join_rg1_rb5)
    RadioButton rg1_rb5;

    @BindView(R.id.join_rg2_rb1)
    RadioButton rg2_rb1;
    @BindView(R.id.join_rg2_rb2)
    RadioButton rg2_rb2;
    @BindView(R.id.join_rg2_rb3)
    RadioButton rg2_rb3;
    @BindView(R.id.join_rg2_rb4)
    RadioButton rg2_rb4;
    @BindView(R.id.join_rg2_rb5)
    RadioButton rg2_rb5;

    @BindView(R.id.join_img1)
    ImageView img1;
    @BindView(R.id.join_img2)
    ImageView img2;
    @BindView(R.id.join_img3)
    ImageView img3;
    @BindView(R.id.join_img4)
    ImageView img4;
    @BindView(R.id.join_img5)
    ImageView img5;
    @BindView(R.id.join_img6)
    ImageView img6;
    @BindView(R.id.join_img7)
    ImageView img7;
    @BindView(R.id.join_img8)
    ImageView img8;


    @BindView(R.id.join_tv1)
    TextView join_tv1;
    @BindView(R.id.join_tv2)
    TextView join_tv2;
    @BindView(R.id.join_tv3)
    TextView join_tv3;
    @BindView(R.id.join_tv4)
    TextView join_tv4;
    @BindView(R.id.join_tv5)
    TextView join_tv5;
    @BindView(R.id.join_tv6)
    TextView join_tv6;
    @BindView(R.id.join_tv7)
    TextView join_tv7;
    @BindView(R.id.join_tv8)
    TextView join_tv8;


    @BindView(R.id.rg)
    RadioGroup sex_rg;
    @BindView(R.id.rb1)
    RadioButton sex_rb1;
    @BindView(R.id.rb2)
    RadioButton sex_rb2;

    @BindView(R.id.img_check1)
    ImageView check1;
    @BindView(R.id.img_check2)
    ImageView check2;


    /**
     * 行动意向
     */
    @BindView(R.id.ac_rg1)
    RadioGroup ac_rg1;

    @BindView(R.id.ac_rb1)
    RadioButton ac_rb1;
    @BindView(R.id.ac_rb2)
    RadioButton ac_rb2;
    @BindView(R.id.ac_rb3)
    RadioButton ac_rb3;
    @BindView(R.id.ac_rb4)
    RadioButton ac_rb4;

    @BindView(R.id.leader_img)
    ImageView leader_img;
    @BindView(R.id.eye_img)
    ImageView eye_img;
    @BindView(R.id.help_img)
    ImageView help_img;
    @BindView(R.id.cpt_img)
    ImageView cpt_img;

    @BindView(R.id.check1)
    ImageView agreement_check1;
    @BindView(R.id.check2)
    ImageView agreement_check2;

    private int checkrg=1;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_join;
    }

    @Override
    protected void init() {
        QMUIStatusBarHelper.translucent(this);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    @Override
    protected void initBtn() {
        rg1.check(R.id.join_rg1_rb5);
        rg2.check(R.id.join_rg2_rb5);
        rg1_rb1.setOnCheckedChangeListener(this);
        rg1_rb2.setOnCheckedChangeListener(this);
        rg1_rb3.setOnCheckedChangeListener(this);
        rg1_rb4.setOnCheckedChangeListener(this);
        rg2_rb1.setOnCheckedChangeListener(this);
        rg2_rb2.setOnCheckedChangeListener(this);
        rg2_rb3.setOnCheckedChangeListener(this);
        rg2_rb4.setOnCheckedChangeListener(this);
        ac_rb1.setOnCheckedChangeListener(this);
        ac_rb2.setOnCheckedChangeListener(this);
        ac_rb3.setOnCheckedChangeListener(this);
        ac_rb4.setOnCheckedChangeListener(this);


        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.join_rg2_rb5!=rg2.getCheckedRadioButtonId()){
                    rg2.check(R.id.join_rg2_rb5);
                }
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.join_rg1_rb5!=rg1.getCheckedRadioButtonId()){
                    rg1.check(R.id.join_rg1_rb5);
                }

                rg1.getCheckedRadioButtonId();
            }
        });



        sex_rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    check1.setImageDrawable(getResources().getDrawable(R.mipmap.check1));
                else
                    check1.setImageDrawable(getResources().getDrawable(R.mipmap.check2));
            }
        });
        sex_rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    check2.setImageDrawable(getResources().getDrawable(R.mipmap.check1));
                else
                    check2.setImageDrawable(getResources().getDrawable(R.mipmap.check2));
            }
        });

        agreement_check1.setOnClickListener(v -> {
            agreement_check1.setImageResource(R.mipmap.round_yes);
        });
        agreement_check2.setOnClickListener(v -> {
            agreement_check2.setImageResource(R.mipmap.round_yes);
        });


    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.join_rg1_rb1:
                if (isChecked) {
                    img1.setImageResource(R.mipmap.background15);
                    join_tv1.setTextColor(getResources().getColor(R.color.white));
                } else {
                    img1.setImageResource(R.mipmap.background14);
                    join_tv1.setTextColor(getResources().getColor(R.color.text_gray1));
                }
                break;
            case R.id.join_rg1_rb2:
                if (isChecked) {
                    img2.setImageResource(R.mipmap.background15);
                    join_tv2.setTextColor(getResources().getColor(R.color.white));
                } else {
                    img2.setImageResource(R.mipmap.background14);
                    join_tv2.setTextColor(getResources().getColor(R.color.text_gray1));
                }
                break;
            case R.id.join_rg1_rb3:
                if (isChecked) {
                    img3.setImageResource(R.mipmap.background15);
                    join_tv3.setTextColor(getResources().getColor(R.color.white));
                } else {
                    img3.setImageResource(R.mipmap.background14);
                    join_tv3.setTextColor(getResources().getColor(R.color.text_gray1));
                }
                break;
            case R.id.join_rg1_rb4:
                if (isChecked) {
                    img4.setImageResource(R.mipmap.background15);
                    join_tv4.setTextColor(getResources().getColor(R.color.white));
                } else {
                    img4.setImageResource(R.mipmap.background14);
                    join_tv4.setTextColor(getResources().getColor(R.color.text_gray1));
                }
                break;
            case R.id.join_rg2_rb1:
                if (isChecked) {
                    img5.setImageResource(R.mipmap.background15);
                    join_tv5.setTextColor(getResources().getColor(R.color.white));
                } else {
                    img5.setImageResource(R.mipmap.background14);
                    join_tv5.setTextColor(getResources().getColor(R.color.text_gray1));
                }
                break;
            case R.id.join_rg2_rb2:
                if (isChecked) {
                    img6.setImageResource(R.mipmap.background15);
                    join_tv6.setTextColor(getResources().getColor(R.color.white));
                } else {
                    img6.setImageResource(R.mipmap.background14);
                    join_tv6.setTextColor(getResources().getColor(R.color.text_gray1));
                }
                break;
            case R.id.join_rg2_rb3:
                if (isChecked) {
                    img7.setImageResource(R.mipmap.background15);
                    join_tv7.setTextColor(getResources().getColor(R.color.white));
                } else {
                    img7.setImageResource(R.mipmap.background14);
                    join_tv7.setTextColor(getResources().getColor(R.color.text_gray1));
                }
                break;
            case R.id.join_rg2_rb4:
                if (isChecked) {
                    img8.setImageResource(R.mipmap.background15);
                    join_tv8.setTextColor(getResources().getColor(R.color.white));
                } else {
                    img8.setImageResource(R.mipmap.background14);
                    join_tv8.setTextColor(getResources().getColor(R.color.text_gray1));
                }
                break;
            case R.id.ac_rb1:
                if (isChecked) {
                    leader_img.setImageResource(R.mipmap.leader);
                } else {
                    leader_img.setImageResource(R.mipmap.leader2);
                }
                break;
            case R.id.ac_rb2:
                if (isChecked) {
                    eye_img.setImageResource(R.mipmap.round_eyes);
                } else {
                    eye_img.setImageResource(R.mipmap.round_eyes2);
                }
                break;
            case R.id.ac_rb3:
                if (isChecked) {
                    help_img.setImageResource(R.mipmap.round_help);
                } else {
                    help_img.setImageResource(R.mipmap.round_help2);
                }
                break;
            case R.id.ac_rb4:
                if (isChecked) {
                    cpt_img.setImageResource(R.mipmap.round_cpt);
                } else {
                    cpt_img.setImageResource(R.mipmap.round_cpt2);
                }
                break;
        }
    }



    private void submit(){
        startActivity(InActionActivity2.class);
    }


}
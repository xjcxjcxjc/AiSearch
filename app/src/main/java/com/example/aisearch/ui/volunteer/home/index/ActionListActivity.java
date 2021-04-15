package com.example.aisearch.ui.volunteer.home.index;

import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.bean.ActionListItem;
import com.example.aisearch.bean.Person;
import com.example.aisearch.ui.volunteer.home.index.adapter.ActionCenterAdapter;
import com.example.aisearch.ui.volunteer.home.index.adapter.SearchNoticeAdapter;
import com.example.aisearch.util.UiUtils;
import com.example.aisearch.util.datautil.DataUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionListActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

//    @BindView(R.id.actionlist_finish)
//    Button finish;
//
//    @BindView(R.id.actionlist_recycle)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.actionlist_input)
//    EditText editText;
//
//    @BindView(R.id.itemview)
//    LinearLayout itemview;
//
//
//    @Override
//    public int getLayoutRes() {
//        return R.layout.activity_action_list;
//    }
//
//    @Override
//    protected void init() {
//        QMUIStatusBarHelper.translucent(this);
//        ButterKnife.bind(this);
//        initRecycleView();
//    }
//
//    protected void initBtn() {
//        finish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View v) {
//    }
//
//    private void initRecycleView(){
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        SharedPreferences sharedPreferences;
//        sharedPreferences= getSharedPreferences("data",  MODE_PRIVATE);   //私自模式，只允许本应用访问
//        int identity= sharedPreferences.getInt("identity",0);
//
//        if (identity== LodingActivity.IDENTIFY_FML){
//            itemview.setBackgroundColor(getResources().getColor(R.color.title_red));
//            recyclerView.setAdapter(new SearchNoticeAdapter(this,initData2(),0));
//        } else{
//            itemview.setBackgroundColor(getResources().getColor(R.color.title_blue));
//            recyclerView.setAdapter(new ActionCenterAdapter(this, DetailsActivity.class,initActionList()));
//        }
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//    }
//    private List<ActionListItem> initActionList(){
//        List<ActionListItem> actionListItems=new ArrayList<>();
//        actionListItems.add(new ActionListItem(UiUtils.resourceToBitmap(this,R.mipmap.clipboard), "海曙老人救援行动",
//                "宁波市海曙区学院路附近", "2021年3月9日8:00——3月10日8:00", "城市寻人", "志愿保险", "低风险", 5, 25));
//
//        return actionListItems;
//    }
//
//
//    private List<Person> initData2(){
//        List<Person> noticePeople =new ArrayList<>();
//        noticePeople.add(new Person(UiUtils.resourceToBitmap(ActionListActivity.this,R.mipmap.clipboard),
//                "应槟鸿","男", "28","秃头  阿兹海默症", "秃头"
//                , "阿兹海默症","阿兹海默症","黑裤子 红帽子", "黑裤子", "红帽子", UiUtils.Lost_Time, "宁波市海曙区学院路附近"
//                , "2000.6.6", "浙江台州", "175", "老人走失", ""));
//
//        return noticePeople;
//
//
//    }


    @BindView(R.id.actionlist_finish)
    Button finish;

    @BindView(R.id.actionlist_recycle)
    RecyclerView recyclerView;

    @BindView(R.id.actionlist_input)
    EditText editText;

    @BindView(R.id.itemview)
    LinearLayout itemview;

    @BindView(R.id.iv_screen)
    ImageView mIvScreen;
    private PopupWindow popupWindow;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_action_list;
    }

    @Override
    protected void init() {
        QMUIStatusBarHelper.translucent(this);
        ButterKnife.bind(this);
        initRecycleView();
    }


    protected void initBtn() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        View.OnClickListener popClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPopupWindow();
                //popupWindow.showAsDropDown(v);

                popupWindow.showAsDropDown(v, -200, 20);
                popupWindow.showAtLocation(findViewById(R.id.iv_screen),
                        Gravity.CENTER, -120, -800);

            }
        };
        mIvScreen.setOnClickListener(popClick);

    }

    @Override
    public void onClick(View v) {
    }

    private void initRecycleView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences;
        sharedPreferences= getSharedPreferences("data",  MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML){
            itemview.setBackgroundColor(getResources().getColor(R.color.title_red));
            recyclerView.setAdapter(new SearchNoticeAdapter(this,initData2(),0));
        } else{
            itemview.setBackgroundColor(getResources().getColor(R.color.title_blue));
            recyclerView.setAdapter(new ActionCenterAdapter(this, DetailsActivity.class,initActionList()));
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
    private List<ActionListItem> initActionList(){
        List<ActionListItem> actionListItems=new ArrayList<>();
        actionListItems.add(new ActionListItem(UiUtils.resourceToBitmap(this,R.mipmap.clipboard), "海曙老人救援行动",
                "宁波市海曙区学院路附近", "2021年3月9日8:00——3月10日8:00", "城市寻人", "志愿保险", "低风险", 5, 25));

        return actionListItems;
    }


    private List<Person> initData2(){
        List<Person> notices=new ArrayList<>();
        notices.add(DataUtil.getNoticePerson(this));

        return notices;
    }
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }

    protected void initPopuptWindow() {

        View popupWindow_view = getLayoutInflater().inflate(R.layout.dialog_screen, null,
                false);

        popupWindow = new PopupWindow(popupWindow_view, 730, 80, true);

        popupWindow.setAnimationStyle(R.style.AnimationFade2);

        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (popupWindow != null && popupWindow.isShowing()) {

                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;

            }
        });


    }

}
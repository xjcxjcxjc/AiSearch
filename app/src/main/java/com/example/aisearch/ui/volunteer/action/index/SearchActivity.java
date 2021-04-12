package com.example.aisearch.ui.volunteer.action.index;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.volunteer.action.index.adapter.RecordListAdapter;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends BaseActivity {

    private List<String> records;
    private EditText input;
    RecyclerView recordlist;
    RecordListAdapter adapter;
    Button back;



    @Override
    public int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        initEditText();
        initRecycleView();
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(SearchActivity.this);

        LinearLayout linearLayout=findViewById(R.id.background);
        SharedPreferences sharedPreferences;
        sharedPreferences= getSharedPreferences("data",  MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML)
            linearLayout.setBackgroundColor(getResources().getColor(R.color.title_red));
        else
            linearLayout.setBackgroundColor(getResources().getColor(R.color.title_blue));


    }

    @Override
    protected void initBtn() {

        QMUIAlphaButton clear=findViewById(R.id.search_record_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        back=findViewById(R.id.search_top_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initEditText() {
        input=findViewById(R.id.search_top_input);
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });

        input.setFocusable(true);
        input.setFocusableInTouchMode(true);
        input.requestFocus();

        InputMethodManager inputManager =
                (InputMethodManager)input.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run() {
                InputMethodManager inputManager =
                        (InputMethodManager)input.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(input, 0);
            }
        }, 1000);

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_UP) {
                    startActivity(ActionListActivity.class);
                }
                return true;
            }
        });
    }

    private void initRecycleView() {
        initData();
        recordlist=findViewById(R.id.search_record_recycle);
        recordlist.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        adapter=new RecordListAdapter(this,records,input);
        recordlist.setAdapter(adapter);
    }


    private void initData() {
        records=new ArrayList<>();
        records.add("蓝天救援队");
        records.add("救援队");
        records.add("宁波行动");
        records.add("活动");
        records.add("志愿者");
        records.add("海曙区");
        records.add("宁波财经学院");
        records.add("701");
        records.add("时间");
        records.add("新手教程");
    }

    private void clearData() {
        records.clear();
        adapter.notifyDataSetChanged();


    }
}
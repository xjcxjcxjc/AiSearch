package com.example.aisearch.ui.volunteer.practice;


import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.example.aisearch.base.BaseActivity;
import com.example.aisearch.ui.volunteer.action.index.adapter.RecordListAdapter;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PracticeSearchActivity extends BaseActivity {

    private List<String> records;
    private EditText input;
    RecyclerView recordlist;
    RecordListAdapter adapter;
    Button back;



    @Override
    public int getLayoutRes() {
        return R.layout.activity_practicce_search2;
    }

    @Override
    protected void init() {
        initEditText();
        initRecycleView();
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(PracticeSearchActivity.this);
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
                    startActivity(SearchResultActivity.class);
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
        records.add("急救");
        records.add("野外生存");
    }

    private void clearData() {
        records.clear();
        adapter.notifyDataSetChanged();


    }
}
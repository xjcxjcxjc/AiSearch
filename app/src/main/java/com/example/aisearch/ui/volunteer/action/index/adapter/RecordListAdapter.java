package com.example.aisearch.ui.volunteer.action.index.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.aisearch.R;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.List;

/**
 * 寻人启事的adapter
 */
public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.recycleHoder> {

    private final Context context;
    private List<String> records;
    private final EditText input;

    public RecordListAdapter(Context context, List<String> records, EditText input) {
        this.context = context;
        this.records = records;
        this.input = input;
    }

    @NonNull
    @Override
    public recycleHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_action_search_recycleview,null,false);
        return new recycleHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleHoder holder, int position) {
//        holder.qmuiRoundButton.setText(records.get(position));
//        holder.qmuiRoundButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                input.setText(records.get(position));
//            }
//        });

        holder.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        holder.recyclerView.setAdapter(new RecordItemAdapter(position));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class recycleHoder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        public recycleHoder(@NonNull View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.recycle_item);
        }
    }

    private class RecordItemAdapter extends RecyclerView.Adapter<RecordItemAdapter.itemHoder>{

        //到第几个recycleview了
        int recycleposition;

        public RecordItemAdapter(int recycleposition) {
            this.recycleposition = recycleposition;
        }

        @NonNull
        @Override
        public itemHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).inflate(R.layout.item_action_search_record,null,false);
            return new itemHoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull itemHoder holder, int position) {
            holder.qmuiRoundButton.setText(records.get(recycleposition*3+position));
            holder.qmuiRoundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    input.setText(records.get(recycleposition*3+position));

                }
            });
        }

        @Override
        public int getItemCount() {
            if(recycleposition*3+3<=records.size())
                return 3;
            else
                return records.size()-recycleposition*3;

        }

        class itemHoder extends RecyclerView.ViewHolder {
            QMUIRoundButton qmuiRoundButton;
            public itemHoder(@NonNull View itemView) {
                super(itemView);
                qmuiRoundButton=itemView.findViewById(R.id.search_record_btn);
            }
        }
    }


}

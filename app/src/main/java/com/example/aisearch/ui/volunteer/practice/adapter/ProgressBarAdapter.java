package com.example.aisearch.ui.volunteer.practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class ProgressBarAdapter extends RecyclerView.Adapter<ProgressBarAdapter.myHoder> {

    private Context context;

    /**
     * 用来记录得分
     */
    private int score;


    public ProgressBarAdapter(Context context ) {
        this.context = context;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_practicetest_progressbar,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.practice_progressbar_nub.setText(Integer.toString(position+1));
        if (position==0)
            holder.practice_progressbar_bar.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class myHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.practice_progressbar_bar)
        TextView practice_progressbar_bar;
        @BindView(R.id.practice_progressbar_nub)
        TextView practice_progressbar_nub;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

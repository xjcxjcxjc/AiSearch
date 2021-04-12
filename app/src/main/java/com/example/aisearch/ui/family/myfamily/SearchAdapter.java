package com.example.aisearch.ui.family.myfamily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.Clue;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.myHoder> {

    private Context context;
    private List<Clue> clues;

    public SearchAdapter(Context context, List<Clue> clues) {
        this.context = context;
        this.clues = clues;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_search_clue,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.search_clue_head.setImageBitmap(clues.get(position).getHeadimg());
        holder.search_clue_name.setText(clues.get(position).getName());
        holder.search_clue_time.setText(clues.get(position).getTime());
        holder.search_clue_describe.setText(clues.get(position).getClue());


    }


    @Override
    public int getItemCount() {
        return clues.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.search_clue_head)
        ImageView search_clue_head;
        @BindView(R.id.search_clue_name)
        TextView search_clue_name;
        @BindView(R.id.search_clue_time)
        TextView search_clue_time;
        @BindView(R.id.search_clue_describe)
        TextView search_clue_describe;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

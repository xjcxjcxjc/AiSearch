package com.example.aisearch.ui.family.myfamily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;

import butterknife.ButterKnife;

/**
 *
 */
public class UrgentHelpAdapter extends RecyclerView.Adapter<UrgentHelpAdapter.myHoder> {

    private Context context;
    public UrgentHelpAdapter(Context context ) {
        this.context = context;
    }


    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_mine2_urgent,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class myHoder extends RecyclerView.ViewHolder {

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

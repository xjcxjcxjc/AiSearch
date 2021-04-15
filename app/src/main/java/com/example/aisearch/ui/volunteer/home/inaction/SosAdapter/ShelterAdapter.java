package com.example.aisearch.ui.volunteer.home.inaction.SosAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;

import butterknife.ButterKnife;

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.myHoder> {


    private Context context;
    public ShelterAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_sos_shelter,parent,false);
        return new myHoder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    class myHoder extends RecyclerView.ViewHolder {


        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

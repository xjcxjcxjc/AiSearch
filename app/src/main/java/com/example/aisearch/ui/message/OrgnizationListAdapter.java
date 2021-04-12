package com.example.aisearch.ui.message;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.Message.OrgnizationItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class OrgnizationListAdapter extends RecyclerView.Adapter<OrgnizationListAdapter.myHoder> {

    private Context context;
    private List<OrgnizationItem> orgnizationItems;


    public OrgnizationListAdapter(Context context, List<OrgnizationItem> orgnizationItems) {
        this.context = context;
        this.orgnizationItems = orgnizationItems;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_orgnization_list,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {

        holder.orgnization2_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,TeamMatesActivity.class));
            }
        });




    }

    @Override
    public int getItemCount() {
        return orgnizationItems.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.orgnization2_btn2)
        RelativeLayout orgnization2_btn2;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

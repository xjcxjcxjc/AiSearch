package com.example.aisearch.ui.volunteer.action.inaction.SosAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.volunteer.SOSBulid;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CopAdapter extends RecyclerView.Adapter<CopAdapter.myHoder> {


    private Context context;
    private List<SOSBulid> cops;

    public CopAdapter(Context context, List<SOSBulid> cops) {
        this.context = context;
        this.cops = cops;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_sos_cop,parent,false);
        return new myHoder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.item_cop_title.setText(cops.get(position).getName());
        holder.item_cop_location.setText(cops.get(position).getAddress());

    }


    @Override
    public int getItemCount() {
        return cops.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_cop_title)
        TextView item_cop_title;

        @BindView(R.id.sos_cop_lable1tv)
        TextView sos_cop_lable1tv;

        @BindView(R.id.item_cop_location)
        TextView item_cop_location;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

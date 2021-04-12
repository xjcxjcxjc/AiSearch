package com.example.aisearch.ui.volunteer.action.beforeaction;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;

import java.util.List;

/**
 * Author WangJie
 * Created on 2019/1/21.
 */
public class SignAdapter extends RecyclerView.Adapter<SignAdapter.ViewHolder> {


    private List<SignInfo> signInfoList;
    private Context context;
    public SignAdapter(Context context, List<SignInfo> signInfoList){
        this.context = context;
        this.signInfoList = signInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_signin,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(root);
        viewHolder.tv_name = root.findViewById(R.id.tv_name);
        viewHolder.tv_equip = root.findViewById(R.id.tv_equip);
        viewHolder.tv_id = root.findViewById(R.id.tv_id);
        viewHolder.tv_status = root.findViewById(R.id.tv_status);
        viewHolder.tv_traffic = root.findViewById(R.id.tv_traffic);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
       viewHolder.tv_id.setText(signInfoList.get(i).getId()+"");
       viewHolder.tv_traffic.setText(signInfoList.get(i).getTraffic());
       viewHolder.tv_equip.setText(signInfoList.get(i).getEquip());
       viewHolder.tv_name.setText(signInfoList.get(i).getName());

        if (("已签到").equals(signInfoList.get(i).getStatus())){
            viewHolder.tv_status.setText(signInfoList.get(i).getStatus());
            viewHolder.tv_status.setTextColor(Color.BLACK);
        }else {
            viewHolder.tv_status.setTextColor(Color.RED);
            viewHolder.tv_status.setText(signInfoList.get(i).getStatus());
        }
    }



    @Override
    public int getItemCount() {
        return signInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView tv_id,tv_name,tv_traffic,tv_equip,tv_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}

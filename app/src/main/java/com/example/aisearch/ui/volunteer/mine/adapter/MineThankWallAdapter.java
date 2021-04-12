package com.example.aisearch.ui.volunteer.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.volunteer.ThankMsg;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class MineThankWallAdapter extends RecyclerView.Adapter<MineThankWallAdapter.myHoder> {

    private Context context;
    private List<ThankMsg> thankMsgs;

    public MineThankWallAdapter(Context context, List<ThankMsg> thankMsgs) {
        this.context = context;
        this.thankMsgs = thankMsgs;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_mine_thankwall,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {

        holder.head.setImageBitmap(thankMsgs.get(position).getHeadimg());
        holder.tv1.setText(thankMsgs.get(position).getDescribe());
        holder.tv2.setText(thankMsgs.get(position).getTime());
        holder.tv3.setText(thankMsgs.get(position).getName());

        if (thankMsgs.get(position).isNewMsg())
            holder.mine_thankwall_new.setVisibility(View.VISIBLE);


//        holder.head.setImageResource(R.mipmap.headimg2);
//        holder.tv1.setText("谢谢你们寻回我家小玲，给我的生命带来了阳光温暖，没有英雄们的付出，" +
//                "这将是我一生之痛，祝你们平安健康，万分感激!");
//        holder.tv2.setText("2021-01-30");
//        holder.tv3.setText("李卫国家人留");

//        if (position==0||position==1)
//            holder.mine_thankwall_new.setVisibility(View.VISIBLE);

    }



    @Override
    public int getItemCount() {
        return thankMsgs.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.thankwall_headimg)
        ImageView head;
        @BindView(R.id.mine_thankwall_new)
        ImageView mine_thankwall_new;
        @BindView(R.id.thankwall_tv1)
        TextView tv1;
        @BindView(R.id.thankwall_tv2)
        TextView tv2;
        @BindView(R.id.thankwall_tv3)
        TextView tv3;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

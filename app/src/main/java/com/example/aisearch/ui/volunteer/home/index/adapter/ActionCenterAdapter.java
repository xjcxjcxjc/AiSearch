package com.example.aisearch.ui.volunteer.home.index.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.ActionListItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class ActionCenterAdapter extends RecyclerView.Adapter<ActionCenterAdapter.myHoder> {

    private Context context;
    private List<ActionListItem> actionListItems;
    private Class aClass;

    public ActionCenterAdapter(Context context, Class aClass, List<ActionListItem> actionListItems) {
        this.context = context;
        this.actionListItems = actionListItems;
        this.aClass = aClass;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_index_actioncenter,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.actionname.setText("海曙老人救援行动");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,aClass);
                context.startActivity(intent);
            }
        });

        if (null!=actionListItems.get(position).getImg() )
            holder.headimg.setImageBitmap(actionListItems.get(position).getImg());
        if (null!=actionListItems.get(position).getName() )
            holder.actionname.setText(actionListItems.get(position).getName());
        if (null!=actionListItems.get(position).getLocation())
            holder.actionlocation.setText(actionListItems.get(position).getLocation());
        if (null!=actionListItems.get(position).getTime())
            holder.actiontime.setText(actionListItems.get(position).getTime());
        if (0!=actionListItems.get(position).getAddNub())
            holder.actiontime.setText(actionListItems.get(position).getTime());

        if(null!=actionListItems.get(position).getLable1())
            holder.actioncenter_lable1.setText(actionListItems.get(position).getLable1());
        else
            holder.actioncenter_lable1.setVisibility(View.GONE);

        if(null!=actionListItems.get(position).getLable2())
            holder.actioncenter_lable2.setText(actionListItems.get(position).getLable2());
        else
            holder.actioncenter_lable2.setVisibility(View.GONE);

        if(null!=actionListItems.get(position).getLable3())
            holder.actioncenter_lable3.setText(actionListItems.get(position).getLable3());
        else
            holder.actioncenter_lable3.setVisibility(View.GONE);

        int urgent=actionListItems.get(position).getUrgentgrade();

        if (urgent>=1&&urgent<=5){
            if (urgent>=1)
                holder.emerg1.setImageResource(R.mipmap.flash3);
            if (urgent>=2)
                holder.emerg2.setImageResource(R.mipmap.flash3);
            if (urgent>=3)
                holder.emerg3.setImageResource(R.mipmap.flash3);
            if (urgent>=4)
                holder.emerg4.setImageResource(R.mipmap.flash3);
            if (urgent>=5)
                holder.emerg5.setImageResource(R.mipmap.flash3);
        }

        if(0==actionListItems.get(position).getAddNub())
            holder.actionstatu.setText("等待中");

        holder.actionnub.setText(actionListItems.get(position).getAddNub()+"人在行动");

    }


    @Override
    public int getItemCount() {
        return actionListItems.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.actioncenter_headimg)
        ImageView headimg;
        @BindView(R.id.actioncenter_actionnub)
        TextView actionnub;
        @BindView(R.id.actioncenter_actionname)
        TextView actionname;
        @BindView(R.id.actioncenter_actionstatus)
        TextView actionstatu;
        @BindView(R.id.actioncenter_actiontime)
        TextView actiontime;
        @BindView(R.id.actioncenter_location)
        TextView actionlocation;

        @BindView(R.id.actioncenter_emerg1)
        ImageView emerg1;
        @BindView(R.id.actioncenter_emerg2)
        ImageView emerg2;
        @BindView(R.id.actioncenter_emerg3)
        ImageView emerg3;
        @BindView(R.id.actioncenter_emerg4)
        ImageView emerg4;
        @BindView(R.id.actioncenter_emerg5)
        ImageView emerg5;
        @BindView(R.id.actioncenter_lable1)
        TextView actioncenter_lable1;
        @BindView(R.id.actioncenter_lable2)
        TextView actioncenter_lable2;
        @BindView(R.id.actioncenter_lable3)
        TextView actioncenter_lable3;

        @BindView(R.id.actioncenter_itemview)
        LinearLayout view;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public interface ItemClick{

        void click();

    }
}




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
import com.example.aisearch.bean.family.ActionStatus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class ActionListAdapter extends RecyclerView.Adapter<ActionListAdapter.myHoder> {

    private Context context;

    private List<ActionStatus> statuses;

    public ActionListAdapter(Context context, List<ActionStatus> statuses) {
        this.context = context;
        this.statuses = statuses;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_mine2_actionlist,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {


        if (statuses.get(position).getType()==ActionStatus.LISTTYPE_EXAMINE) {
            holder.mine2_actionlist_type.setText("补充信息");
            holder.actioncenter_actionstatus.setVisibility(View.INVISIBLE);
            holder.emerg3.setImageResource(R.mipmap.flash3);
            holder.emerg4.setImageResource(R.mipmap.flash2);
            holder.emerg5.setImageResource(R.mipmap.flash2);
            holder.actioncenter_actionnub.setVisibility(View.INVISIBLE);
        }else if (statuses.get(position).getType()==ActionStatus.LISTTYPE_ACTION) {
            holder.mine2_actionlist_type.setText("撤回行动");
            holder.actioncenter_actionstatus.setVisibility(View.VISIBLE);
            holder.emerg3.setImageResource(R.mipmap.flash3);
            holder.emerg4.setImageResource(R.mipmap.flash3);
            holder.emerg5.setImageResource(R.mipmap.flash3);
            holder.actioncenter_actionnub.setVisibility(View.VISIBLE);
        }else if (statuses.get(position).getType()==ActionStatus.LISTTYPE_END) {
            holder.mine2_actionlist_type.setText("再次发起");
            holder.actioncenter_actionstatus.setVisibility(View.INVISIBLE);
            holder.emerg3.setImageResource(R.mipmap.flash2);
            holder.emerg4.setImageResource(R.mipmap.flash2);
            holder.emerg5.setImageResource(R.mipmap.flash2);
            holder.actioncenter_actionnub.setVisibility(View.INVISIBLE);
        }



    }


    @Override
    public int getItemCount() {
        return statuses.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.mine2_actionlist_type)
        TextView mine2_actionlist_type;
        @BindView(R.id.actioncenter_actionstatus)
        TextView actioncenter_actionstatus;
        @BindView(R.id.actioncenter_actionnub)
        TextView actioncenter_actionnub;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

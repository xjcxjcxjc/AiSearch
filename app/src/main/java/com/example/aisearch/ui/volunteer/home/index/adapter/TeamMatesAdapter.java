package com.example.aisearch.ui.volunteer.home.index.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.util.volunteer.TeamMates;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class TeamMatesAdapter extends RecyclerView.Adapter<TeamMatesAdapter.myHoder> {

    private Context context;
    private TeamMates teamMates;

    public TeamMatesAdapter(Context context, TeamMates teamMates) {
        this.context = context;
        this.teamMates = teamMates;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_actiondetail_teammates,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        if (position<teamMates.getNowNub()){
            holder.action_details_leadimg.setImageResource(R.mipmap.eyes);
            holder.name.setText(teamMates.getNames().get(position));
        }else{
            holder.name.setText("待加入");
            holder.action_details_leadimg.setImageResource(R.mipmap.teammate_noperson);
        }
    }



    @Override
    public int getItemCount() {
        return teamMates.getMaxNub();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.action_details_leadimg)
        ImageView action_details_leadimg;
        @BindView(R.id.action_details_name)
        TextView name;


        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public void setData(TeamMates data){
        teamMates =data;

    }


}




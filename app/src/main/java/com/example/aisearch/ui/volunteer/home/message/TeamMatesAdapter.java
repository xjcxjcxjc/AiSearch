package com.example.aisearch.ui.volunteer.home.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.Message.TeamMate;
import com.example.aisearch.util.UiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class TeamMatesAdapter extends RecyclerView.Adapter<TeamMatesAdapter.myHoder> {

    private Context context;

    private List<TeamMate> teamMates;

    public TeamMatesAdapter(Context context, List<TeamMate> teamMates) {
        this.context = context;
        this.teamMates = teamMates;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_teammates,parent,false);
        return new myHoder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {


        if (TeamMate.POSITION.CAPTAIN==teamMates.get(holder.getLayoutPosition()).getPosition())
            holder.teammate_captain.setVisibility(View.VISIBLE);

        holder.teammate_headimg.setImageBitmap(teamMates.get(holder.getLayoutPosition()).getHead());
        holder.teammate_name.setText(teamMates.get(holder.getLayoutPosition()).getName());
        holder.teammate_callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.callPhone(context,teamMates.get(holder.getLayoutPosition()).getPhone());
            }
        });



    }

    @Override
    public int getItemCount() {
        return teamMates.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.teammate_headimg)
        ImageView teammate_headimg;
        @BindView(R.id.teammate_captain)
        CardView teammate_captain;
        @BindView(R.id.teammate_callbtn)
        Button teammate_callbtn;
        @BindView(R.id.teammate_name)
        TextView teammate_name;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

package com.example.aisearch.ui.volunteer.action.inaction.SosAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.util.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.myHoder> {

    
    private Context context;

    public TeamAdapter(Context context ) {
        this.context = context;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_sos_team,parent,false);
        return new myHoder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {

        if (0!=position)
            holder.item_team_title.setText("第二小队");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.callPhone(context,"13245234643");
            }
        });

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_team_title)
        TextView item_team_title;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

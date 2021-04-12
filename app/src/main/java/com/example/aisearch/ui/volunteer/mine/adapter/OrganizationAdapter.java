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
import com.example.aisearch.bean.volunteer.Badge;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  组织和徽章界面的adapter
 */
public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.myHoder> {

    private Context context;
    private List<Badge> badges;


    public OrganizationAdapter(Context context,List<Badge> badges) {
        this.context = context;
        this.badges = badges;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_mine_organization,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.organization_name.setText(badges.get(position).getTitle());
        holder.organization_itemcard.setImageBitmap(badges.get(position).getBitmap());

    }

    @Override
    public int getItemCount() {
        return badges.size();
    }

    class myHoder extends RecyclerView.ViewHolder {


        @BindView(R.id.organization_itemcard)
        ImageView organization_itemcard;
        @BindView(R.id.organization_name)
        TextView organization_name;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}

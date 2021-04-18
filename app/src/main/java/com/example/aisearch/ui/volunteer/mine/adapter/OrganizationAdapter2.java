package com.example.aisearch.ui.volunteer.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.util.volunteer.Badge;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  组织和徽章界面的adapter
 */
public class OrganizationAdapter2 extends RecyclerView.Adapter<OrganizationAdapter2.myHoder> {

    private Context context;
    private List<Badge> badges;


    public OrganizationAdapter2(Context context, List<Badge> badges) {
        this.context = context;
        this.badges = badges;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_organization,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
//        holder.organization_itemcard.setImageBitmap(badges.get(position).getBitmap());
        holder.t1.setText(badges.get(position).getTitle());
        holder.organization_name.setText(badges.get(position).getTitle2());

    }

    @Override
    public int getItemCount() {
        return badges.size();
    }

    class myHoder extends RecyclerView.ViewHolder {


//        @BindView(R.id.organization_itemcard)
//        ImageView organization_itemcard;
        @BindView(R.id.organization_name)
        TextView organization_name;
        @BindView(R.id.t1)
        TextView t1;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}

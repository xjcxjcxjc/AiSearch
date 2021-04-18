package com.example.aisearch.ui.volunteer.community.adpter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.util.volunteer.CommunityItems;
import com.example.aisearch.ui.volunteer.community.DetailsActivity;

import java.util.List;


/*
 * @Author : XJC
 * @Time : 2021/2/13 15:25
 * @Description : 发现界面的adapter
 *
 */
public class CommunityFragmentAdapter extends RecyclerView.Adapter<CommunityFragmentAdapter.myHoder> {

    private Context context;
    private List<CommunityItems> communityItems;

    public CommunityFragmentAdapter(Context context, List<CommunityItems> communityItems) {
        this.context = context;
        this.communityItems = communityItems;


    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_recycle_cmnt_find,parent,false);
        return new myHoder(view);
    }


    @Override
    public int getItemCount() {
        if (null==communityItems)
            return 0;
        return communityItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.Pct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
            }
        });

        Bitmap coverImage=communityItems.get(position).getCoverimg();
        Bitmap headImage=communityItems.get(position).getHeadimg();
        String title=communityItems.get(position).getTitle();
        String name=communityItems.get(position).getName();
        String likeNub=communityItems.get(position).getLikeNub();


        holder.headView.setImageBitmap(headImage);
        holder.Pct.setImageBitmap(coverImage);
        holder.title.setText(title);
        holder.name.setText(name);
        holder.nub.setText(likeNub);

    }


    class myHoder extends RecyclerView.ViewHolder {

        ImageView Pct;
        ImageView headView;
        ImageView heart;
        TextView title;
        TextView nub;
        TextView name;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            Pct=itemView.findViewById(R.id.community_item_img);
            headView=itemView.findViewById(R.id.community_item_headimg);
            heart=itemView.findViewById(R.id.community_item_heart);
            title=itemView.findViewById(R.id.community_item_title);
            nub=itemView.findViewById(R.id.community_item_nub);
            name=itemView.findViewById(R.id.community_item_name);
        }
    }


}


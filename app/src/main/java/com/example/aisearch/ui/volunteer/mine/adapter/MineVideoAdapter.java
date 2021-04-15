package com.example.aisearch.ui.volunteer.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.volunteer.Video;
import com.example.aisearch.ui.volunteer.train.StudyDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class MineVideoAdapter extends RecyclerView.Adapter<MineVideoAdapter.myHoder> {

    private Context context;
    private List<Video> videos;

    public MineVideoAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_mine_indexvideo,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        if (position==0)
            holder.depart.setVisibility(View.INVISIBLE);

        holder.videoitem_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, StudyDetailsActivity.class);
                intent.putExtra("video",videos.get(position));
                context.startActivity(intent);
            }
        });

        holder.videoitem_name.setText(videos.get(position).getTitle());
        holder.videoitem_last.setText(videos.get(position).getLastTime());
        holder.videoitem_lasttitle.setText(videos.get(position).getLasttitle());
        holder.videoitem_progress.setText("学习至"+videos.get(position).getStudyprogress()+"%");



    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.videoitem_depart1)
        TextView depart;
        @BindView(R.id.videoitem_view)
        LinearLayout videoitem_view;

        @BindView(R.id.videoitem_last)
        TextView videoitem_last;

        @BindView(R.id.videoitem_lasttitle)
        TextView videoitem_lasttitle;

        @BindView(R.id.videoitem_name)
        TextView videoitem_name;

        @BindView(R.id.videoitem_progress)
        TextView videoitem_progress;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

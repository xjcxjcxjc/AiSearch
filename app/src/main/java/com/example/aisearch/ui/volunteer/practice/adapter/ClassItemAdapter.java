package com.example.aisearch.ui.volunteer.practice.adapter;

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
import com.example.aisearch.ui.volunteer.practice.StudyDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class ClassItemAdapter extends RecyclerView.Adapter<ClassItemAdapter.myHoder> {

    private Context context;
    private List<String> names;
    private String videotitle;
    private Video video;

    public ClassItemAdapter(Context context, List<String> names,String videotitle,Video video) {
        this.context = context;
        this.names = names;
        this.videotitle = videotitle;
        this.video=video;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_class,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.classNub.setText("第"+Integer.toString(position+1)+"节");
        holder.className.setText(names.get(position));
        holder.videoName.setText(videotitle);

        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, StudyDetailsActivity.class);
                video.setNowcourse7(holder.className.getText().toString());
                intent.putExtra("video",video);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return names.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.classitem_classnub)
        TextView classNub;
        @BindView(R.id.classitem_classname)
        TextView className;
        @BindView(R.id.classitem_videoname)
        TextView videoName;
        @BindView(R.id.itemview)
        LinearLayout itemview;



        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

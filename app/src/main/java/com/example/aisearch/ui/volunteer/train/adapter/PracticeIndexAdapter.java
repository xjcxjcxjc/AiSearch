package com.example.aisearch.ui.volunteer.train.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.volunteer.Video;
import com.example.aisearch.ui.volunteer.train.StudyDetailsActivity;
import com.example.aisearch.ui.volunteer.train.dialog.LockDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class PracticeIndexAdapter extends RecyclerView.Adapter<PracticeIndexAdapter.myHoder> {

    private Context context;
    public static final String FRESH="FRESH";
    public static final String SKILL="SKILL";
    private int type;
    private List<Video> videos;
    LockDialog lockDialog;
    private List<Bitmap> backgroudBitmaps=null;

    
    public PracticeIndexAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    public PracticeIndexAdapter(Context context, List<Video> videos, List<Bitmap> backgroudBitmaps) {
        this.context = context;
        this.videos = videos;
        this.backgroudBitmaps = backgroudBitmaps;
    }


    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_freshbird_index,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        if (null!=backgroudBitmaps){
            Bitmap bitmap= backgroudBitmaps.get(position%backgroudBitmaps.size());
            holder.typeimg.setImageBitmap(bitmap);
        }else{
            if (FRESH.equals(videos.get(position).getCategory()))
                holder.typeimg.setImageResource(R.mipmap.backgrd2);
            else if (SKILL.equals(videos.get(position).getCategory()))
                holder.typeimg.setImageResource(R.mipmap.backgrd1);
        }


        holder.title1.setText(videos.get(position).getTitle());
        holder.title2.setText(videos.get(position).getTitle());
        holder.title3.setText(videos.get(position).getTitle());
        holder.studynub.setText(Integer.toString(videos.get(position).getStudynub())+"人已观看");
        holder.describe.setText(videos.get(position).getDescribe());

        if (videos.get(position).getHard()>=1)
            holder.star1.setImageResource(R.mipmap.prc_star2);

        if (videos.get(position).getHard()>=2)
            holder.star2.setImageResource(R.mipmap.prc_star2);

        if (videos.get(position).getHard()>=3)
            holder.star3.setImageResource(R.mipmap.prc_star2);

        if (videos.get(position).getHard()>=4)
            holder.star4.setImageResource(R.mipmap.prc_star2);

        if (videos.get(position).getHard()>=5)
            holder.star5.setImageResource(R.mipmap.prc_star2);

        /**
         * 为不可阅读视频增加阴影和锁
         */
        if(videos.get(position).getRequiregrade()>=2){
            holder.shadow.setVisibility(View.VISIBLE);
            holder.lock.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lockDialog=new LockDialog(context, new LockDialog.ClickCallBack() {
                        @Override
                        public void onStop() {
                            lockDialog.dismiss();
                        }
                    },
                            "您需要达到Lv2才能观看视频");
                    lockDialog.show();

                }
            });

        }else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, StudyDetailsActivity.class);
                    intent.putExtra("video",videos.get(position));
                    context.startActivity(intent);
                }
            });

        }



    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.freshbird_item_title)
        TextView title1;
        //图标上的title
        @BindView(R.id.freshbird_item_title3)
        TextView title3;
        //内容与title一致
        @BindView(R.id.freshbird_item_title2)
        TextView title2;

        @BindView(R.id.freshbird_item_typeimg)
        ImageView typeimg;

        @BindView(R.id.freshbird_item_progress)
        TextView progress;
        @BindView(R.id.freshbird_item_describe)
        TextView describe;
        @BindView(R.id.freshbird_item_studynub)
        TextView studynub;
        @BindView(R.id.freshbird_item_black)
        TextView shadow;


        @BindView(R.id.freshbird_item_star1)
        ImageView star1;
        @BindView(R.id.freshbird_item_star2)
        ImageView star2;
        @BindView(R.id.freshbird_item_star3)
        ImageView star3;
        @BindView(R.id.freshbird_item_star4)
        ImageView star4;
        @BindView(R.id.freshbird_item_star5)
        ImageView star5;
        @BindView(R.id.freshbird_item_lock)
        ImageView lock;

        @BindView(R.id.freshbird_item_view)
        RelativeLayout item;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

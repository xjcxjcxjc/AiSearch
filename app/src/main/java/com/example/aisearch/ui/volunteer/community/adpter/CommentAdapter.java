package com.example.aisearch.ui.volunteer.community.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.util.volunteer.Comment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  组织和徽章界面的adapter
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.myHoder> {

    private Context context;
    private List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_dialog_comment,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.comment_img.setImageBitmap(comments.get(position).getHead());
        holder.comment_name.setText(comments.get(position).getName());
        holder.comment_time.setText(comments.get(position).getTime());
        holder.comment_likenub.setText(comments.get(position).getLikenub()+"");
        holder.comment_comment.setText(comments.get(position).getComment());


    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.comment_img)
        ImageView comment_img;
        @BindView(R.id.comment_time)
        TextView comment_time;
        @BindView(R.id.comment_name)
        TextView comment_name;
        @BindView(R.id.comment_likenub)
        TextView comment_likenub;
        @BindView(R.id.comment_comment)
        TextView comment_comment;


        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}

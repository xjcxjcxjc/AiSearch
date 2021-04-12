package com.example.aisearch.ui.message;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.Message.MessageListItem;
import com.example.aisearch.ui.volunteer.action.zwactivity.chat.ChatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.myHoder> {

    private Context context;

    private List<MessageListItem> messageListItems;

    public MessageAdapter(Context context, List<MessageListItem> messageListItems) {
        this.context = context;
        this.messageListItems = messageListItems;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_msglist,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {

        holder.msg_head.setImageBitmap(messageListItems.get(position).getHeadimg());
        holder.msg_item_title.setText(messageListItems.get(position).getTitle());
        holder.msg_item_describe.setText(messageListItems.get(position).getDescribe());
        holder.msg_item_time.setText(messageListItems.get(position).getTime());
        holder.msg_item_newnub.setText(messageListItems.get(position).getNewbun()+"");
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ChatActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageListItems.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.msg_head)
        ImageView msg_head;
        @BindView(R.id.msg_item_title)
        TextView msg_item_title;
        @BindView(R.id.msg_item_describe)
        TextView msg_item_describe;
        @BindView(R.id.msg_item_time)
        TextView msg_item_time;
        @BindView(R.id.msg_item_newnub)
        TextView msg_item_newnub;
        @BindView(R.id.itemview)
        RelativeLayout itemview;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

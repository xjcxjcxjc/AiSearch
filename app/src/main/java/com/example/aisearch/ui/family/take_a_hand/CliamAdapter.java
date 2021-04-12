package com.example.aisearch.ui.family.take_a_hand;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.Person;
import com.example.aisearch.util.datautil.DataUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class CliamAdapter extends RecyclerView.Adapter<CliamAdapter.myHoder> {

    private Context context;
    private List<Person> noticePeople;
    private int color;

    /**
     *
     * @param context
     * @param noticePeople
     * @param color 传0表示红色主题，1表示蓝色主题
     */
    public CliamAdapter(Context context, List<Person> noticePeople, int color) {
        this.context = context;
        this.noticePeople = noticePeople;
        this.color = color;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_index_claimnotice,parent,false);
        return new myHoder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataUtil.startActivityWithPerson(context,ClaimPersonDetailsActivity.class,noticePeople.get(position),
                        noticePeople.get(position).getImg());
            }
        });


        Bitmap img= noticePeople.get(position).getImg();
        String name= noticePeople.get(position).getNub();
        String sex= noticePeople.get(position).getSex();
        String age= noticePeople.get(position).getAge();
        String time= noticePeople.get(position).getTime();
        String location= noticePeople.get(position).getLocation();
        String searchnotice_home= noticePeople.get(position).getFrom();
        String features1= noticePeople.get(position).getFeatures1();
        String features2= noticePeople.get(position).getFeatures2();
        String features3= noticePeople.get(position).getFeatures3();


        if (null!=img)
            holder.headimg.setImageBitmap(img);
        if (null!=name)
            holder.name.setText(name);
        if (null!=time)
            holder.losttime.setText(time);
        if (null!=location)
            holder.location.setText(location);

        if (null!=features1)
            holder.searchnotice_lable1.setText(features1);
        if (null!=features2)
            holder.searchnotice_lable2.setText(features2);
        if (null!=features3)
            holder.searchnotice_lable3.setText(features3);
        holder.searchnotice_name2.setText(noticePeople.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return noticePeople.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.searchnotice_itemview)
        LinearLayout itemview;
        @BindView(R.id.searchnotice_headimg)
        ImageView headimg;
        @BindView(R.id.searchnotice_name)
        TextView name;
        @BindView(R.id.searchnotice_name2)
        TextView searchnotice_name2;
        @BindView(R.id.searchnotice_losttime)
        TextView losttime;
        @BindView(R.id.searchnotice_location)
        TextView location;
        @BindView(R.id.searchnotice_lable1)
        TextView searchnotice_lable1;
        @BindView(R.id.searchnotice_lable2)
        TextView searchnotice_lable2;
        @BindView(R.id.searchnotice_lable3)
        TextView searchnotice_lable3;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}

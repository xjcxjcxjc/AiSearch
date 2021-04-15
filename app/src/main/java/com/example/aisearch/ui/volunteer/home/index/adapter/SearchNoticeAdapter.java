package com.example.aisearch.ui.volunteer.home.index.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.aisearch.ui.volunteer.home.LostPersonDetailsActivity;
import com.example.aisearch.ui.volunteer.home.publish.ScreenActivity;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class SearchNoticeAdapter extends RecyclerView.Adapter<SearchNoticeAdapter.myHoder> {

    private Context context;
    private List<Person> people;
    private int color;

    public SearchNoticeAdapter(Context context, List<Person> people, int color) {
        this.context = context;
        this.people = people;
        this.color = color;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_index_searchnotice,parent,false);
        return new myHoder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LostPersonDetailsActivity.class);
                context.startActivity(intent);
            }
        });


        holder.addclue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ScreenActivity.class);
                context.startActivity(intent);
            }
        });

        if (0==color)
            holder.addclue.setBackgroundColor(context.getResources().getColor(R.color.title_red));
        else if (1==color)
            holder.addclue.setBackgroundColor(context.getResources().getColor(R.color.title_blue));


        Bitmap img= people.get(position).getImg();
        String name= people.get(position).getName();
        String sex= people.get(position).getSex();
        String age= people.get(position).getAge();
        String time= people.get(position).getTime();
        String location= people.get(position).getLocation();
        String searchnotice_home= people.get(position).getFrom();
        String features1= people.get(position).getFeatures1();
        String features2= people.get(position).getFeatures2();
        String features3= people.get(position).getFeatures3();


        if (null!=img)
            holder.headimg.setImageBitmap(img);
        if (null!=name)
            holder.name.setText(name);
        if (null!=sex)
            holder.sex.setText(sex);
        if (null!=age)
            holder.age.setText(age);
        if (null!=time)
            holder.losttime.setText(time);
        if (null!=location)
            holder.location.setText(location);
        if (null!=searchnotice_home)
            holder.searchnotice_home.setText(searchnotice_home);

        if (null!=features1)
            holder.searchnotice_lable1.setText(features1);
        if (null!=features2)
            holder.searchnotice_lable2.setText(features2);
        if (null!=features3)
            holder.searchnotice_lable3.setText(features3);


    }


    @Override
    public int getItemCount() {
        return people.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.searchnotice_itemview)
        LinearLayout itemview;
        @BindView(R.id.searchnotice_headimg)
        ImageView headimg;
        @BindView(R.id.searchnotice_name)
        TextView name;
        @BindView(R.id.searchnotice_sex)
        TextView sex;
        @BindView(R.id.searchnotice_age)
        TextView age;
        @BindView(R.id.searchnotice_losttime)
        TextView losttime;
        @BindView(R.id.searchnotice_location)
        TextView location;
        @BindView(R.id.searchnotice_addclue)
        QMUIRoundButton addclue;
        @BindView(R.id.searchnotice_home)
        TextView searchnotice_home;
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

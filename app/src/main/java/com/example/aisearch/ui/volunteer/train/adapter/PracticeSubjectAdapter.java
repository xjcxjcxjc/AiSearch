package com.example.aisearch.ui.volunteer.train.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.util.volunteer.Subject_Single;
import com.example.aisearch.ui.volunteer.train.dialog.PassDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻人启事的adapter
 */
public class PracticeSubjectAdapter extends RecyclerView.Adapter<PracticeSubjectAdapter.myHoder> {

    private Context context;

    /**
     * 用来记录得分
     */
    private int score;
    private List<Subject_Single> singles;

    public PracticeSubjectAdapter(Context context , List<Subject_Single> singles) {
        this.context = context;
        this.singles = singles;
    }


    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_practice_subject,parent,false);
//        View view= LayoutInflater.from(context).inflate(R.layout.item_practice_subject2,parent,false);
        return new myHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.practice_subject_nub.setText(Integer.toString(position+1));

        holder.practice_subject_title.setText(singles.get(position).getTitle());
        holder.practice_subject_A.setText("A."+singles.get(position).getChoose_A());
        holder.practice_subject_B.setText("B."+singles.get(position).getChoose_B());
        holder.practice_subject_C.setText("C."+singles.get(position).getChoose_C());
        holder.practice_subject_D.setText("D."+singles.get(position).getChoose_D());

    }

    @Override
    public int getItemCount() {
        return singles.size();
    }

    class myHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.practice_subject_nub)
        TextView practice_subject_nub;
        @BindView(R.id.practice_subject_rb1)
        RadioButton practice_subject_rb1;
        @BindView(R.id.practice_subject_rb2)
        RadioButton practice_subject_rb2;
        @BindView(R.id.practice_subject_rb3)
        RadioButton practice_subject_rb3;
        @BindView(R.id.practice_subject_rb4)
        RadioButton practice_subject_rb4;


        @BindView(R.id.practice_subject_title)
        TextView practice_subject_title;
        @BindView(R.id.practice_subject_A)
        TextView practice_subject_A;
        @BindView(R.id.practice_subject_B)
        TextView practice_subject_B;
        @BindView(R.id.practice_subject_C)
        TextView practice_subject_C;
        @BindView(R.id.practice_subject_D)
        TextView practice_subject_D;


        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void submit(){
        pass();

    }

    public void pass(){

        new PassDialog(context, new PassDialog.ClickCallBack() {
            @Override
            public void onStop() {
                Activity activity= (Activity) context;
                activity.finish();
            }
        }).show();

    }
}

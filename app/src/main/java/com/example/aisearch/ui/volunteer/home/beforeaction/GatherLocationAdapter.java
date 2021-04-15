package com.example.aisearch.ui.volunteer.home.beforeaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Author WangJie
 * Created on 2019/1/21.
 */
public class GatherLocationAdapter extends RecyclerView.Adapter<GatherLocationAdapter.ViewHolder> {


    private List<GatherLocation> locations;
    private Context context;
    private DialogGather.CallBack callBack;

//    public GatherLocationAdapter (Context context,List<GatherLocation> locations){
//        this.context = context;
//        this.locations = locations;
//    }

    public GatherLocationAdapter (Context context,List<GatherLocation> locations,DialogGather.CallBack callBack){
        this.context = context;
        this.locations = locations;
        this.callBack=callBack;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gatherlocation,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(root);
        viewHolder.tv_name = root.findViewById(R.id.tv_name);
        viewHolder.tv_address = root.findViewById(R.id.tv_address);
        viewHolder.tv_ohter = root.findViewById(R.id.tv_other);
        viewHolder.btn_send = root.findViewById(R.id.btn_send);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv_name.setText(locations.get(i).getName());
        viewHolder.tv_address.setText(locations.get(i).getMiles()+"KM|"+locations.get(i).getAddress());
        if (locations.get(i).getOther()!=null){
            viewHolder.tv_ohter.setText(locations.get(i).getOther());
        }else {
            viewHolder.tv_ohter.setVisibility(View.INVISIBLE);
        }


        viewHolder.btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StaticInfo.gatherLocation==null){
                    StaticInfo.gatherLocation = new GatherLocation();
                }
                StaticInfo.gatherLocation.setName(locations.get(i).getName());
                StaticInfo.gatherLocation.setLatLng(locations.get(i).getLatLng());
                Toasty.success(context,"发送成功").show();
                /**
                 * 增加点击回调
                 */
                callBack.callback();
            }
        });


    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView tv_name,tv_address,tv_ohter;
        Button btn_send;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}

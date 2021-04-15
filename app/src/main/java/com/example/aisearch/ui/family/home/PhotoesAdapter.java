package com.example.aisearch.ui.family.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aisearch.R;
import com.example.aisearch.bean.Person;
import com.example.aisearch.util.UiUtils;
import com.example.aisearch.util.datautil.DataUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoesAdapter extends RecyclerView.Adapter<PhotoesAdapter.myHoder> {


    private Context context;
    private List<Bitmap> bitmaps;
    private List<String> similar;


    public PhotoesAdapter(Context context, List<Bitmap> bitmaps, List<String> similar) {
        this.context = context;
        this.bitmaps = bitmaps;
        this.similar = similar;
    }

    @NonNull
    @Override
    public myHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_help_photoes,parent,false);
        return new myHoder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull myHoder holder, int position) {
        holder.item_photoes.setImageBitmap(bitmaps.get(position));
        holder.nub.setText(similar.get(position)+"%");
        holder.item_photoes_card.setOnClickListener(v -> {
            Person ps= new Person(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img9), "不详","", "", "跛脚  "
                    , "跛脚", "","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年2月16日", "宁波市江北区下江路附近"
                    , "2000.6.6", "浙江宁波", "175", "老人走失", "如有认识他的好心人，请联系我们！","1000110");

            DataUtil.startActivityWithPerson(context,ClaimPersonDetailsActivity.class,ps,
                    ps.getImg());
//            context.startActivity(new Intent(context,ClaimPersonDetailsActivity.class));
        });
    }


    @Override
    public int getItemCount() {
        return bitmaps.size();
    }

    class myHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_photoes)
        ImageView item_photoes;

        @BindView(R.id.nub)
        TextView nub;

        @BindView(R.id.item_photoes_card)
        CardView item_photoes_card;

        public myHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setBitmaps(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    public void setSimilar(List<String> similar) {
        this.similar = similar;
    }
}

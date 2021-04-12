package com.example.aisearch.ui.volunteer.action.beforeaction;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.model.LatLng;
import com.example.aisearch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 增加一个回调，在地图上显示定位点
 */
public class DialogGather {
    private Dialog dialog;
    private List<GatherLocation> locations ;
    private View view;
    private Context context;
    CallBack callBack;

//    public DialogGather(Context context){
//        this.context =  context;
//        dialog = new Dialog(context, R.style.DialogTheme);
//         view = View.inflate(context, R.layout.dialog_gather, null);
//        dialog.setContentView(view);
//
//        init();
//        Window window = dialog.getWindow();
//
//        window.setGravity(Gravity.BOTTOM);
//
//        window.setWindowAnimations(R.style.main_menu_animStyle);
//
//        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 650);
//    }
    public DialogGather(Context context,CallBack callBack){
        this.context =  context;
        this.callBack=callBack;
        dialog = new Dialog(context, R.style.DialogTheme);
        view = View.inflate(context, R.layout.dialog_gather, null);
        dialog.setContentView(view);

        init();
        Window window = dialog.getWindow();

        window.setGravity(Gravity.BOTTOM);

        window.setWindowAnimations(R.style.main_menu_animStyle);

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 650);

    }



    public void showDialog(){
        dialog.show();
    }
    private void init(){
        RecyclerView rv =view.findViewById(R.id.rv_gatherLocation);
        locations = new ArrayList<>();
        locations.add(new GatherLocation("中山宾馆",4.1,"宁波市海曙区中山西路88号","最佳集合点",new LatLng(29.883753,121.552863)));
        locations.add(new GatherLocation("浙海商业广场",2.2,"宁波市海曙区学院路21号","距您最近",new LatLng(29.875921,121.489303)));
        locations.add(new GatherLocation("学院路688弄",4.1,"宁波市海曙区学院路688号",null,new LatLng(29.894028,121.490987)));
        GatherLocationAdapter adapter = new GatherLocationAdapter(context,locations,callBack);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(adapter);



    }

    interface CallBack{
        void callback();
    }

}

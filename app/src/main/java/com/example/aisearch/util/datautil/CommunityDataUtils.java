package com.example.aisearch.util.datautil;

import android.content.Context;

import com.example.aisearch.R;
import com.example.aisearch.bean.volunteer.CommunityItems;
import com.example.aisearch.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author : XJC
 * @Time : 2021/4/1 18:36
 * @Description : 用于得到Community数据，由于数据量大，专门写一个类来获取数据
 *
 */
public class CommunityDataUtils {

    public static List<CommunityItems> getCommunityData(Context context){

        return null;
    }
    public static List<CommunityItems> getCommunityData1(Context context){
        List<CommunityItems> communityItems=new ArrayList<>();

        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg6),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img1),
                1, "六哥",
                "成功找回老人，谢谢大家!!!", "495","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg1),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img3),
                1, "洒一地阳光ヽ",
                "撸铁啦", "21","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg2),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img2),
                1, "于初秋",
                "落日", "127","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg3),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img4),
                1, "暮年",
                "老年人冬泳", "1204","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg4),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img5),
                1, "晚雾",
                "干饭人干饭魂", "251","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg5),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img6),
                1, "张卫国",
                "两个小可爱", "961","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg6),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img7),
                1, "三花",
                "艺术来源于生活", "127","26" ,"68" ));

        return communityItems;
    }

    public static List<CommunityItems> getCommunityData2(Context context){
        List<CommunityItems> communityItems=new ArrayList<>();

        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg6),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img10),
                1, "六哥",
                "消防战士纪念日", "495","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg1),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img11),
                1, "洒一地阳光ヽ",
                "看看新宝贝", "21","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg2),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img12),
                1, "于初秋",
                "夜", "127","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg3),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img13),
                1, "暮年",
                "老年人冬泳", "1204","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg4),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img14),
                1, "晚雾",
                "干饭人干饭魂", "251","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg5),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img15),
                1, "张卫国",
                "两个小可爱", "961","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg6),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img16),
                1, "三花",
                "艺术来源于生活", "127","26" ,"68" ));

        return communityItems;
    }

    public static List<CommunityItems> getCommunityData3(Context context){
        List<CommunityItems> communityItems=new ArrayList<>();

        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg6),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img17),
                1, "六哥",
                "彩虹", "495","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg1),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img18),
                1, "阳光",
                "这就是春天", "21","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg2),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img19),
                1, "于初秋",
                "志愿者活动哦", "127","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg3),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img20),
                1, "暮年",
                "花开", "1204","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg4),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img21),
                1, "晚雾",
                "花开", "251","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg5),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img22),
                1, "张卫国",
                "两个小可爱", "961","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg6),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img23),
                1, "三花",
                "艺术来源于生活", "127","26" ,"68" ));

        return communityItems;
    }

    public static List<CommunityItems> getCommunityData4(Context context){
        List<CommunityItems> communityItems=new ArrayList<>();
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg6),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img24),
                1, "六哥",
                "黄昏", "495","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg1),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img25),
                1, "洒一地阳光ヽ",
                "猫猫", "21","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg2),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img26),
                1, "于初秋",
                "撒一地烂漫", "127","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg3),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img27),
                1, "暮年",
                "盛夏", "1204","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg4),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img28),
                1, "晚雾",
                "干饭人干饭魂", "251","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg5),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img29),
                1, "张卫国",
                "两个小可爱", "961","26" ,"68" ));
        communityItems.add(new CommunityItems( UiUtils.resourceToBitmap(context, R.mipmap.orgnization_headimg6),
                UiUtils.bitmapFitWidth(context, UiUtils.getDeviceWidth(context)/2, R.mipmap.cover_img30),
                1, "三花",
                "艺术来源于生活", "127","26" ,"68" ));

        return communityItems;
    }


}

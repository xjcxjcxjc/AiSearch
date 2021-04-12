package com.example.aisearch.util.datautil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.aisearch.R;
import com.example.aisearch.bean.ActionListItem;
import com.example.aisearch.bean.Message.MessageListItem;
import com.example.aisearch.bean.Person;
import com.example.aisearch.bean.volunteer.CommunityItems;
import com.example.aisearch.ui.family.take_a_hand.ClaimPersonDetailsActivity;
import com.example.aisearch.util.UiUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author : XJC
 * @Time : 2021/3/28 14:47
 * @Description :
 *
 */
public class DataUtil {



    public static List<ActionListItem> initActionList(Context context){
        List<ActionListItem> actionListItems=new ArrayList<>();
        actionListItems.add(getSearchPerson(context));
        actionListItems.add(new ActionListItem(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img3), "镇海老人救援行动",
                "宁波市镇海区慈海南路路附近", "2021年3月9日9:00——3月11日9:00", "城市寻人", "志愿保险", "低风险", 3, 15));
        actionListItems.add(new ActionListItem(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img4), "鄞州老人救援行动",
                "宁波市鄞州区惠风东路路附近", "2021年3月6日12:00——3月7日12:00", "城市寻人", "志愿保险", "低风险", 2, 11));
        actionListItems.add(new ActionListItem(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img5), "江北老人救援行动",
                "宁波市江北区下江路附近", "22021年3月6日12:00——3月7日12:00", "城市寻人", "志愿保险", "低风险", 2, 9));
        actionListItems.add(new ActionListItem(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img6), "海曙老人救援行动",
                "宁波市海曙区学院路附近", "2021年3月6日12:00——3月7日12:00", "城市寻人", "志愿保险", "低风险", 2, 5));
        return actionListItems;
    }

    public static List<Person> initSearchNoticeData(Context context){
        List<Person> noticePeople =new ArrayList<>();
        noticePeople.add(getNoticePerson(context));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img3), "刘芳琴","女", "65", "跛脚"
                , "跛脚", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年3月10日", "宁波市镇海区慈海南路路附近"
                , "2000.6.6", "浙江舟山", "175", "老人走失", ""));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img4), "李海德","男", "61", "秃头  "
                , "精神疾病", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年2月22日", "宁波市鄞州区惠风东路路附近"
                , "2000.6.6", "浙江杭州", "175", "老人走失", ""));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img5), "文县张","男", "64", "秃头  "
                , "跛脚", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年2月16日", "宁波市江北区下江路附近"
                , "2000.6.6", "浙江宁波", "175", "老人走失", ""));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img6), "王兴芬","女", "45", "手上烫伤  "
                , "跛脚", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年2月10日", "宁波市海曙区学院路附近"
                , "2000.6.6", "浙江宁波", "175", "老人走失", ""));


        return noticePeople;
    }


    public static List<Person> initClaimNoticeData(Context context){
        List<Person> noticePeople =new ArrayList<>();


        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.claimperson21), "王顺财","", "",
                "跛脚  阿兹海默症"
                , "秃头", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "",
                "2021年3月10日", "宁波市镇海区慈海南路路附近"
                , "2000.6.6", "浙江舟山", "175", "老人走失", "如有认识他的好心人，请联系我们！","1000108"));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.claimperson22), "不详","", "", "精神疾病  阿兹海默症"
                , "精神疾病", "秃头","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年2月22日", "宁波市鄞州区惠风东路路附近"
                , "2000.6.6", "浙江杭州", "175", "老人走失", "如有认识他的好心人，请联系我们！","1000109"));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.claimperson23), "不详","", "", "跛脚  阿兹海默症"
                , "跛脚", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年2月16日", "宁波市江北区下江路附近"
                , "2000.6.6", "浙江宁波", "175", "老人走失", "如有认识他的好心人，请联系我们！","1000110"));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.claimperson24), "周国军","", "", "跛脚  阿兹海默症"
                , "秃头", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年2月10日", "宁波市海曙区学院路附近"
                , "2000.6.6", "浙江宁波", "175", "老人走失", "如有认识他的好心人，请联系我们！","1000111"));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.claimperson25), "不详","", "", "跛脚  阿兹海默症"
                , "跛脚", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年3月10日", "宁波市镇海区慈海南路路附近"
                , "2000.6.6", "浙江舟山", "175", "老人走失", "如有认识他的好心人，请联系我们！","1000112"));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.claimperson26), "不详","", "", "精神疾病  阿兹海默症"
                , "精神疾病", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年2月22日", "宁波市鄞州区惠风东路路附近"
                , "2000.6.6", "浙江杭州", "175", "老人走失", "如有认识他的好心人，请联系我们！","1000113"));

        noticePeople.add(new Person(UiUtils.resourceToBitmap(context, R.mipmap.claimperson27), "不详","", "", "跛脚  阿兹海默症"
                , "跛脚", "阿兹海默症","黑裤子 红帽子", "红帽子", "黑裤子", "","2021年2月16日", "宁波市江北区下江路附近"
                , "2000.6.6", "浙江宁波", "175", "老人走失", "如有认识他的好心人，请联系我们！","1000114"));

        return noticePeople;
    }

    public static ActionListItem getSearchPerson(Context context){
        return new ActionListItem(UiUtils.resourceToBitmap(context, R.mipmap.clipboard),
                context.getResources().getString(R.string.lostperson_actiontitle),
                context.getResources().getString(R.string.lostperson_lostplace), context.getResources().getString(R.string.lostperson_actiontime),
                "城市寻人", "志愿保险", "低风险", 5,
                25);
    }


    public static Person getNoticePerson(Context context){
        return new Person(UiUtils.resourceToBitmap(context, R.mipmap.clipboard),
                context.getResources().getString(R.string.lostperson_name),"男", "68",
                context.getResources().getString(R.string.lostperson_lostfeature1),
                resToString(context,R.string.lostperson_lostfeature2)
                , resToString(context,R.string.lostperson_lostfeature2),
                resToString(context,R.string.lostperson_clothes),
                resToString(context,R.string.lostperson_clothes),
                resToString(context,R.string.lostperson_clothe2),
                resToString(context,R.string.lostperson_clothe2),
                resToString(context,R.string.lostperson_losttime),
                resToString(context,R.string.lostperson_lostplace),
                resToString(context,R.string.lostperson_birth),
                resToString(context,R.string.lostperson_birthplace),
                resToString(context,R.string.lostperson_heigth),
                resToString(context,R.string.lostperson_losttype),
                resToString(context,R.string.lostperson_familysupplement));



//        new Person(UiUtils.resourceToBitmap(context, R.mipmap.lostperson_img3), "刘芳琴",
//                "女",
//                "65",
//                "跛脚"
//                , "跛脚",
//                "阿兹海默症",
//                "黑裤子 红帽子",
//                "红帽子",
//                "黑裤子",
//                "","2021年3月10日",
//                "宁波市镇海区慈海南路路附近"
//                , "2000.6.6",
//                "浙江舟山", "175",
//                "老人走失",
//                "");
    }


    public static List<CommunityItems> getCommunityData(Context context, CommunityItems.TYPE type,CommunityItems.GROUP group){

        if(type== CommunityItems.TYPE.FIND){
            if(group== CommunityItems.GROUP.COMMEND)
               return CommunityDataUtils.getCommunityData1(context);
            else
                return CommunityDataUtils.getCommunityData2(context);
        }else if(type== CommunityItems.TYPE.CITY){
            if(group== CommunityItems.GROUP.COMMEND)
                return CommunityDataUtils.getCommunityData3(context);
            else
                return CommunityDataUtils.getCommunityData4(context);
        }


        return null;
    }


    /**
     * 模拟得到val的message信息
     * @param context
     * @return
     */
    public static List<MessageListItem> getValData(Context context){
        List<MessageListItem> messageListItems=new ArrayList<>();
        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(context, R.mipmap.mine2_urgentimg1),
                "系统消息","您申请的海曙老人救援行动已经开始行动，快去参加吧！",1,"9:12"));
//        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(context,R.mipmap.background32),
//                "蓝天救援队","欢迎大家加入蓝天大家庭，希望大家能够同心协力，一起为",3,"12:20"));
//        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(context,R.mipmap.background33),
//                "海曙老人救援行动[队长群]","[语言]",16,"刚刚"));
//        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(context,R.mipmap.background33),
//                "海曙老人救援行动[尖刀小队]","[语言]",5,"刚刚"));

        return messageListItems;
    }

    /**
     * 模拟得到Fml的message信息
     * @param context
     * @return
     */
    public static List<MessageListItem> getFmlData(Context context){
        List<MessageListItem> messageListItems=new ArrayList<>();
        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(context, R.mipmap.mine2_urgentimg1),
                "系统消息","您提交的老人走失已经开始行动!",1,"9:12"));
//        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(context,R.mipmap.background32),
//                "蓝天救援队","欢迎大家加入蓝天大家庭，希望大家能够同心协力，一起为",3,"12:20"));
//        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(context,R.mipmap.background33),
//                "海曙老人救援行动[队长群]","[语言]",16,"刚刚"));
//        messageListItems.add(new MessageListItem(UiUtils.resourceToBitmap(context,R.mipmap.background33),
//                "海曙老人救援行动[尖刀小队]","[语言]",5,"刚刚"));


        return messageListItems;

    }


    /**
     * 跳转的时候传入person类和headimg，两个分开传
     * @param context
     * @param c
     * @param person
     * @param headimg
     */
    public static void startActivityWithPerson(Context context, Class c, Person person, Bitmap headimg){
        Intent intent=new Intent(context, ClaimPersonDetailsActivity.class);
        intent.putExtra("person",person);


        ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
        headimg.compress(Bitmap.CompressFormat.PNG, 100, output);//把bitmap100%高质量压缩 到 output对象里
        byte[] result = output.toByteArray();
        intent.putExtra("headimg",result);

        context.startActivity(intent);
    }


    public static Person getPersonFromIntent(Context context){

        Activity activity= (Activity) context;
        Person person= activity.getIntent().getParcelableExtra("person");
        byte[] res = activity.getIntent().getByteArrayExtra("headimg");
        Bitmap bitmap= getPicFromBytes(res,null);
        if(null!=bitmap)
            person.setImg(bitmap);

        return person;
    }


    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {

        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,  opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }



    private static String resToString(Context context,int Resourse){
        return context.getResources().getString(Resourse);
    }


}




















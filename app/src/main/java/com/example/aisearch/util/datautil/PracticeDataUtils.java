package com.example.aisearch.util.datautil;

import com.example.aisearch.bean.volunteer.Video;
import com.example.aisearch.ui.volunteer.train.adapter.PracticeIndexAdapter;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author : XJC
 * @Time : 2021/4/2 7:54
 * @Description :
 *
 */
public class PracticeDataUtils {


    public static List getMineVideos(){
        List videos=new ArrayList();
        videos.add(new Video("急救员资格", PracticeIndexAdapter.SKILL, "志愿者精神体现着个人对生命，社会，人类，和世界",
                1, 4231,1,"68","","救生员资格培训",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("水上救生救援", PracticeIndexAdapter.SKILL, "志愿者精神体现着个人对生命，社会，人类，和世界",
                3, 4231,1,"9","","水上救生救援",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("常见注意事项", PracticeIndexAdapter.SKILL, "志愿者精神体现着个人对生命，社会，人类，和世界",
                3, 4231,1,"100","","常见注意事项培训",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));

        return videos;
    }


    public static List getFreshBirdVideos(){
        List videos=new ArrayList();
        videos.add(new Video("app的使用", PracticeIndexAdapter.FRESH, "app的有效使用能减轻很多不必要的事件，能让志愿者更加的专注于",
                2, 4256,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("基础知识", PracticeIndexAdapter.FRESH, "志愿者是指不为物质报酬,基于良知、信念和责任,志愿为社会和他人提供服务 和帮助的人",
                1, 4231,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("行动流程与事项", PracticeIndexAdapter.FRESH, "组织签到。社区活动组织者要安排专人负责签到,对志愿者迟到、缺席、早退的情况做好详细记录",
                3, 3876,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("常见急救知识", PracticeIndexAdapter.FRESH, "首先是普通类的情况,最常见的是扭伤和抽筋,那么什么是扭伤呢,关节过猛的扭转、 撕裂",
                3, 4112,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("优秀志愿者心得", PracticeIndexAdapter.FRESH, "经常参加本村志愿服务活动,可以说是志愿服务给了我最好的锻炼机会和实践舞台",
                2, 3998,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("志愿者的意义", PracticeIndexAdapter.FRESH, "社会价值 对社会而言,志愿活动具有以下积极意义,一是传递爱心,传播文明。志愿者在把关怀带给社会的同时,也传递了爱心,传播了文明,这种“爱心",
                2, 2146,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("常见注意事项", PracticeIndexAdapter.FRESH, "要重视协会活动短信,慎重考虑能否参加后应及时回复或主动联系召集人",
                1, 4500,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        return videos;
    }


    public static List getSkillImpvVideos(){
        List videos=new ArrayList();
        videos.add(new Video("水上救生救援", PracticeIndexAdapter.SKILL, "水上救援是一项技术性强,但是危险性高的活动,不仅对水上救援志愿者的体力和技术有严格的要求,",
                4, 2031,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("急救资格", PracticeIndexAdapter.SKILL, "愿者医疗急救常识一、心肺复苏(cpr)(一)心肺复苏适应症心肺复苏适用于",
                4, 2897,1,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("野外生存", PracticeIndexAdapter.SKILL, "夏季野外生存必备品一、 穿着方面应选择舒适而厚底的鞋,如旅游鞋",
                5, 1045,2,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("技术装备使用", PracticeIndexAdapter.SKILL, "技术装备使用方法及措施为切实加强志愿者教育技术装备管理",
                5, 1021,2,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("破拆器材使用", PracticeIndexAdapter.SKILL, "手动破拆工具是一种抢险救援所用的破拆工具的组合,它综合了世界普遍使用的救援破拆工具功能",
                5, 973,2,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("志愿者的意义", PracticeIndexAdapter.SKILL, "志愿者精神体现着个人对生命，社会，人类，和世界",
                5, 941,2,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));
        videos.add(new Video("常见注意事项", PracticeIndexAdapter.SKILL, "到活动现场后,要主动向召集人签到、领帽子; 13、要注意形象,胸章戴在左",
                5, 926,2,"100","","",
                "溺水","水上救生设备的使用","水中拖救姿势"
                ,"上岸的急救方法1","上岸的急救方法2","","","溺水"));


        return videos;
    }

}

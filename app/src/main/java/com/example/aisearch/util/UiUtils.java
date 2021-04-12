package com.example.aisearch.util;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;

import com.bigkoo.pickerview.TimePickerView;
import com.example.aisearch.R;
import com.example.aisearch.ui.family.take_a_hand.share.ShareActivity1;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author XJC
 * @data 2021/1/23
 *
 * 对组件的封装，主要是QMUI框架的组件，用于实现简单的界面
 *
 */
public class UiUtils {

    public static String Action_Time="2021年3月9日8:00——3月10日8:00";
    public static String Action_Time_detailed="2021.1.21-1.22";
    public static String Lost_Time="2021年4月10日";
    public static String Lost_Time_detailed="2020年12月1日 9:00";




    /**
     *  implementation "com.contrarywind:Android-PickerView:3.2.7"
     *
     * 时间选择器
     * @param context
     * @param textView 回调内容到TextView
     * 更改getTimes方法中的Date来改变显示的日期，改变setType来改变选择器的展示
     *
     */
    public static void ShowTimeSelector(Context context,TextView textView){
        TimePickerView pvTime;
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2010, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2040, 11, 31);


        //时间选择器
        pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                TextView tv = (TextView) v;
                tv.setText(getTimes(date));
            }
        })
                //年月日时分秒的显示与否，不设置则默认全部显示，这里可自行定制
                .setType(new boolean[]{true, true, true, true, false, false})
                .setLabel(" 年", "月", "日", "时", "分", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();

        pvTime.show(textView);
    }

    private static String getTimes(Date date) {//可根据需要自行格式化数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时");
        return format.format(date);
    }


    /**
     * TipDialog
     * @param context
     * @param position 要显示的信息
     */
    public static void ShowTipDialog(Context context,int position){
        QMUITipDialog tipDialog;
        switch (position) {
            //显示发送成功
            case 1:
                tipDialog = new QMUITipDialog.Builder(context)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .setTipWord("发送成功")
                        .create();
                break;
            //显示发送失败
            case 2:
                tipDialog = new QMUITipDialog.Builder(context)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                        .setTipWord("发送失败")
                        .create();
                break;
            //显示请勿重复操作
            case 3:
                tipDialog = new QMUITipDialog.Builder(context)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                        .setTipWord("请勿重复操作")
                        .create();
                break;
            //显示成功图标
            case 4:
                tipDialog = new QMUITipDialog.Builder(context)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .create();
                break;
            // 显示文字
            case 5:
                tipDialog = new QMUITipDialog.Builder(context)
                        .setTipWord("请勿重复操作")
                        .create();
                break;
            //显示加载
            default:
                tipDialog = new QMUITipDialog.Builder(context)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord("正在加载")
                        .create();
        }
        tipDialog.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run() {
                tipDialog.dismiss();
            }
        }, 1000);
    }


    /**
     * Popups
     * @param anchor 锚点，也是popus弹出的位置
     * @param showView 要展示的Viwe
     * @param context
     * @return  返回组件本身，用于dismiss
     *
     */
    public static QMUIPopup ShowPopups(View anchor,View showView,Context context){
        QMUIPopup qmuiPopups1= QMUIPopups.popup(context, QMUIDisplayHelper.dp2px(context, 70),
                QMUIDisplayHelper.dp2px(context, 170))
                .preferredDirection(QMUIPopup.DIRECTION_BOTTOM)
                .view(showView)
                .edgeProtection(QMUIDisplayHelper.dp2px(context, 20))
                .offsetX(QMUIDisplayHelper.dp2px(context, 20))
                .offsetYIfBottom(QMUIDisplayHelper.dp2px(context, 5))
                .shadow(false)
                .arrow(false)
                .animStyle(QMUIPopup.ANIM_AUTO)
                .borderWidth(0)
                .onDismiss(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
//                        UiUtils.showMsg(MainActivity3.this,"dismiss");
                    }
                })
                .show(anchor);

        return qmuiPopups1;
    }


    /**
     * QMUIBottomSheet
     * 底部分享界面的弹出
     * @param context
     */
    public static void ShareToOthers(Context context){
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        final int TAG_SHARE_CHAT = 3;
        final int TAG_SHARE_LOCAL = 4;

        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet.BottomGridSheetBuilder(context);
        builder.addItem(R.mipmap.icon_wechart, "分享到微信", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_friend, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_weibo, "分享到微博", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_qq, "分享到QQ", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_qqspace , "分享到空间", TAG_SHARE_LOCAL, QMUIBottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView) {
                        dialog.dismiss();
                        int tag = (int) itemView.getTag();
                        switch (tag) {
                            case TAG_SHARE_WECHAT_FRIEND:
                                Share(context,"com.tencent.mm");
                                break;
                            case TAG_SHARE_WECHAT_MOMENT:
                                context.startActivity(new Intent(context, ShareActivity1.class));
//                                Toast.makeText(context, "分享到朋友圈", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_WEIBO:
                                Toast.makeText(context, "分享到微博", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_CHAT:
                                Toast.makeText(context, "分享到QQ", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_LOCAL:
                                Toast.makeText(context, "分享到QQ空间", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }).build().show();
    }


    /**
     * 分享到微信
     */
    public static void Share(Context context,String packageName){
        new QMUIDialog.MessageDialogBuilder(context)
                .setTitle("口令已复制")
                .setMessage("8.0 ha:/\uD83D\uDD10iZ8BcvuxDoq₤匋一宝" +
                        "【NEW BALANCE银泰专柜2020秋季新品男女同款羽绒服NCNPA4S013】" +
                        "fsagasgsdtfhgkjgndfhshhd")
                .addAction("去微信粘贴给好友", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        openOthers(context,packageName);
                        dialog.dismiss();
                    }
                })
                .show();
        //放置内容到剪切板
        ClipboardManager cbm=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        cbm.setText( "8.0 ha:/\uD83D\uDD10iZ8BcvuxDoq₤匋一宝" +
                "【NEW BALANCE银泰专柜2020秋季新品男女同款羽绒服NCNPA4S013】" +
                "fsagasgsdtfhgkjgndfhshhd");
        String s=cbm.getText().toString();
    }


    /**
     * 打开其他应用
     * @param context
     * @param packageName
     */
    public static void openOthers(Context context,String packageName) {
        Intent lan =context.getPackageManager().getLaunchIntentForPackage(packageName);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(lan.getComponent());
        context.startActivity(intent);
    }

    /**
     * 根据包名检测某个APP是否安装
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/6/27,13:02
     * <h3>UpdateTime</h3> 2016/6/27,13:02
     * <h3>CreateAuthor</h3>
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     * @return true 安装 false 没有安装
     *
     *  pacage "/data/data/com.baidu.BaiduMap"
     */
    public static boolean isInstalled(String pacage) {
        return new File(pacage).exists();
    }

    /**
     * toast的封装
     */
    public static void showMsg(Context context, String msg){
        Toast mToast=null;
        if (mToast == null) {
            mToast = Toast.makeText(context,msg,Toast.LENGTH_LONG);
        }else{
            mToast.cancel();
            mToast = Toast.makeText(context,msg,Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    /**
     * 把资源文件转成bitmap
     * @param context
     * @param resource
     * @return
     */
    public static Bitmap resourceToBitmap(Context context, @DrawableRes int resource){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),resource);
        return bitmap;
    }


    public static Bitmap bitmapFitWidth(Context context,int targetWidth,@DrawableRes int resource){
        Bitmap bitmap=resourceToBitmap(context,resource);
        return bitmapFitWidth(targetWidth,bitmap);

    }


    /**
     * 不改变bitmap宽高比的情况下，构建一个宽度为targetWidth的bitmap
     * @param targetWidth 固定宽度
     * @param bitmap 入参
     * @return bitmap
     */
    public static Bitmap bitmapFitWidth(int targetWidth,Bitmap bitmap){
        double aspectRatio = (double) bitmap.getHeight() / (double) bitmap.getWidth();
        int targetHeight = (int) (targetWidth * aspectRatio);
        Bitmap result = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
        return result;
    }



    public static void callPhone(Context context,String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }


    public static int getDeviceWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 是否显示状态栏
     * @param activity
     * @param enable
     */
    public static void fullscreen(Activity activity, boolean enable) {
        if (enable) { //显示状态栏
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(lp);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else { //隐藏状态栏
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(lp);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public static byte[] ImgToByteArray(Bitmap headimg){

        ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
        headimg.compress(Bitmap.CompressFormat.PNG, 100, output);//把bitmap100%高质量压缩 到 output对象里
        byte[] result = output.toByteArray();
        return result;
    }

}
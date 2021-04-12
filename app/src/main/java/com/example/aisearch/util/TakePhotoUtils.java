package com.example.aisearch.util;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author 项佳诚
 * @data 2021/1/21
 *
 * 让用户选择从本地传入照片或者拍照。
 * 使用的时候，new一个对象,把按钮绑定在这个类上，在activity的onActivityResult方法中，
 * 实现PhotoActivityResult.
 *
 */
public class TakePhotoUtils implements View.OnClickListener {

    public static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    public static final int CROP_PHOTO = 2;
    public static final int CHOOSE_PHOTO = 3;
    private Activity activity;

    private Uri imageUri;
    public static File tempFile;

    //返回的照片
    Bitmap bitmap=null;

    public TakePhotoUtils(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        //打开一个dialog，让用户选择从相册选还是自己拍摄照片，使用QMUI
        new QMUIBottomSheet.BottomListSheetBuilder(activity)
                .addItem("从相册选择")
                .addItem("拍照")
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        switch (position){
                            case 0:
                                choosePhoto();
                                break;
                            case 1:
                                openCamera();
                                break;
                        }
                    }
                })
                .build()
                .show();
    }

    //打开相机
    public void openCamera() {
        if(!RequestPermission())
            return ;
        //獲取系統版本
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                    "yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    filename + ".jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri
                imageUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());

                imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            }
        }

        //开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    //是否可以存储
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    //请求相机权限和存储权限
    private boolean RequestPermission(){

        //检查是否有存储权限，以免崩溃
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
        ||ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(activity,new String[]{
                    Manifest.permission.CAMERA
                    ,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            return false;
        }

        return true;
    }


    //进入相册
    private void choosePhoto(){
        if(!RequestPermission())
            return ;

        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intentToPickPic, CHOOSE_PHOTO);
    }


    public Bitmap PhotoActivityResult(int requestCode, int resultCode, Intent data){

        switch (requestCode) {
            case TakePhotoUtils.PHOTO_REQUEST_CAREMA:
                if (resultCode == Activity.RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    //image/*就是权限的格式
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    activity.startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case TakePhotoUtils.CROP_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        bitmap = BitmapFactory.decodeStream(activity.getContentResolver()
                                .openInputStream(imageUri));
                        //picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO: {
                // 获取图片
                try {
                    //该uri是上一个Activity返回的
                    imageUri = data.getData();
                    bitmap= BitmapFactory.decodeStream(activity.getContentResolver()
                            .openInputStream(imageUri));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        if (null!=bitmap)
            return bitmap;
        else
            return null;
    }

}

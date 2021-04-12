package com.example.aisearch.ui.volunteer.publish;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.core.content.FileProvider;

import com.example.aisearch.R;

import java.io.File;
import java.io.IOException;

public class ShowBottomDialog {
    private View view;
    private File outputImage;
    private Uri imageUri;
    private int takePhoto = 1;
    private int fromAlbum = 2;
    public void BottomDialog(Activity activity) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
        //2、设置布局
        view = View.inflate(activity, R.layout.dialog_bottom, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        dialog.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputImage = new File(activity.getExternalCacheDir(),"output_image.jpg");
                if(outputImage.exists()){
                    outputImage.delete();
                }
                try {
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                    imageUri =  FileProvider.getUriForFile(activity,"com.studio701.cameraalbum.fileprovider"
                            ,outputImage);
                }else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                activity.startActivityForResult(intent,takePhoto);
            }
        });

        dialog.findViewById(R.id.tv_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                activity.startActivityForResult(intent,fromAlbum);
            }
        });

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }


}

package com.example.aisearch.ui.volunteer.home.publish;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.aisearch.R;
import com.example.aisearch.ui.volunteer.train.dialog.LockDialog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScreenActivity extends AppCompatActivity {
    private ImageView add;
    private int takePhoto = 1;
    private int fromAlbum = 2;
    private Uri imageUri;
    private File outputImage;
    Dialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);


        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);


        String s= getIntent().getStringExtra("see");
        RelativeLayout title=findViewById(R.id.title);
        if ("yes".equals(s))
            title.setVisibility(View.GONE);



        add = findViewById(R.id.iv_add);
        initDialog();

        dialog.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                if(outputImage.exists()){
                    outputImage.delete();
                }
                try {
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                    imageUri =  FileProvider.getUriForFile(ScreenActivity.this,"com.studio701.cameraalbum.fileprovider"
                            ,outputImage);
                }else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,takePhoto);
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent,fromAlbum);
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        initView();
    }
    private void initDialog(){
//1、使用Dialog、设置style
        dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_bottom, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    private void initView(){
        ImageView iv_return=findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LockDialog(ScreenActivity.this, new LockDialog.ClickCallBack() {
                    @Override
                    public void onStop() {
                        finish();
                    }
                },"感谢您的帮助").show();
            }
        });





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==takePhoto){

            if (resultCode== Activity.RESULT_OK){
                Bitmap bitmap = null;

                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                add.setImageBitmap(bitmap);

            }
        }
        if (requestCode==fromAlbum){


            //获取返回的数据，这里是android自定义的Uri地址
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
              ;
                bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(selectedImage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            add.setImageBitmap(bitmap);


        }
    }
}
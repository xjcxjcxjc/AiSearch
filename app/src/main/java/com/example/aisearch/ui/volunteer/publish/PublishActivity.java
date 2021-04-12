package com.example.aisearch.ui.volunteer.publish;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.aisearch.R;
import com.example.aisearch.util.UiUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PublishActivity extends AppCompatActivity implements View.OnClickListener{
    private Button kindTravel,kindPoem,kindFood,kindGnosis,kindWrit;
    private ImageView addPic;
    private int takePhoto = 1;
    private int fromAlbum = 2;
    private Uri imageUri;
    private File outputImage;
    private TextView tvLocation;
    private EditText title,content;


    //完成按钮
    private TextView tv_Share;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        ImageView iv_return=findViewById(R.id.iv_return);
        TextView iv_share=findViewById(R.id.iv_share);
        title=findViewById(R.id.et_title);
        content=findViewById(R.id.et_content);



        iv_return.setOnClickListener((v)->{
            finish();
        });


        iv_share.setOnClickListener((v)->{
            Intent intent=new Intent();
            addPic.setDrawingCacheEnabled(true);
            byte[] imgbt= UiUtils.ImgToByteArray(addPic.getDrawingCache());
            addPic.setDrawingCacheEnabled(false);
            intent.putExtra("img",imgbt);
            intent.putExtra("title",title.getText().toString());
            intent.putExtra("content",content.getText().toString());
            setResult(Activity.RESULT_OK,intent);  //Activity.RESULT_OK   resultcode
            finish();
        });

        init();
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
                    imageUri =  FileProvider.getUriForFile(PublishActivity.this,"com.studio701.cameraalbum.fileprovider"
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
    }
    private void init(){
        kindPoem = findViewById(R.id.btn_kind_poem);
        kindTravel = findViewById(R.id.btn_kind_travelnotes);
        kindFood = findViewById(R.id.btn_kind_food);
        kindGnosis = findViewById(R.id.btn_kind_gnosis);
        kindWrit = findViewById(R.id.btn_kind_writ);
        addPic = findViewById(R.id.iv_add_pic);
        tvLocation = findViewById(R.id.tv_location);
        title = findViewById(R.id.et_title);
        content = findViewById(R.id.et_content);
        tvLocation.setOnClickListener(this);
        addPic.setOnClickListener(this);
        kindWrit.setOnClickListener(this);
        kindGnosis.setOnClickListener(this);
        kindFood.setOnClickListener(this);
        kindTravel.setOnClickListener(this);
        kindPoem.setOnClickListener(this);
        initDialog();
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
                addPic.setImageBitmap(bitmap);
                System.out.println("设置了图片2");
            }
        }
        if (requestCode==fromAlbum){


            //获取返回的数据，这里是android自定义的Uri地址
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                System.out.println("设置了图片3");
                bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(selectedImage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("设置了图片4");
            addPic.setImageBitmap(bitmap);


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_kind_poem:
                setEnable(kindPoem);

                break;
            case R.id.btn_kind_travelnotes:
                setEnable(kindTravel);

                break;
            case R.id.btn_kind_food:
                setEnable(kindFood);

                break;
            case R.id.btn_kind_gnosis:
                setEnable(kindGnosis);
                break;
            case R.id.btn_kind_writ:
                setEnable(kindWrit);
                break;
            case R.id.iv_add_pic:
                dialog.show();
                break;
        }
    }
    private void setEnable(Button btn) {
        List<Button> buttonList=new ArrayList<>();
        if (buttonList.size()==0){
            buttonList.add(kindTravel);
            buttonList.add(kindPoem);
            buttonList.add(kindWrit);
            buttonList.add(kindFood);
            buttonList.add(kindGnosis);

        }
        for (int i = 0; i <buttonList.size() ; i++) {
            buttonList.get(i).setEnabled(true);
        }
        btn.setEnabled(false);
    }


}
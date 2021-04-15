package com.example.aisearch.ui.volunteer.home.zwactivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.example.aisearch.ui.volunteer.train.dialog.LockDialog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPeopleByImageActivity extends BaseActivity implements View.OnClickListener {

    private static final int CHOOSE_PHOTO = 2;//选择照片
    private static final int TAKE_PHOTO = 1;//拍照
    private ImageView iv_show_img;
    private RelativeLayout take_photo;
    private Uri imageUri;

    @BindView(R.id.rl_back)
    RelativeLayout bacgrd1;
    @BindView(R.id.rl_content)
    RelativeLayout bacgrd2;
    @BindView(R.id.cv_new)
    CardView cv_new;

    @BindView(R.id.btn_return)
    Button btn_return;
    LockDialog lockDialog;

    /**
     * 请求选择本地图片文件的请求码
     */
    private static final int ACTION_CHOOSE_IMAGE = 0x201;
    Bitmap mBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_people_by_image);
        /**
         * 实例化，并设立点击事件
         */
        ButterKnife.bind(this);
        iv_show_img = findViewById(R.id.iv_show_img);
        take_photo = findViewById(R.id.rl_take_picture);
        take_photo.setOnClickListener(this);
        QMUIStatusBarHelper.translucent(this);
        initBackGround();

    }


    private void initBackGround(){
        SharedPreferences sharedPreferences;
        sharedPreferences= getSharedPreferences("data",  MODE_PRIVATE);   //私自模式，只允许本应用访问
        int identity= sharedPreferences.getInt("identity",0);

        if (identity== LodingActivity.IDENTIFY_FML){
            bacgrd1.setBackgroundColor( getResources().getColor(R.color.title_red));
            bacgrd2.setBackgroundColor( getResources().getColor(R.color.title_red));
            cv_new.setCardBackgroundColor(getResources().getColor(R.color.title_red));

        }
        else{
            bacgrd1.setBackgroundColor( getResources().getColor(R.color.title_blue));
            bacgrd2.setBackgroundColor( getResources().getColor(R.color.title_blue));
            cv_new.setCardBackgroundColor(getResources().getColor(R.color.title_blue));
        }


        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccess();
            }
        });
    }


    private void showSuccess(){
        lockDialog=new LockDialog(this, new LockDialog.ClickCallBack() {
            @Override
            public void onStop() {
                lockDialog.dismiss();
                finish();
            }
        },
                "内容已提交");
        lockDialog.show();

    }



    /**
     * 请求权限的请求码
     */
    private static final int ACTION_REQUEST_PERMISSIONS = 0x001;
    private static String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_take_picture:

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    List<String> permissionList = new ArrayList<>(Arrays.asList(NEEDED_PERMISSIONS));
                    permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                    NEEDED_PERMISSIONS = permissionList.toArray(new String[0]);
                }

                if (!checkPermissions(NEEDED_PERMISSIONS)) {
                    ActivityCompat.requestPermissions(this, NEEDED_PERMISSIONS, ACTION_REQUEST_PERMISSIONS);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, ACTION_CHOOSE_IMAGE);
                }

                break;

        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);//打开相册
    }

    /**
     * 重写onRequestPermissionsResult方法
     * 对查看本地相册的权限申请进行判断看是否申请成功
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    openAlbum();//申请成功
                }
                else
                {
                    Toast.makeText(this,"你拒绝了权限！",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    @Override
    void afterRequestPermission(int requestCode, boolean isAllGranted) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_CHOOSE_IMAGE) {
            if (data == null || data.getData() == null) {
                showToast(getString(R.string.get_picture_failed));
                return;
            }
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            if (mBitmap == null) {
                showToast(getString(R.string.get_picture_failed));
                return;
            }
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
            iv_show_img.setImageBitmap(mBitmap);
            iv_show_img.setLayoutParams(params);
        }
    }






}
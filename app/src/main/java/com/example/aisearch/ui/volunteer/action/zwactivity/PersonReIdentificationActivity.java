package com.example.aisearch.ui.volunteer.action.zwactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.example.aisearch.R;
import com.example.aisearch.ui.volunteer.practice.dialog.LockDialog;
import com.example.aisearch.util.TakePhotoUtils;
import com.example.aisearch.util.UiUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.json.JSONObject;

public class PersonReIdentificationActivity extends AppCompatActivity {
    AipBodyAnalysis client;
    Button button;
    QMUIRoundButton btn_Search;
    Handler h = null;
    int imgWidth = 0;
    int imgHeight = 0;
    float xp=1;
    float yp=1;
    float widthp=1;
    float heightp=1;
    Bitmap bitmap;
    Display display;
    ImageView iv_Person;
    Button finish;
    TextView bodystatu;
    TextView faceidtf;
    TextView clothidf;



    TakePhotoUtils t = new TakePhotoUtils(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_re_identification2);

        QMUIStatusBarHelper.translucent(this);


        finish = findViewById(R.id.finish);
        button = findViewById(R.id.button);
        bodystatu= findViewById(R.id.iv_bodystatu);
        faceidtf= findViewById(R.id.iv_faceidt);
        clothidf= findViewById(R.id.iv_clothidt);

        display = getWindowManager().getDefaultDisplay();
        //获取老人照片点击事件
        iv_Person = findViewById(R.id.iv_person);
        iv_Person.setOnClickListener(t);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        h = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                JSONObject location = (JSONObject) msg.obj;
                System.out.println(location);
                int x = 0,y=0;
                int width = 0,height=0;
                try {
                    x = location.getInt("top");
                    y = location.getInt("left");
                    width = location.getInt("width");
                    height = location.getInt("height");
                }catch (Exception e){
                    e.printStackTrace();
               }
                System.out.println("iv_Person.getHeight():"+(float)iv_Person.getHeight());
                System.out.println("imgHeight:"+(float)imgHeight);
                xp = (float)iv_Person.getWidth()/(float)imgWidth;
                yp = (float)iv_Person.getHeight()/(float)imgHeight;
                System.out.println("ImageView尺寸：width："+iv_Person.getWidth()+":"+iv_Person.getHeight());
//                xp = (float)display.getWidth()/(float)imgWidth;
//                yp = (float)display.getHeight()/(float)imgHeight;
                //button.setPadding(x,y,200,200);
//                System.out.println("手机尺寸："+ display.getWidth()+","+display.getHeight()+",比例："+xp+"，比例："+yp);
//                button.setX(x*xp);
//                button.setY(y*yp);
//                button.setWidth((int) (width*xp));
//                button.setHeight((int) (height*yp));
//                ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
//                button.setX(x*xp+dip2px(PersonReIdentificationActivity.this,100));
                button.setX(x*xp);

                button.setY(y*yp+dip2px(PersonReIdentificationActivity.this,40));
                button.setWidth((int) (width*xp));
                button.setHeight((int) (height*yp));
                ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
                bodystatu.setText("行走");
                faceidtf.setText("92%");
                clothidf.setText("97%");
                initDialog();
            }
        };
        btn_Search = findViewById(R.id.btn_identify);
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getAipBody(bitmap);
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                bodystatu.setText("行走");
                bodystatu.setText("静止");
                faceidtf.setText("92%");
                clothidf.setText("97%");
                initDialog();
            }
        }, 1000);


            }
        });

    }


    private void initDialog(){
        new LockDialog(this,() -> {
            UiUtils.showMsg(this,"反馈成功");
        },"相似度高，反馈平台").show();



    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = t.PhotoActivityResult(requestCode,resultCode,data);
        iv_Person.setImageBitmap(bitmap);
    }

    private void getAipBody(Bitmap bitmapT){
        /*
        client = AipBodyUtils.getAipBodyAnalysisInstance();
        new Thread(){
            @Override
            public void run() {
                super.run();
                //@SuppressLint("ResourceType") InputStream is = getResources().openRawResource(id);
                //Bitmap bitmap = BitmapFactory.decodeStream(is);
                System.out.println(bitmapT.toString());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                bitmapT.compress(Bitmap.CompressFormat.PNG,100,out);
                byte[] img = out.toByteArray();
                JSONObject res = client.bodyAnalysis(img,new HashMap<String, String>());
                HashMap<String,String> map = new HashMap<>();
                System.out.println(res.toString());
                res = client.bodyAttr(img,map);
                System.out.println(res.toString());
                imgWidth = bitmapT.getWidth();
                imgHeight = bitmapT.getHeight();
                System.out.println("图片尺寸：width："+bitmapT.getWidth()+",height:"+bitmapT.getHeight());
                Display display = getWindowManager().getDefaultDisplay();
                //xp = (float) ((display.getWidth()/bitmap.getWidth())*1.2);
                //yp = (float) ((display.getHeight()/bitmap.getHeight())*1.3);

                Message message = Message.obtain();
                try {
                    JSONArray person_info = res.getJSONArray("person_info");
                    System.out.println("person_info:"+person_info.toString());
                    JSONObject attributes = person_info.getJSONObject(0);
                    JSONObject attributesArry = attributes.getJSONObject("location");
                    //System.out.println("attributesArry:"+attributesArry);
                    // location = attributesArry.getJSONObject("gender");
                    //JSONObject location = person_info.getJSONObject("attributes").getJSONObject("location");
                    System.out.println("location:"+attributesArry.toString());
                    message.obj = attributesArry;
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("体态位置：width："+bitmap.getWidth()+",height:"+bitmap.getHeight());
                System.out.println("体态尺寸：width："+bitmap.getWidth()+",height:"+bitmap.getHeight());
                h.sendMessage(message);
            }
        }.start();

         */
    }
}
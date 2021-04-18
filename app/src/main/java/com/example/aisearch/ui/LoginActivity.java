package com.example.aisearch.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aisearch.LodingActivity;
import com.example.aisearch.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog = null;

    QMUIRoundButton login;

    @BindView(R.id.pwdet)
    EditText pwd;
    @BindView(R.id.login2)
    Button login2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);

        JMessageClient.setDebugMode(true);
        JMessageClient.init(getApplicationContext());
        JMessageClient.registerEventReceiver(this);

        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);

        login=findViewById(R.id.login);
        login.setOnClickListener(v -> {
            login(1);
        });
        login2.setOnClickListener(v -> {
            login(2);
        });
    }

    private void init(){






    }



    private void login(int num){
        String user = "";
        String pwd = "";
        if(num==1){
            user = "2408003640";
            pwd = "123456";
        }else if(num==2){
            user = "123456";
            pwd = "123456";
        }else if(num==3){
            user = "654321";
            pwd = "654321";
        }
        mProgressDialog = ProgressDialog.show(LoginActivity.this, "提示：", "正在加载中。。。");
        mProgressDialog.setCanceledOnTouchOutside(true);
        JMessageClient.login(user, pwd, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String LoginDesc) {
                if (responseCode == 0) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    Log.i("MainActivity", "JMessageClient.login" + ", responseCode = " + responseCode + " ; LoginDesc = " + LoginDesc);
                    startActivity(new Intent(getApplicationContext(), LodingActivity.class));
                    finish();
                } else {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    Log.i("MainActivity", "JMessageClient.login" + ", responseCode = " + responseCode + " ; LoginDesc = " + LoginDesc);
                }
            }
        });
    }

    public void onEvent(MessageEvent event) {
        final Message message = event.getMessage();

        //若为群聊相关事件，如添加、删除群成员
        if (message.getContentType() == ContentType.eventNotification) {
        }
        runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
//                        tv_Message.setText(message.toString());
                        if (message.getTargetType() == ConversationType.single) {
                            UserInfo userInfo = (UserInfo) message.getTargetInfo();
                            String targetId = userInfo.getUserName();
                            String appKey = userInfo.getAppKey();
                        } else {
                        }
                    }
                }
        );
    }



    public void onEvent(OfflineMessageEvent event) {
        Conversation conv = event.getConversation();
//        tv_Message.setText(event.getConversation().toString());

    }

}
package com.example.aisearch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class TestChatActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog = null;
    private Button btn_Login1;
    private Button btn_Login2;
    private Button btn_Login3;
    private Button btn_Send;
    private TextView tv_Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chat);
        JMessageClient.setDebugMode(true);
        JMessageClient.init(getApplicationContext());
        JMessageClient.registerEventReceiver(this);
        //startActivity(new Intent(this, RegisterAndLoginActivity.class));
        tv_Message = findViewById(R.id.tv_message);
        btn_Login1 = findViewById(R.id.btn_login1);
        btn_Login2 = findViewById(R.id.btn_login2);
        btn_Login3 = findViewById(R.id.btn_login3);
        btn_Login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(1);
            }
        });
        btn_Login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(2);
            }
        });
        btn_Login3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(3);
            }
        });
        btn_Send = findViewById(R.id.btn_send);
        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
        mProgressDialog = ProgressDialog.show(TestChatActivity.this, "提示：", "正在加载中。。。");
        mProgressDialog.setCanceledOnTouchOutside(true);
        JMessageClient.login(user, pwd, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String LoginDesc) {
                if (responseCode == 0) {
                    mProgressDialog.dismiss();
                    tv_Message.setText("登录成功");
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    Log.i("MainActivity", "JMessageClient.login" + ", responseCode = " + responseCode + " ; LoginDesc = " + LoginDesc);
                    startActivity(new Intent(getApplicationContext(),LodingActivity.class));
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
                        tv_Message.setText(message.toString());
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
        tv_Message.setText(event.getConversation().toString());

    }

}
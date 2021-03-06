package com.example.aisearch.ui.volunteer.home.zwactivity.chat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.aisearch.R;
import com.example.aisearch.bean.util.User;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.adapter.ChatAdapter;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.bean.AudioMsgBody;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.bean.FileMsgBody;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.bean.ImageMsgBody;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.bean.Message;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.bean.MsgSendStatus;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.bean.MsgType;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.bean.TextMsgBody;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.bean.VideoMsgBody;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.util.ChatUiHelper;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.util.FileUtils;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.util.LogUtil;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.util.PictureFileUtil;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.widget.MediaManager;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.widget.RecordButton;
import com.example.aisearch.ui.volunteer.home.zwactivity.chat.widget.StateButton;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.UserInfo;
import io.reactivex.functions.Consumer;


    public class ChatActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

        @BindView(R.id.llContent)
        LinearLayout mLlContent;
        @BindView(R.id.rv_chat_list)
        RecyclerView mRvChat;
        @BindView(R.id.et_content)
        EditText mEtContent;
        @BindView(R.id.bottom_layout)
        RelativeLayout mRlBottomLayout;//??????,??????????????????
        @BindView(R.id.ivAdd)
        ImageView mIvAdd;
        @BindView(R.id.ivEmo)
        ImageView mIvEmo;
        @BindView(R.id.btn_send)
        StateButton mBtnSend;//????????????
        @BindView(R.id.ivAudio)
        ImageView mIvAudio;//????????????
        @BindView(R.id.btnAudio)
        RecordButton mBtnAudio;//????????????
        @BindView(R.id.rlEmotion)
        LinearLayout mLlEmotion;//????????????
        @BindView(R.id.llAdd)
        LinearLayout mLlAdd;//????????????
        @BindView(R.id.swipe_chat)
        SwipeRefreshLayout mSwipeRefresh;//????????????

        ImageView ivReturn;
        private ChatAdapter mAdapter;
        public static final String mSenderId="right";
        public static final String mTargetId="left";
        public static final int       REQUEST_CODE_IMAGE=0000;
        public static final int       REQUEST_CODE_VEDIO=1111;
        public static final int       REQUEST_CODE_FILE=2222;
        private UserInfo mUserInfo;
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chat);
            //?????????????????????
            ivReturn = findViewById(R.id.iv_return);
            ivReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestPermisson();
                }
            }, 100);

            initContent();
            mUserInfo = JMessageClient.getMyInfo();
            initUserMessage();
            initMesage();
            JMessageClient.registerEventReceiver(this);
        }
        public void initUserMessage(){
            if(mUserInfo.getUserName().equals("2408003640")){
                user1 = new User("??????","??????(??????????????????)");
                user2 = new User("??????","??????(??????????????????)");
                user3 = new User("??????","??????(??????????????????)");
            }else{
                user1 = new User("??????","??????(??????????????????)");
                user2 = new User("??????","??????(??????????????????)");
                user3 = new User("??????","??????(??????????????????)");
            }
        }
        //????????????
        public void initMesage(){
            ChatAdapter.num = 6;
            List<Message> mReceiveMsgList=new ArrayList<Message>();
            //??????????????????
            Message mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            AudioMsgBody audioMsgBody = new AudioMsgBody();
            audioMsgBody.setDuration(12);
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/voice_1618000549473.mp3");
            audioMsgBody.setExtra("????????????????????????????????????????????????????????????");
            audioMsgBody.setSize(12);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);

            mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            audioMsgBody = new AudioMsgBody();
            audioMsgBody.setDuration(6);
            audioMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/voice_1618000549473.mp3");
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setExtra("????????????????????????????????????");
            audioMsgBody.setSize(1);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);

            mAdapter.addData(0,mReceiveMsgList);


            mReceiveMsgList=new ArrayList<Message>();

            mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            audioMsgBody = new AudioMsgBody();
            audioMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/voice_1618000549473.mp3");
            audioMsgBody.setDuration(5);
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setExtra("????????????????????????????????????");
            audioMsgBody.setSize(1);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);

            mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            audioMsgBody = new AudioMsgBody();
            audioMsgBody.setDuration(5);
            audioMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/voice_1618000549473.mp3");
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setExtra("????????????????????????????????????");
            audioMsgBody.setSize(1);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);

            mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            audioMsgBody = new AudioMsgBody();
            audioMsgBody.setDuration(7);
            audioMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/voice_1618000549473.mp3");
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setExtra("????????????????????????????????????");
            audioMsgBody.setSize(1);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);

            mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            audioMsgBody = new AudioMsgBody();
            audioMsgBody.setDuration(12);
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/voice_1618000549473.mp3");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setExtra("????????????????????????????????????");
            audioMsgBody.setSize(1);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);
            mAdapter.addData(1,mReceiveMsgList);

        /*

        final Message mMessgae=getBaseSendMessage(MsgType.AUDIO);
        AudioMsgBody mFileMsgBody=new AudioMsgBody();
        mFileMsgBody.setDuration(2);
        mFileMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/media/728457826/60bc63bbe2fc9abaf5346a47/single_123456/voice/E269C330CA1268CF82C4D5BB0CD2B242.mp3");
        mFileMsgBody.setName2("??????(??????????????????)");
        mFileMsgBody.setDisplayName("??????");
        mFileMsgBody.setExtra("????????????????????????????????????");
        mFileMsgBody.setSize(1);
        mFileMsgBody.setLocalPath("");
        mFileMsgBody.setDuration(2);
        mMessgae.setBody(mFileMsgBody);
        //????????????
        mAdapter.addData( mMessgae);
        //???????????????????????????
        updateMsg(mMessgae);

         */
        }



        //????????????
        public void initMesage2(){
            ChatAdapter.num = 6;
            List<Message> mReceiveMsgList=new ArrayList<Message>();
            //??????????????????
            Message mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            AudioMsgBody audioMsgBody = new AudioMsgBody();
            audioMsgBody.setDuration(12);
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/media/728457826/60bc63bbe2fc9abaf5346a47/single_123456/voice/E269C330CA1268CF82C4D5BB0CD2B242.mp3");
            audioMsgBody.setExtra("????????????????????????????????????????????????????????????");
            audioMsgBody.setSize(12);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);

            mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            audioMsgBody = new AudioMsgBody();
            audioMsgBody.setDuration(2);
            audioMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/media/728457826/60bc63bbe2fc9abaf5346a47/single_123456/voice/E269C330CA1268CF82C4D5BB0CD2B242.mp3");
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setExtra("????????????????????????????????????");
            audioMsgBody.setSize(1);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);

            mAdapter.addData(0,mReceiveMsgList);


            mReceiveMsgList=new ArrayList<Message>();


            Message mMessgae=getBaseSendMessage(MsgType.AUDIO);
            AudioMsgBody mFileMsgBody=new AudioMsgBody();
            mMessgae=getBaseSendMessage(MsgType.AUDIO);
            mFileMsgBody=new AudioMsgBody();
            mFileMsgBody.setDuration(2);
            mFileMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/media/728457826/60bc63bbe2fc9abaf5346a47/single_123456/voice/E269C330CA1268CF82C4D5BB0CD2B242.mp3");
            mFileMsgBody.setName2("??????(??????????????????)");
            mFileMsgBody.setDisplayName("??????");
            mFileMsgBody.setExtra("????????????????????????????????????");
            mFileMsgBody.setSize(1);
            mFileMsgBody.setLocalPath("");
            mFileMsgBody.setDuration(2);
            mMessgae.setBody(mFileMsgBody);
            //????????????
            mAdapter.addData( mMessgae);
            //???????????????????????????
            updateMsg(mMessgae);

            mMessgae=getBaseSendMessage(MsgType.AUDIO);
            mFileMsgBody=new AudioMsgBody();
            mFileMsgBody.setDuration(2);
            mFileMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/media/728457826/60bc63bbe2fc9abaf5346a47/single_123456/voice/E269C330CA1268CF82C4D5BB0CD2B242.mp3");
            mFileMsgBody.setName2("??????(??????????????????)");
            mFileMsgBody.setDisplayName("??????");
            mFileMsgBody.setExtra("????????????????????????????????????");
            mFileMsgBody.setSize(1);
            mFileMsgBody.setLocalPath("");
            mFileMsgBody.setDuration(2);
            mMessgae.setBody(mFileMsgBody);
            //????????????
            mAdapter.addData( mMessgae);
            //???????????????????????????
            updateMsg(mMessgae);


            mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            audioMsgBody = new AudioMsgBody();
            audioMsgBody.setDuration(12);
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setExtra("????????????????????????????????????");
            audioMsgBody.setSize(1);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);
            mAdapter.addData(4,mReceiveMsgList);

        /*
        mMessgae=getBaseSendMessage(MsgType.AUDIO);
        mFileMsgBody=new AudioMsgBody();
        mFileMsgBody.setDuration(2);
        mFileMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/media/728457826/60bc63bbe2fc9abaf5346a47/single_123456/voice/E269C330CA1268CF82C4D5BB0CD2B242.mp3");
        mFileMsgBody.setName2("??????(??????????????????)");
        mFileMsgBody.setDisplayName("??????");
        mFileMsgBody.setExtra("????????????????????????????????????");
        mFileMsgBody.setSize(1);
        mFileMsgBody.setLocalPath("");
        mFileMsgBody.setDuration(2);
        mMessgae.setBody(mFileMsgBody);
        //????????????
        mAdapter.addData( mMessgae);
        //???????????????????????????
        updateMsg(mMessgae);

         */

            mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
            audioMsgBody = new AudioMsgBody();
            audioMsgBody.setDuration(5);
            audioMsgBody.setName2("??????(??????????????????)");
            audioMsgBody.setDisplayName("??????");
            audioMsgBody.setExtra("????????????????????????????????????????????????????????????");
            audioMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/voice_1618000204686.mp3");
            audioMsgBody.setSize(1);
            mMessageAudio.setBody(audioMsgBody);
            mReceiveMsgList.add(mMessageAudio);
            mAdapter.addData(5,mReceiveMsgList);
        /*
        final Message mMessgae=getBaseSendMessage(MsgType.AUDIO);
        AudioMsgBody mFileMsgBody=new AudioMsgBody();
        mFileMsgBody.setDuration(2);
        mFileMsgBody.setLocalPath("/data/user/0/com.example.aisearch/files/media/728457826/60bc63bbe2fc9abaf5346a47/single_123456/voice/E269C330CA1268CF82C4D5BB0CD2B242.mp3");
        mFileMsgBody.setName2("??????(??????????????????)");
        mFileMsgBody.setDisplayName("??????");
        mFileMsgBody.setExtra("????????????????????????????????????");
        mFileMsgBody.setSize(1);
        mFileMsgBody.setLocalPath("");
        mFileMsgBody.setDuration(2);
        mMessgae.setBody(mFileMsgBody);
        //????????????
        mAdapter.addData( mMessgae);
        //???????????????????????????
        updateMsg(mMessgae);

         */
        }

        private ImageView ivAudio;
        protected void initContent() {
            ButterKnife.bind(this) ;
            mAdapter=new ChatAdapter(this, new ArrayList<Message>());
            LinearLayoutManager mLinearLayout=new LinearLayoutManager(this);
            mRvChat.setLayoutManager(mLinearLayout);
            mRvChat.setAdapter(mAdapter);
            mSwipeRefresh.setOnRefreshListener(this);
            initChatUi();
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    int type = 1;
                    if(view.getId()==R.id.rlAudio){
                        view = mRvChat.getChildAt(position);
                    }else if(view.getId()==R.id.cv_trans_text){
                        view = mRvChat.getChildAt(position);
                        try {
                            if(view==null) view = adapter.getViewByPosition(mRvChat,position, R.id.ll_text_receive);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        System.out.println(view);
                        Log.e("position:",""+position);
                        type = 2;
                    }
                    System.out.println("type:"+type);
                    final  boolean isSend = mAdapter.getItem(position).getSenderId().equals(ChatActivity.mSenderId);
                    if (ivAudio != null) {
                        if (isSend){
                            ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_right_3);
                        }else {
                            ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_left_3);
                        }
                        ivAudio = null;
                        MediaManager.reset();
                    }else{
                        ivAudio = view.findViewById(R.id.ivAudio);
                        MediaManager.reset();
                        CardView cv = view.findViewById(R.id.cv_inditor);
                        CardView cvTransText = view.findViewById(R.id.cv_trans_text);
                        cv.setVisibility(View.INVISIBLE);
                        cvTransText.setVisibility(View.INVISIBLE);
                        if(type==2){
                            TextView tv_Contant = view.findViewById(R.id.tv_content);
                            tv_Contant.setVisibility(View.VISIBLE);
                            RelativeLayout relativeLayout = view.findViewById(R.id.rlAudio);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(relativeLayout.getLayoutParams());
                            String str = ((AudioMsgBody)mAdapter.getData().get(position).getBody()).getExtra();
                            if(str.length()*500/12>500)
                                params.width = 500;
                            else
                                params.width = str.length()*500/12;
                            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                            relativeLayout.setLayoutParams(params);
                        }
                        if (isSend){
                            ivAudio.setBackgroundResource(R.drawable.audio_animation_right_list);
                        }else {
                            ivAudio.setBackgroundResource(R.drawable.audio_animation_left_list);
                        }
                        AnimationDrawable drawable = (AnimationDrawable) ivAudio.getBackground();
                        drawable.start();
                        MediaManager.playSound(ChatActivity.this,((AudioMsgBody)mAdapter.getData().get(position).getBody()).getLocalPath(), new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                if (isSend){
                                    ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_right_3);
                                }else {
                                    ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_left_3);
                                }
                                MediaManager.release();
                            }
                        });
                    }
                }
            });

        }
        public void onEvent(MessageEvent event) {
            cn.jpush.im.android.api.model.Message msg = event.getMessage();

            System.out.println("text??????");
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run(){
                            switch (msg.getContentType()) {
                                case text:
                                    System.out.println("text??????");
                                    break;
                                case image:
                                    //??????????????????
                                    ImageContent imageContent = (ImageContent) msg.getContent();
                                    imageContent.getLocalPath();//??????????????????
                                    imageContent.getLocalThumbnailPath();//????????????????????????????????????
                                    break;
                                case voice:
                                    System.out.println("msg.getContentType()"+msg.getContentType());
                                    System.out.println("voice??????");
                                    //??????????????????
                                    VoiceContent voiceContent = (VoiceContent)msg.getContent();
                                    List<Message> mReceiveMsgList=new ArrayList<Message>();
                                    //??????????????????
                                    Message mMessageAudio=getBaseReceiveMessage(MsgType.AUDIO);
                                    AudioMsgBody audioMsgBody = new AudioMsgBody();
                                    audioMsgBody.setDuration(voiceContent.getDuration());
                                    audioMsgBody.setLocalPath(voiceContent.getLocalPath());
                                    System.out.println(voiceContent.getLocalPath());
                                    audioMsgBody.setName2("??????(??????????????????)");
                                    audioMsgBody.setDisplayName("??????");
                                    audioMsgBody.setExtra("????????????????????????????????????????????????????????????");
                                    audioMsgBody.setSize(12);
                                    mMessageAudio.setBody(audioMsgBody);
                                    mReceiveMsgList.add(mMessageAudio);
                                    mAdapter.addData(mAdapter.getItemCount(),mReceiveMsgList);
                                    break;
                                case custom:
                                    //?????????????????????
                                    CustomContent customContent = (CustomContent) msg.getContent();
                                    customContent.getNumberValue("custom_num"); //?????????????????????
                                    customContent.getBooleanValue("custom_boolean");
                                    customContent.getStringValue("custom_string");
                                    break;

                            }
                        }
                    }
            );

        }

        //???????????????????????????????????????OfflineMessageEvent?????????????????????????????????????????????
        //MessageEvent
        public void onEvent(OfflineMessageEvent event) {
            List<cn.jpush.im.android.api.model.Message> msgs = event.getOfflineMessageList();
            for (cn.jpush.im.android.api.model.Message msg:msgs) {
            }
        }

        @Override
        public void onRefresh() {
            //????????????????????????????????????
            List<Message> mReceiveMsgList=new ArrayList<Message>();
            //??????????????????
            Message mMessgaeText=getBaseReceiveMessage(MsgType.TEXT);
            TextMsgBody mTextMsgBody=new TextMsgBody();
            mTextMsgBody.setMessage("???????????????");
            mMessgaeText.setBody(mTextMsgBody);
            mReceiveMsgList.add(mMessgaeText);
            //??????????????????
            Message mMessgaeImage=getBaseReceiveMessage(MsgType.IMAGE);
            ImageMsgBody mImageMsgBody=new ImageMsgBody();
            mImageMsgBody.setThumbUrl("https://c-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.thumb.700_0.jpeg");
            mMessgaeImage.setBody(mImageMsgBody);
            mReceiveMsgList.add(mMessgaeImage);
            //??????????????????
            Message mMessgaeFile=getBaseReceiveMessage(MsgType.FILE);
            FileMsgBody mFileMsgBody=new FileMsgBody();
            mFileMsgBody.setDisplayName("???????????????");
            mFileMsgBody.setSize(12);
            mMessgaeFile.setBody(mFileMsgBody);
            mReceiveMsgList.add(mMessgaeFile);
            mAdapter.addData(0,mReceiveMsgList);
            mSwipeRefresh.setRefreshing(false);
        }




        private void initChatUi(){
            //mBtnAudio
            final ChatUiHelper mUiHelper= ChatUiHelper.with(this);
            mUiHelper.bindContentLayout(mLlContent)
                    .bindttToSendButton(mBtnSend)
                    .bindEditText(mEtContent)
                    .bindBottomLayout(mRlBottomLayout)
                    .bindEmojiLayout(mLlEmotion)
                    .bindAddLayout(mLlAdd)
                    .bindToAddButton(mIvAdd)
                    .bindToEmojiButton(mIvEmo)
                    .bindAudioBtn(mBtnAudio)
                    .bindAudioIv(mIvAudio)
                    .bindEmojiData();
            //??????????????????,??????????????????
            mRvChat.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (bottom < oldBottom) {
                        mRvChat.post(new Runnable() {
                            @Override
                            public void run() {
                                if (mAdapter.getItemCount() > 0) {
                                    mRvChat.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                                }
                            }
                        });
                    }
                }
            });
            //??????????????????????????????
            mRvChat.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    mUiHelper.hideBottomLayout(false);
                    mUiHelper.hideSoftInput();
                    mEtContent.clearFocus();
                    mIvEmo.setImageResource(R.mipmap.ic_emoji);
                    return false;
                }
            });
            //
            ((RecordButton) mBtnAudio).setOnFinishedRecordListener(new RecordButton.OnFinishedRecordListener() {
                @Override
                public void onFinishedRecord(String audioPath, int time) {
                    LogUtil.d("??????????????????");
                    File file = new File(audioPath);
                    if (file.exists()) {
                        sendAudioMessage(audioPath,time);
                        Log.e("path:",audioPath);
                        try {
                            cn.jpush.im.android.api.model.Message m;
                            if(mUserInfo.getUserName().equals("2408003640")){
                                m = JMessageClient.createSingleVoiceMessage("123456", "60bc63bbe2fc9abaf5346a47",file, time);
                                JMessageClient.sendMessage(m);
                            }else{
                                m = JMessageClient.createSingleVoiceMessage("2408003640", "60bc63bbe2fc9abaf5346a47",file, time);
                                JMessageClient.sendMessage(m);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

        }

        @OnClick({R.id.btn_send,R.id.rlPhoto,R.id.rlVideo,R.id.rlLocation,R.id.rlFile,R.id.tv_info2,R.id.rl_quanyuan})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.btn_send:
                    sendTextMsg(mEtContent.getText().toString());
                    mEtContent.setText("");
                    break;
                case R.id.rlPhoto:
                    PictureFileUtil.openGalleryPic(ChatActivity.this,REQUEST_CODE_IMAGE);
                    break;
                case R.id.rlVideo:
                    PictureFileUtil.openGalleryAudio(ChatActivity.this,REQUEST_CODE_VEDIO);
                    break;
                case R.id.rlFile:
                    PictureFileUtil.openFile(ChatActivity.this,REQUEST_CODE_FILE);
                    break;
                case R.id.rlLocation:
                    break;
                case R.id. tv_info2:
                    initContent();
                    initMesage2();
                    mAdapter.notifyDataSetChanged();
                    break;
                case R.id.rl_quanyuan:
                    initContent();
                    initMesage();
                    mAdapter.notifyDataSetChanged();

            }
        }





        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                switch (requestCode) {
                    case REQUEST_CODE_FILE:
                        String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                        LogUtil.d("????????????????????????:"+filePath);
                        sendFileMessage(mSenderId, mTargetId, filePath);
                        break;
                    case REQUEST_CODE_IMAGE:
                        // ????????????????????????
                        List<LocalMedia> selectListPic = PictureSelector.obtainMultipleResult(data);
                        for (LocalMedia media : selectListPic) {
                            LogUtil.d("????????????????????????:"+  media.getPath());
                            sendImageMessage(media);
                        }
                        break;
                    case REQUEST_CODE_VEDIO:
                        // ????????????????????????
                        List<LocalMedia> selectListVideo = PictureSelector.obtainMultipleResult(data);
                        for (LocalMedia media : selectListVideo) {
                            LogUtil.d("????????????????????????:"+  media.getPath());
                            sendVedioMessage(media);
                        }
                        break;
                }
            }
        }




        //????????????
        private void sendTextMsg(String hello)  {
            final Message mMessgae=getBaseSendMessage(MsgType.TEXT);
            TextMsgBody mTextMsgBody=new TextMsgBody();
            mTextMsgBody.setMessage(hello);
            mMessgae.setBody(mTextMsgBody);
            //????????????
            mAdapter.addData( mMessgae);
            //???????????????????????????
            updateMsg(mMessgae);
        }



        //????????????
        private void sendImageMessage(final LocalMedia media) {
            final Message mMessgae=getBaseSendMessage(MsgType.IMAGE);
            ImageMsgBody mImageMsgBody=new ImageMsgBody();
            mImageMsgBody.setThumbUrl(media.getCompressPath());
            mMessgae.setBody(mImageMsgBody);
            //????????????
            mAdapter.addData( mMessgae);
            //???????????????????????????
            updateMsg(mMessgae);
        }


        //????????????
        private void sendVedioMessage(final LocalMedia media) {
            final Message mMessgae=getBaseSendMessage(MsgType.VIDEO);
            //?????????????????????
            String vedioPath=media.getPath();
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(vedioPath);
            Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime();
            String imgname = System.currentTimeMillis() + ".jpg";
            String urlpath = Environment.getExternalStorageDirectory() + "/" + imgname;
            File f = new File(urlpath);
            try {
                if (f.exists()) {
                    f.delete();
                }
                FileOutputStream out = new FileOutputStream(f);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            }catch ( Exception e) {
                LogUtil.d("????????????????????????????????????"+e.toString());
                e.printStackTrace();
            }
            VideoMsgBody mImageMsgBody=new VideoMsgBody();
            mImageMsgBody.setExtra(urlpath);
            mMessgae.setBody(mImageMsgBody);
            //????????????
            mAdapter.addData( mMessgae);
            //???????????????????????????
            updateMsg(mMessgae);

        }

        //????????????
        private void sendFileMessage(String from, String to, final String path) {
            final Message mMessgae=getBaseSendMessage(MsgType.FILE);
            FileMsgBody mFileMsgBody=new FileMsgBody();
            mFileMsgBody.setLocalPath(path);
            mFileMsgBody.setDisplayName(FileUtils.getFileName(path));
            mFileMsgBody.setSize(FileUtils.getFileLength(path));
            mMessgae.setBody(mFileMsgBody);
            //????????????
            mAdapter.addData( mMessgae);
            //???????????????????????????
            updateMsg(mMessgae);

        }

        //????????????
        private void sendAudioMessage(final String path, int time) {
            final Message mMessgae=getBaseSendMessage(MsgType.AUDIO);
            AudioMsgBody mFileMsgBody=new AudioMsgBody();
            mFileMsgBody.setName2(user1.getName()+"(??????????????????)");
            mFileMsgBody.setDisplayName(user1.getName());
            mFileMsgBody.setLocalPath(path);
            mFileMsgBody.setDuration(time);
            mMessgae.setBody(mFileMsgBody);
            Log.e("path:",path);
            //????????????
            mAdapter.addData( mMessgae);
            //???????????????????????????
            updateMsg(mMessgae);
        }


        private Message getBaseSendMessage(MsgType msgType){
            Message mMessgae=new Message();
            mMessgae.setUuid(UUID.randomUUID()+"");
            mMessgae.setSenderId(mSenderId);
            mMessgae.setTargetId(mTargetId);
            mMessgae.setSentTime(System.currentTimeMillis());
            mMessgae.setSentStatus(MsgSendStatus.SENDING);
            mMessgae.setMsgType(msgType);
            return mMessgae;
        }


        private Message getBaseReceiveMessage(MsgType msgType){
            Message mMessgae=new Message();
            mMessgae.setUuid(UUID.randomUUID()+"");
            mMessgae.setSenderId(mTargetId);
            mMessgae.setTargetId(mSenderId);
            mMessgae.setSentTime(System.currentTimeMillis());
            mMessgae.setSentStatus(MsgSendStatus.SENDING);
            mMessgae.setMsgType(msgType);
            return mMessgae;
        }


        private void updateMsg(final Message mMessgae) {
            mRvChat.scrollToPosition(mAdapter.getItemCount() - 1);
            //??????2??????????????????
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    int position=0;
                    mMessgae.setSentStatus(MsgSendStatus.SENT);
                    //?????????????????????
                    for (int i=0;i<mAdapter.getData().size();i++){
                        Message mAdapterMessage=mAdapter.getData().get(i);
                        if (mMessgae.getUuid().equals(mAdapterMessage.getUuid())){
                            position=i;
                        }
                    }
                    mAdapter.notifyItemChanged(position);
                }
            }, 1);

        }


        private void requestPermisson(){
            RxPermissions rxPermission = new RxPermissions(this);
            rxPermission
                    .request(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,//????????????
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO
                    )
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                        }
                    });
        }


    }

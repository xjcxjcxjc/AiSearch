package im.sdk.debug.activity.createmessage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.aisearch.R;

import java.io.File;
import java.io.IOException;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.callback.ProgressUpdateCallback;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.content.FileContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.LocationContent;
import cn.jpush.im.android.api.content.PromptContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VideoContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

/**
 * Created by ${chenyn} on 16/4/6.
 *
 * @desc :
 */
public class ShowMessageActivity extends Activity {
    public static final String EXTRA_MSG_TYPE = "msg_type";

    public static final String EXTRA_IS_GROUP = "isGroup";

    public static final String EXTRA_FROM_USERNAME = "from_username";

    public static final String EXTRA_FROM_APPKEY = "from_appkey";

    public static final String EXTRA_GROUPID = "from_gid";

    public static final String EXTRA_MSGID = "msgid";

    private final String TAG = ShowMessageActivity.class.getSimpleName();
    private ImageView mIv_showImage;
    private TextView mTv_showText;
    private Button mPlay;
    private Button mDownload;

    private ContentType contentType;
    private Message message;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String msgTypeString = intent.getStringExtra(EXTRA_MSG_TYPE);
        contentType = ContentType.valueOf(msgTypeString);
        boolean isGroup = intent.getBooleanExtra(EXTRA_IS_GROUP, false);
        long gid = intent.getLongExtra(EXTRA_GROUPID, 0);
        String user = intent.getStringExtra(EXTRA_FROM_USERNAME);
        String appkey = intent.getStringExtra(EXTRA_FROM_APPKEY);
        int msgid = intent.getIntExtra(EXTRA_MSGID, 0);
        Conversation conversation;
        if (isGroup) {
            conversation = JMessageClient.getGroupConversation(gid);
            mTv_showText.append("?????????????????????" + gid + ",??????" + user + "?????????\n");
        } else {
            conversation = JMessageClient.getSingleConversation(user, appkey);
            mTv_showText.append("?????????????????????" + user + "?????????\n");
        }
        if (conversation == null) {
            Toast.makeText(getApplicationContext(), "???????????????null", Toast.LENGTH_SHORT).show();
            return;
        }
        message = conversation.getMessage(msgid);

        switch (contentType) {
            case text:
                TextContent textContent = (TextContent) message.getContent();
                mTv_showText.append("????????????" +
                        "\n???????????? = " + textContent.getText() + "\n???????????? = " + textContent.getStringExtras() +
                        "\n?????????isAtMe = " + message.isAtMe() + "\n?????????isAtAll = " + message.isAtAll() + "\n");
                break;
            case image:
                mIv_showImage.setVisibility(View.VISIBLE);
                mDownload.setVisibility(View.VISIBLE);
                //????????????????????????????????????sdk???????????????
                String thumbLocalPath = ((ImageContent) message.getContent()).getLocalThumbnailPath();
                if (!TextUtils.isEmpty(thumbLocalPath)) {
                    Bitmap bitmap = BitmapFactory.decodeFile(thumbLocalPath);
                    mIv_showImage.setImageBitmap(bitmap);
                }
                mTv_showText.append("?????????????????????????????????????????????????????????????????????");
                break;

            case voice:
                mPlay.setVisibility(View.VISIBLE);
                String voiceFilePath = ((VoiceContent) message.getContent()).getLocalPath();
                //???????????????????????????????????????sdk???????????????
                mTv_showText.append("???????????????????????? = " + voiceFilePath + "\n");
                break;
            case location:
                LocationContent locationContent = (LocationContent) message.getContent();
                mTv_showText.append("?????????????????????address = " + locationContent.getAddress() + "\nlatitude = " + locationContent.getLatitude() + "\nscale = " + locationContent.getScale() + "\nlongitude = " + locationContent.getLongitude());
                break;
            case file:
                mDownload.setVisibility(View.VISIBLE);
                message = conversation.getMessage(msgid);
                mTv_showText.append("??????????????????????????????????????????\n");
                break;
            case custom:
                CustomContent content = (CustomContent) message.getContent();
                mTv_showText.append("??????????????? \nall string values = " + content.getAllStringValues()
                        + "\nall number values = " + content.getAllNumberValues() + "\nall boolean values = " + content.getAllBooleanValues() + "\n");
                break;
            case eventNotification:
                EventNotificationContent eventNotificationContent = (EventNotificationContent) message.getContent();
                mTv_showText.append("??????" + gid + "?????????????????????????????? ???????????????" + eventNotificationContent.getEventText() + "\n");
                break;
            case prompt:
                PromptContent promptContent = (PromptContent) message.getContent();
                mTv_showText.append("????????????????????? ???????????????" + promptContent.getPromptText() + "\n");
                break;
            case video:
                mIv_showImage.setVisibility(View.VISIBLE);
                mDownload.setVisibility(View.VISIBLE);
                //??????????????????????????????????????????sdk????????????
                String videoThumbPath = ((VideoContent) message.getContent()).getThumbLocalPath();
                if (!TextUtils.isEmpty(videoThumbPath)) {
                    Bitmap bitmap = BitmapFactory.decodeFile(videoThumbPath);
                    mIv_showImage.setImageBitmap(bitmap);
                }
                mTv_showText.append("??????????????????????????????????????????????????????????????????????????????");
                break;
        }


        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (contentType) {
                    case voice:
                        MediaPlayer player = new MediaPlayer();
                        VoiceContent voiceContent = (VoiceContent) message.getContent();
                        String voicePath = voiceContent.getLocalPath();
                        try {
                            if (TextUtils.isEmpty(voicePath)) {
                                Toast.makeText(getApplicationContext(), "???????????????????????????", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            player.setDataSource(voicePath);
                            player.prepare();
                            player.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });

        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTv_showText.setText("");
                final ProgressDialog dialog = new ProgressDialog(ShowMessageActivity.this);
                dialog.setTitle("??????");
                dialog.setMessage("???????????????...");
                dialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelDownload();
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

                if (message != null) {
                    switch (contentType) {
                        case image:
                            ImageContent imageContent = (ImageContent) message.getContent();
                            imageContent.downloadOriginImage(message, new DownloadCompletionCallback() {
                                @Override
                                public void onComplete(int responseCode, String responseMessage, File file) {
                                    dialog.dismiss();
                                    if (responseCode == 0) {
                                        mTv_showText.append("?????????????????????????????????:" + file.getPath() + "\n");
                                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                                        Log.i("ShowMessageActivity", "downloadFile" + ", responseCode = " + responseCode + " ; Desc = " + responseMessage);
                                    }
                                }
                            });
                            break;
                        case file:
                            FileContent content = (FileContent) message.getContent();
                            message.setOnContentDownloadProgressCallback(new ProgressUpdateCallback() {
                                @Override
                                public void onProgressUpdate(double percent) {
                                    mTv_showText.append("????????????????????????:" + percent + "\n");
                                }
                            });
                            content.downloadFile(message, new DownloadCompletionCallback() {
                                @Override
                                public void onComplete(int i, String s, File file) {
                                    dialog.dismiss();
                                    if (i == 0) {
                                        mTv_showText.append("???????????????????????????:" + file.getPath() + "\n");
                                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                                    } else {
                                        mTv_showText.append("?????????????????????responseCode:" + i + " ; Desc = " + s);
                                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                                        Log.i("ShowMessageActivity", "downloadFile" + ", responseCode = " + i + " ; Desc = " + s);
                                    }
                                }
                            });
                            break;
                        case video:
                            VideoContent videoContent = (VideoContent) message.getContent();
                            videoContent.downloadVideoFile(message, new DownloadCompletionCallback() {
                                @Override
                                public void onComplete(int responseCode, String responseMessage, File file) {
                                    dialog.dismiss();
                                    if (responseCode == 0) {
                                        mTv_showText.append("?????????????????????????????????:" + file.getPath() + "\n");
                                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                                    } else {
                                        mTv_showText.append("???????????????????????????responseCode:" + responseCode + " ; Desc = " + responseCode);
                                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                                        Log.i("ShowMessageActivity", "downloadFile" + ", responseCode = " + responseCode + " ; Desc = " + responseMessage);
                                    }
                                }
                            });
                            break;
                    }
                } else {
                    Toast.makeText(ShowMessageActivity.this, "???????????????message??????", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cancelDownload() {
        if (message != null) {
            message.getContent().cancelDownload(message);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_show_message);
        mIv_showImage = (ImageView) findViewById(R.id.iv_show_image);
        mTv_showText = (TextView) findViewById(R.id.tv_show_text);
        mPlay = (Button) findViewById(R.id.play);
        mDownload = (Button) findViewById(R.id.download);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

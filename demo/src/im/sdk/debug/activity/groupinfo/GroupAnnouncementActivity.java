package im.sdk.debug.activity.groupinfo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aisearch.R;

import java.util.List;

import cn.jpush.im.android.ErrorCode;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetGroupInfoCallback;
import cn.jpush.im.android.api.callback.PublishAnnouncementCallback;
import cn.jpush.im.android.api.callback.RequestCallback;
import cn.jpush.im.android.api.model.GroupAnnouncement;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

public class GroupAnnouncementActivity extends Activity implements View.OnClickListener{
    private EditText mEtGroupId;
    private EditText mEtText;
    private EditText mEtSendMessage;
    private EditText mEtAnnounceID;
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        findViewById(R.id.bt_publish_group_announcement).setOnClickListener(this);
        findViewById(R.id.bt_del_group_announcement).setOnClickListener(this);
        findViewById(R.id.bt_set_top).setOnClickListener(this);
        findViewById(R.id.bt_cancel_top).setOnClickListener(this);
        findViewById(R.id.bt_get_announcements).setOnClickListener(this);
    }

    private void initView() {
        setContentView(R.layout.activity_group_announcement);
        mEtGroupId = (EditText) findViewById(R.id.et_gid);
        mEtText = (EditText) findViewById(R.id.et_text);
        mEtSendMessage = (EditText) findViewById(R.id.et_send_message);
        mEtAnnounceID = (EditText) findViewById(R.id.et_announce_id);
        mTvResult = (TextView) findViewById(R.id.tv_show_result);
    }

    @Override
    public void onClick(final View view) {
        try {
            long gid = Long.parseLong(mEtGroupId.getText().toString());
            JMessageClient.getGroupInfo(gid, new GetGroupInfoCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage, GroupInfo groupInfo) {
                    if (ErrorCode.NO_ERROR == responseCode) {
                        int id = view.getId();
                        if (id == R.id.bt_publish_group_announcement) {
                            publishGroupAnnouncement(groupInfo);
                        } else if (id == R.id.bt_del_group_announcement) {
                            delGroupAnnouncement(groupInfo);
                        } else if (id == R.id.bt_set_top) {
                            setTopAnnouncement(groupInfo, true);
                        } else if (id == R.id.bt_cancel_top) {
                            setTopAnnouncement(groupInfo, false);
                        } else if (id == R.id.bt_get_announcements) {
                            getGroupAnnouncements(groupInfo);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "?????????????????????ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void publishGroupAnnouncement(GroupInfo groupInfo) {
        if (!TextUtils.isEmpty(mEtText.getText())) {
            String text = mEtText.getText().toString();
            boolean needSendMessage = Boolean.valueOf(mEtSendMessage.getText().toString());
            groupInfo.publishGroupAnnouncement(text, needSendMessage, new PublishAnnouncementCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage, GroupAnnouncement announcement, Message message) {
                    if (ErrorCode.NO_ERROR == responseCode) {
                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                        mTvResult.setText("responseCode:" + responseCode + "\nresponseMessage:" + responseMessage);
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "?????????????????????", Toast.LENGTH_SHORT).show();
        }
    }

    private void delGroupAnnouncement(GroupInfo groupInfo) {
        try {
            int announceID = Integer.valueOf(mEtAnnounceID.getText().toString());
            groupInfo.delGroupAnnouncement(announceID, new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {
                    if (ErrorCode.NO_ERROR == responseCode) {
                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                        mTvResult.setText("responseCode:" + responseCode + "\nresponseMessage:" + responseMessage);
                    }
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "?????????????????????ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTopAnnouncement(GroupInfo groupInfo, final boolean isTop) {
        try {
            int announceID = Integer.valueOf(mEtAnnounceID.getText().toString());
            groupInfo.setTopAnnouncement(announceID, isTop, new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {
                    StringBuilder result = new StringBuilder();
                    result.append(isTop ? "??????" : "????????????");
                    if (ErrorCode.NO_ERROR == responseCode) {
                        result.append("??????");
                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        result.append("??????");
                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                        mTvResult.setText("responseCode:" + responseCode + "\nresponseMessage:" + responseMessage);
                    }
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "?????????????????????ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void getGroupAnnouncements(GroupInfo groupInfo) {
        groupInfo.getAnnouncementsByOrder(new RequestCallback<List<GroupAnnouncement>>() {
            @Override
            public void gotResult(int responseCode, String responseMessage, List<GroupAnnouncement> announcements) {
                if (ErrorCode.NO_ERROR == responseCode) {
                    StringBuilder result = new StringBuilder();
                    for (GroupAnnouncement announcement : announcements) {
                        result.append("??????ID:" + announcement.getAnnounceID() + "\n");
                        result.append("????????????:" + announcement.getText() + "\n");
                        result.append("??????????????????:" + announcement.getCtime() + "\n");
                        result.append("??????????????????:" + announcement.isTop() + "\n");
                        result.append("??????????????????:" + announcement.getTopTime() + "\n");
                        result.append("???????????????(username):" + announcement.getPublisher().getUserName() + "\n\n");
                    }
                    Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                    mTvResult.setText(result.toString());
                } else {
                    Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                    mTvResult.setText("responseCode:" + responseCode + "\nresponseMessage:" + responseMessage);
                }
            }
        });
    }
}

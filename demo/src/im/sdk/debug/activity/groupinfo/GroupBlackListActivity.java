package im.sdk.debug.activity.groupinfo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aisearch.R;

import java.util.Collections;
import java.util.List;

import cn.jpush.im.android.ErrorCode;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetGroupInfoCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.callback.RequestCallback;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class GroupBlackListActivity extends Activity implements View.OnClickListener {
    private EditText mEtGroupID;
    private EditText mEtUserName;
    private EditText mEtUserAppKey;
    private TextView mTvShowResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_del).setOnClickListener(this);
        findViewById(R.id.bt_get).setOnClickListener(this);
    }

    private void initView() {
        setContentView(R.layout.activity_group_black_list);
        mEtGroupID = (EditText) findViewById(R.id.et_group_id);
        mEtUserName = (EditText) findViewById(R.id.et_user_name);
        mEtUserAppKey = (EditText) findViewById(R.id.et_user_appkey);
        mTvShowResult = (TextView) findViewById(R.id.tv__show_result);;
    }

    @Override
    public void onClick(final View v) {
        try {
            long gid = Long.parseLong(mEtGroupID.getText().toString());
            JMessageClient.getGroupInfo(gid, new GetGroupInfoCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage, GroupInfo groupInfo) {
                    if (ErrorCode.NO_ERROR == responseCode) {
                        int id = v.getId();
                        if (id == R.id.bt_add) {
                            operateGroupBlackList(groupInfo, true);
                        } else if (id == R.id.bt_del) {
                            operateGroupBlackList(groupInfo, false);
                        } else if (id == R.id.bt_get) {
                            getGroupBlackList(groupInfo);
                        }
                    }
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "?????????????????????ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void getGroupBlackList(GroupInfo groupInfo) {
        groupInfo.getGroupBlackList(new RequestCallback<List<UserInfo>>() {
            @Override
            public void gotResult(int responseCode, String responseMessage, List<UserInfo> result) {
                if (ErrorCode.NO_ERROR == responseCode) {
                    Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                    StringBuilder builder = new StringBuilder();
                    if (result.size() > 0) {
                        builder.append("???????????????:\n");
                        for (UserInfo userInfo : result) {
                            builder.append("?????????:").append(userInfo.getUserName()).append("\n");
                            builder.append("appKey:").append(userInfo.getAppKey()).append("\n\n");
                        }
                    } else {
                        builder.append("????????????????????????");
                    }
                    mTvShowResult.setText(builder.toString());
                } else {
                    Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                    mTvShowResult.setText("????????????????????????:\n" + "responseCode:" + responseCode + "\nresponseMessage:" + responseMessage);
                }
            }
        });
    }

    private void operateGroupBlackList(final GroupInfo groupInfo, final boolean isAdd) {
        if (!TextUtils.isEmpty(mEtUserName.getText())) {
            String userName = mEtUserName.getText().toString();
            String appKey = TextUtils.isEmpty(mEtUserAppKey.getText()) ? null : mEtUserAppKey.getText().toString();
            JMessageClient.getUserInfo(userName, appKey, new GetUserInfoCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                    if (ErrorCode.NO_ERROR == responseCode) {
                        if (isAdd) {
                            groupInfo.addGroupBlacklist(Collections.singletonList(info), new BasicCallback() {
                                @Override
                                public void gotResult(int responseCode, String responseMessage) {
                                    if (ErrorCode.NO_ERROR == responseCode) {
                                        Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                                        mTvShowResult.setText("??????????????????????????????:\n" + "responseCode:" + responseCode + "\nresponseMessage:" + responseMessage);
                                    }
                                }
                            });
                        } else {
                            groupInfo.delGroupBlacklist(Collections.singletonList(info), new BasicCallback() {
                                @Override
                                public void gotResult(int responseCode, String responseMessage) {
                                    if (ErrorCode.NO_ERROR == responseCode) {
                                        Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                                        mTvShowResult.setText("????????????????????????????????????:\n" + "responseCode:" + responseCode + "\nresponseMessage:" + responseMessage);
                                    }
                                }
                            });
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "????????????????????????", Toast.LENGTH_SHORT).show();
                        mTvShowResult.setText("????????????????????????:\n" + "responseCode:" + responseCode + "\nresponseMessage:" + responseMessage);
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
        }
    }
}

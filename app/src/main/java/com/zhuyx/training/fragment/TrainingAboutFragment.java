package com.zhuyx.training.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseFragment;
import com.zhuyx.training.util.TrainingConstants;
import com.zhuyx.training.util.TrainingUtils;
import com.zhuyx.training.widget.TrainingAboutItemView;

/**
 * 关于界面
 * Created by Administrator on 2016/12/18.
 */

public class TrainingAboutFragment extends TrainingBaseFragment {
    private Toolbar toolbar;
    private TrainingAboutItemView telView, qqView, weChatView, emailView;

    @Override
    public int getLayoutId() {
        return R.layout.training_f_about;
    }

    @Override
    public void initView(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        telView = (TrainingAboutItemView) view.findViewById(R.id.training_tel);
        qqView = (TrainingAboutItemView) view.findViewById(R.id.training_qq);
        weChatView = (TrainingAboutItemView) view.findViewById(R.id.training_we_chat);
        emailView = (TrainingAboutItemView) view.findViewById(R.id.training_email);
        telView.setContentAutoLink(true, TrainingConstants.LINK_TYPE_TEL);
        qqView.setContentAutoLink(true,"qq");
        qqView.setContentViewTextColor(mResources.getColor(R.color.colorPrimary));
        weChatView.setContentAutoLink(false,"");
        emailView.setContentAutoLink(true,TrainingConstants.LINK_TYPE_EMAIL);
        TrainingUtils.setTitlePaddingView(view.findViewById(R.id.padding_view));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

        toolbar.setNavigationOnClickListener(v -> {
            getActivity().finish();
        });
        telView.setOnLinkClickListener((view) -> {
            skipActivity(Intent.ACTION_CALL);
        });
        qqView.setOnLinkClickListener((view) -> {
            skipActivity(Intent.ACTION_VIEW);
        });
        emailView.setOnLinkClickListener((view) -> {
            skipActivity(Intent.ACTION_SENDTO);
        });
    }

    /**
     * 在Android中，调用Email有三种类型的Intent：
     * Intent.ACTION_SENDTO 无附件的发送
     * Intent.ACTION_SEND 带附件的发送
     * Intent.ACTION_SEND_MULTIPLE 带有多附件的发送
     */
    private void skipActivity(String action) {
        Intent intent = new Intent();
        if (TextUtils.equals(action, Intent.ACTION_CALL)) {
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + telView.getContent()));
        } else if (TextUtils.equals(action, Intent.ACTION_VIEW)) {
            try {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin="+qqView.getContent()));
            } catch (Exception e) {
                e.printStackTrace();
                TrainingUtils.showMsg(toolbar, "请检查是否安装了QQ");
            }
        } else if (TextUtils.equals(action, Intent.ACTION_SENDTO)) {
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + emailView.getContent()));
            intent.putExtra(Intent.EXTRA_SUBJECT, "标题");
            intent.putExtra(Intent.EXTRA_TEXT, "内容");
        }
        getActivity().startActivity(intent);
    }
}

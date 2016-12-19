package com.zhuyx.training.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseWidget;
import com.zhuyx.training.util.TrainingConstants;

/**
 * 关于界面的item
 * Created by Administrator on 2016/12/18.
 */

public class TrainingAboutItemView extends TrainingBaseWidget {
    private String title, content;
    private boolean isShowUnderline;
    private TextView tvContent;
    private OnLinkClickListener listener;

    public TrainingAboutItemView(Context context) {
        super(context);
    }

    public TrainingAboutItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TrainingAboutItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnLinkClickListener(OnLinkClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void initTypedArray(Context context, AttributeSet attrs) {
        super.initTypedArray(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TrainingAboutItemView);
        title = array.getString(R.styleable.TrainingAboutItemView_aboutTitle);
        content = array.getString(R.styleable.TrainingAboutItemView_aboutContent);
        isShowUnderline = array.getBoolean(R.styleable.TrainingAboutItemView_aboutUnderlineShow, true);
        array.recycle();
    }

    @Override
    public void initView(Context context) {
        super.initView(context);
        View view = LayoutInflater.from(context).inflate(R.layout.training_about_item_view, this);
        TextView tvTitle = (TextView) view.findViewById(R.id.training_title);
        tvContent = (TextView) view.findViewById(R.id.training_content);
        View underline = view.findViewById(R.id.training_underline);
        tvTitle.setText(TextUtils.isEmpty(title) ? "" : title);
        tvContent.setText(TextUtils.isEmpty(content) ? "" : content);
        underline.setVisibility(isShowUnderline ? VISIBLE : GONE);
        tvContent.setOnClickListener(v -> {
            listener.onLinkClick(v);
        });
    }

    /**
     * 设置为链接
     */
    public void setContentAutoLink(boolean autoLink,String linkType) {
        if (autoLink) {
            //以html显示超链接，必须写全url。以setAutoLinkMask(Linkify.ALL)可以不用不用写全，就能自动识别出来。
            //setAutoLinkMask不仅 识别超链接，包括电话号码之类的。
            if (TextUtils.equals(linkType, TrainingConstants.LINK_TYPE_TEL)) {
                tvContent.setAutoLinkMask(Linkify.PHONE_NUMBERS);
            } else if (TextUtils.equals(linkType, TrainingConstants.LINK_TYPE_EMAIL)) {
                tvContent.setAutoLinkMask(Linkify.EMAIL_ADDRESSES);
            }
            //设置之后才会跳转
            tvContent.setMovementMethod(LinkMovementMethod.getInstance());
            tvContent.setLinkTextColor(mResources.getColor(R.color.colorPrimary));
        } else {
            tvContent.setEnabled(false);
            tvContent.setLinkTextColor(mResources.getColor(R.color.color_4d4c59));
            tvContent.setMovementMethod(null);
            tvContent.setAutoLinkMask(0);
        }
    }

    public String getContent() {
        String content = tvContent.getText().toString();
        return TextUtils.isEmpty(content) ? "" : content;
    }

    public interface OnLinkClickListener {
        /**
         * 链接的点击事件
         */
        void onLinkClick(View view);
    }

    /**
     * 摘自http://www.jb51.net/article/38158.htm
     * 发送邮件中，有收件者，抄送者，密送者。 也就是分别通过
     * Intent.EXTRA_EMAIL,
     * Intent.EXTRA_CC,
     * Intent.EXTRA_BCC
     * 来进行putExtra来设定的，而单个附件的发送，则使用Intent.EXTRA_STREAM来设置附件的地址Uri。
     * 发送多个附件，最主要的时候，通过putParcelableArrayListExtra将多个附件的Uri地址List设置进去就OK了。
     */
    public void setContentViewTextColor(int color) {
        tvContent.setTextColor(color);
    }

}

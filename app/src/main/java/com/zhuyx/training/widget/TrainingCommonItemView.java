package com.zhuyx.training.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseWidget;

/**
 * item公共类
 * Created by zhuyingxin on 2016/11/10.
 * email : rixtdqqq_2015@163.com
 */

public class TrainingCommonItemView extends TrainingBaseWidget {
    private String title, rightContent, underContent;
    private int leftImageId, rightImageId;
    private TextView tvTitle, tvUnderContent, tvRightContent;
    private ImageView rightImage;
    private OnRightImageClickListener mListener;
    /**
     * isShowUnderContent展示下面的内容
     * isShowUnderline显示下划线
     */
    private boolean isShowUnderContent, isShowUnderline;

    public TrainingCommonItemView(Context context) {
        super(context);
    }

    public TrainingCommonItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TrainingCommonItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initTypedArray(Context context, AttributeSet attrs) {
        super.initTypedArray(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TrainingCommonItemView);
        title = typedArray.getString(R.styleable.TrainingCommonItemView_commonTitle);
        rightContent = typedArray.getString(R.styleable.TrainingCommonItemView_commonContentRight);
        underContent = typedArray.getString(R.styleable.TrainingCommonItemView_commonContentUnder);
        leftImageId = typedArray.getResourceId(R.styleable.TrainingCommonItemView_commonLeftImage, 0);
        rightImageId = typedArray.getResourceId(R.styleable.TrainingCommonItemView_commonRightImage, 0);
        isShowUnderline = typedArray.getBoolean(R.styleable.TrainingCommonItemView_commonUnderlineShow, true);
        isShowUnderContent = typedArray.getBoolean(R.styleable.TrainingCommonItemView_commonContentShow, false);
        typedArray.recycle();
    }

    @Override
    public void initView(Context context) {
        super.initView(context);
        View rootView = LayoutInflater.from(context).inflate(R.layout.common_item_view, this);
        tvTitle = (TextView) rootView.findViewById(R.id.training_title);//标题
        tvRightContent = (TextView) rootView.findViewById(R.id.training_content_right);//标题右边的内容
        View underline = rootView.findViewById(R.id.training_item_view_underline);//下划线
        tvUnderContent = (TextView) rootView.findViewById(R.id.training_content_under);//下方的内容
        rightImage = (ImageView) rootView.findViewById(R.id.training_item_view_right_iv);//右边的图片
        tvUnderContent.setVisibility(isShowUnderContent ? VISIBLE : GONE);
        underline.setVisibility(isShowUnderline ? VISIBLE : GONE);

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(rightContent)) {
            tvRightContent.setText(rightContent);
        }
        if (!TextUtils.isEmpty(underContent) && isShowUnderContent) {
            tvUnderContent.setText(underContent);
        }
        if (leftImageId > 0) {
            tvTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(leftImageId, 0, 0, 0);
        }
        if (rightImageId > 0) {
            tvTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, rightImageId, 0);
        }
        rightImage.setOnClickListener((view) -> mListener.onRightImageClick(this));
    }

    /**
     * 设置右边的图片
     */
    public void setRightImageResource(int resId) {
        rightImage.setImageResource(resId > 0 ? resId : 0);
    }

    /**
     * 设置右边的图片
     */
    public void setRightImageResource(Drawable drawable, boolean isShowImageView) {
        if (isShowImageView) {
            rightImage.setVisibility(VISIBLE);
            rightImage.setImageDrawable((null == drawable) ? mResources.getDrawable(R.mipmap.weixin_icon) : drawable);
        }
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        tvTitle.setText(TextUtils.isEmpty(title) ? "" : title);
    }

    /**
     * 设置标题右边的内容
     */
    public void setRightContent(String rightContent, boolean isShowRightContent) {
        if (isShowRightContent) {
            tvRightContent.setVisibility(VISIBLE);
            tvRightContent.setText(TextUtils.isEmpty(rightContent) ? "" : rightContent);
        }
    }

    /**
     * 设置标题下方的内容
     */
    public void setUnderContent(String underContent, boolean isShowUnderContent) {
        tvUnderContent.setText((!TextUtils.isEmpty(underContent) && isShowUnderContent) ? underContent : "");
    }

    /**
     * 右边的图片点击事件接口
     */
    public void setOnRightImageClickListener(OnRightImageClickListener listener) {
        mListener = listener;
    }


    public interface OnRightImageClickListener {
        /**
         * 右边的图片点击事件
         */
        void onRightImageClick(TrainingCommonItemView view);
    }
}

package com.zhuyx.training.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuyx.training.R;

/**
 * topBar
 * Created by zhuyingxin on 2016/7/16.
 * email : rixtdqqq_2015@163.com
 */
public class CommonTopBar extends LinearLayout implements View.OnClickListener {
    /**
     * 标题控件
     */
    private TextView tvTitle;
    /**
     * 左边的控件
     */
    private TextView tvBack;
    /**
     * 从右向左的第一个控件
     */
    private TextView tvRight;
    /**
     * 从右向左的第二个控件
     */
    private TextView tvRight2;

    private Context mContext;

    /**
     * 当android手机的版本高于4.4时显示.需要在manifest.xml设置theme隐藏android自带的标题
     */
    private TextView tvGone;

    public CommonTopBar(Context context) {
        this(context, null);
    }

    public CommonTopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews(context);
    }

    private void initViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_top_bar, this);
        tvGone = (TextView) view.findViewById(R.id.top_bar_high_version_tv);
        tvTitle = (TextView) view.findViewById(R.id.top_bar_center_tv);
        tvBack = (TextView) view.findViewById(R.id.top_bar_back_tv);
        tvRight = (TextView) view.findViewById(R.id.top_bar_right_tv);
        tvRight2 = (TextView) view.findViewById(R.id.top_bar_right2_tv);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            tvGone.setVisibility(VISIBLE);
        }
        initListeners();
    }

    private void initListeners() {
        tvBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvRight2.setOnClickListener(this);
    }

    /**
     * 设置标题
     */
    public void setCenterView(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置标题
     */
    public void setCenterView(int resId) {
        if (resId > 0) {
            tvTitle.setText(mContext.getResources().getString(resId));
        }
    }


    /**
     * 设置左边按钮的图标
     */
    public void setLeftView(int resId) {
        setTextViewDrawableLeft(tvBack, resId);
    }

    /**
     * 设置左边按钮的隐藏显示
     */
    public void setLeftViewVisible(boolean isVisible) {
        tvBack.setVisibility(isVisible ? VISIBLE : GONE);
    }

    /**
     * 设置左边按钮的文本
     */
    public void setLeftView(String text) {
        tvBack.setText(text);
    }

    /**
     * 设置从右向左边第一个按钮的图标
     */
    public void setRightView(int resId) {
        setTextViewDrawableLeft(tvRight, resId);
    }

    /**
     * 设置从右向左边第一个按钮的文本
     */
    public void setRightView(String text) {
        tvRight.setText(text);
    }


    /**
     * 设置从右向左边第二个按钮的图标
     */
    public void setRight2View(int resId) {
        setTextViewDrawableLeft(tvRight2, resId);
    }

    /**
     * 设置textView的drawLeft
     */
    private void setTextViewDrawableLeft(TextView tv, int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (resId > 0) {
            tv.setCompoundDrawables(drawable, null, null, null);
        }
    }

    /**
     * 设置从右向左边第二个按钮的文本
     */
    public void setRight2View(String text) {
        tvRight2.setText(text);
    }


    /**
     * 设置从右向左边第一个按钮的图标的隐藏显示
     */
    public void setRightViewVisible(boolean isVisible) {
        tvRight.setVisibility(isVisible ? VISIBLE : GONE);
    }

    /**
     * 设置从右向左边第二个按钮的图标的隐藏显示
     */
    public void setRight2ViewVisible(boolean isVisible) {
        tvRight2.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.top_bar_back_tv == id) {
            if (null != mListener){
                mListener.onClickLeftView();
            }
        } else if (R.id.top_bar_right_tv == id) {
            if (null != mListener){
                mListener.onClickRightView();
            }
        } else if (R.id.top_bar_right2_tv == id) {
            if (null != mListener){
                mListener.onClickRight2View();
            }
        }
    }


    public interface onTopBarClickListener {
        /**
         * 点击左边的按钮的监听事件
         */
        void onClickLeftView();

        /**
         * 点击从右向左第一个按钮的监听事件
         */
        void onClickRightView();

        /**
         * 点击从右向左第二个按钮的监听事件
         */
        void onClickRight2View();
    }

    private onTopBarClickListener mListener;

    public void setOnTopBarClickListener(onTopBarClickListener listener) {
        mListener = listener;
    }
}

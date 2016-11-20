package com.zhuyx.training.base;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by zhuyingxin on 2016/9/26.
 * email : rixtdqqq_2015@163.com
 */

public class TrainingBaseWidget extends LinearLayout {

    public Context mContext;
    public Resources mResources;
    public TrainingBaseWidget(Context context) {
        super(context);
        mContext = context;
        mResources = context.getResources();
        initTypedArray(context, null);
        initView(context);
    }

    public TrainingBaseWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mResources = context.getResources();
        initTypedArray(context, attrs);
        initView(context);
    }


    public TrainingBaseWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mResources = context.getResources();
        initTypedArray(context, attrs);
        initView(context);
    }

    /**
     * 初始化自定义属性
     */
    public void initTypedArray(Context context, AttributeSet attrs) {

    }

    /**
     * 初始化控件
     */
    public void initView(Context context) {

    }
}

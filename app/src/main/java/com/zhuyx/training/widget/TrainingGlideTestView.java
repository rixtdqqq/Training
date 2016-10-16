package com.zhuyx.training.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseWidget;

/**
 * Created by zhuyingxin on 2016/10/16.
 * email : rixtdqqq_2015@163.com
 */

public class TrainingGlideTestView extends TrainingBaseWidget {
    private ImageView ivGlide6;

    public TrainingGlideTestView(Context context) {
        super(context);
    }

    public TrainingGlideTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TrainingGlideTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initTypedArray(Context context, AttributeSet attrs) {
        super.initTypedArray(context, attrs);
    }

    @Override
    public void initView(Context context) {
        super.initView(context);
        View view = LayoutInflater.from(context).inflate(R.layout.training_glide_test_view, this);
        ivGlide6 = (ImageView) view.findViewById(R.id.iv_glide6);
    }

    public void setImageResource(Drawable drawable) {
        if (null != drawable) {
            ivGlide6.setImageDrawable(drawable);
        }
    }
}

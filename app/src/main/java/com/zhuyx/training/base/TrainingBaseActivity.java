package com.zhuyx.training.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhuyingxin on 2016/9/26.
 * email : rixtdqqq_2015@163.com
 */

public abstract class TrainingBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initData();
        initView();
    }

    /**
     * 设置数据
     */
    public abstract void initData();

    /**
     * 设置布局id
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
    public abstract void initView();
}

package com.zhuyx.training.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhuyingxin on 2016/9/26.
 * email : rixtdqqq_2015@163.com
 */

public abstract class TrainingBaseFragment extends Fragment {
    protected Resources mResources;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mResources = context.getResources();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    /**
     * 获取布局id
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
    public abstract void initView(View view);

    /**
     * 设置初始化数据
     */
    public abstract void initData();
}

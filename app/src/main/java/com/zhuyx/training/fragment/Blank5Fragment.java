package com.zhuyx.training.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseFragment;

public class Blank5Fragment extends TrainingBaseFragment {
    TextView tv;

    public Blank5Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    @Override
    public int getLayoutId() {
        return R.layout.training_f_blank5;
    }

    @Override
    public void initView(View view) {
         tv= (TextView) view.findViewById(R.id.lambda_test);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

    }

    private void initListener() {
        tv.setOnClickListener((view) -> toast());
    }
    private void toast() {
        Toast.makeText(getActivity(), "lambda表达式", Toast.LENGTH_SHORT).show();
    }
}


package com.zhuyx.training.fragment;


import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseFragment;
import com.zhuyx.training.util.TrainingUtils;

/**
 * 工具界面
 */
public class TrainingToolsFragment extends TrainingBaseFragment {
    private Toolbar toolbar;

    public TrainingToolsFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.training_f_tools;
    }

    @Override
    public void initView(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TrainingUtils.setTitlePaddingView(view.findViewById(R.id.padding_view));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {
        toolbar.setNavigationOnClickListener((view) -> {
            getActivity().finish();
        });
    }

}

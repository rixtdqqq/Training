package com.zhuyx.training.fragment;


import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseFragment;
import com.zhuyx.training.util.TrainingUtils;

/**
 * 收藏界面
 */
public class TrainingCollectionFragment extends TrainingBaseFragment {
    private Toolbar toolbar;

    public TrainingCollectionFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.training_f_collection;
    }

    @Override
    public void initView(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TrainingUtils.setTitlePaddingView(view.findViewById(R.id.padding_view));
    }

    @Override
    public void initData() {
        String result = "{\"msg\":\"成功有数据\",\"status\":\"1\",\"data\":[{\"id\":\"1\",\"name\":\"zhu\"},{\"id\":\"1\",\"name\":\"ying\"},{\"id\":\"1\",\"name\":\"xin\"},{\"id\":\"2\",\"name\":\"shi\"},{\"id\":\"3\",\"name\":\"jie\"}]}";
        TrainingUtils.dealResult(getActivity(), result);
    }

    @Override
    public void initListeners() {
        toolbar.setNavigationOnClickListener((view) -> {
            getActivity().finish();
        });
    }

}

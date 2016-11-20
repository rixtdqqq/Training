package com.zhuyx.training.fragment;


import android.view.View;
import android.widget.Button;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseFragment;
import com.zhuyx.training.entity.TrainingEventEntity;
import com.zhuyx.training.widget.CommonTopBar;
import com.zhuyx.training.widget.TrainingCommonItemView;

import org.greenrobot.eventbus.EventBus;

public class Blank7Fragment extends TrainingBaseFragment implements CommonTopBar.onTopBarClickListener {


    public Blank7Fragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.training_f_blank7;
    }

    @Override
    public void initView(View rootView) {
        Button btnBack2Main = (Button) rootView.findViewById(R.id.btn);
        CommonTopBar topBar = (CommonTopBar) rootView.findViewById(R.id.common_top_bar);
        TrainingCommonItemView itemView = (TrainingCommonItemView) rootView.findViewById(R.id.training_content_description);
        itemView.setRightImageResource(mResources.getDrawable(android.R.drawable.arrow_down_float),true);
        itemView.setOnRightImageClickListener((view) -> view.setRightContent("右边描述的内容",true));
        handleTopBar(topBar);
        btnBack2Main.setOnClickListener((view) -> EventBus.getDefault().post(new TrainingEventEntity("msg")));

    }

    /**
     * 初始化 topBar
     */
    private void handleTopBar(CommonTopBar topBar) {
        topBar.setLeftView(R.mipmap.back);
        topBar.setCenterView("blank7");
        topBar.setOnTopBarClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClickLeftView() {
        getActivity().finish();
    }

    @Override
    public void onClickRightView() {

    }

    @Override
    public void onClickRight2View() {

    }
}

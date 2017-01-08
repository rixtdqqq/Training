package com.zhuyx.training.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseActivity;
import com.zhuyx.training.base.TrainingBaseFragment;
import com.zhuyx.training.fragment.TrainingAboutFragment;
import com.zhuyx.training.fragment.Blank7Fragment;
import com.zhuyx.training.fragment.TrainingToolsFragment;
import com.zhuyx.training.fragment.TrainingCollectionFragment;
import com.zhuyx.training.fragment.TrainingPositionFragment;
import com.zhuyx.training.util.TrainingConstants;

public class TrainingEventActivity extends TrainingBaseActivity {

    private TrainingBaseFragment mFragment;
    private String fragmentFlag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        fragmentFlag = intent.getStringExtra(TrainingConstants.FRAGMENT_FLAG);
    }

    @Override
    public int getLayoutId() {
        return R.layout.training_a_event;
    }

    @Override
    public void initView() {
    }

    @Override
    public void loadFragment() {
        if (TextUtils.equals(fragmentFlag, "Blank7Fragment")) {
            if (null == mFragment) {
                mFragment = new Blank7Fragment();
            }
        } else if (TextUtils.equals(fragmentFlag, TrainingConstants.TRAINING_TOOLS_FRAGMENT)) {//工具界面
            if (null == mFragment) {
                mFragment = new TrainingToolsFragment();
            }
        } else if (TextUtils.equals(fragmentFlag, TrainingConstants.TRAINING_COLLECTION_FRAGMENT)) {//收藏界面
            if (null == mFragment) {
                mFragment = new TrainingCollectionFragment();
            }
        } else if (TextUtils.equals(fragmentFlag, TrainingConstants.TRAINING_ABOUT_FRAGMENT)) {//关于界面
            if (null == mFragment) {
                mFragment = new TrainingAboutFragment();
            }
        } else if (TextUtils.equals(fragmentFlag, TrainingConstants.TRAINING_POSITION_FRAGMENT)) {//定位界面
            if (null == mFragment) {
                mFragment = new TrainingPositionFragment();
            }
        } else {
            if (null == mFragment) {
                mFragment = new TrainingAboutFragment();
            }
        }
        changeFragment(mFragment);
    }

    /**
     * 加载  fragment
     */
    private void changeFragment(TrainingBaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.training_content, fragment);
        transaction.commit();
    }
}

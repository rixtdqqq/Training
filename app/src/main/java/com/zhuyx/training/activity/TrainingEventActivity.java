package com.zhuyx.training.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseActivity;
import com.zhuyx.training.base.TrainingBaseFragment;
import com.zhuyx.training.fragment.Blank7Fragment;
import com.zhuyx.training.fragment.Blank8Fragment;
import com.zhuyx.training.fragment.Blank9Fragment;
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
        } else if (TextUtils.equals(fragmentFlag, "Blank8Fragment")) {
            if (null == mFragment) {
                mFragment = new Blank8Fragment();
            }
        } else if (TextUtils.equals(fragmentFlag, "Blank9Fragment")) {
            if (null == mFragment) {
                mFragment = new Blank9Fragment();
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

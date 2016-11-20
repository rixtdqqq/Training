package com.zhuyx.training.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.zhuyx.training.R;
import com.zhuyx.training.adapter.MyFragmentPagerAdapter;
import com.zhuyx.training.base.TrainingBaseActivity;
import com.zhuyx.training.fragment.Blank1Fragment;
import com.zhuyx.training.fragment.Blank2Fragment;
import com.zhuyx.training.fragment.Blank3Fragment;
import com.zhuyx.training.fragment.Blank4Fragment;
import com.zhuyx.training.fragment.Blank5Fragment;
import com.zhuyx.training.fragment.Blank6Fragment;
import com.zhuyx.training.widget.CommonTopBar;

import java.util.ArrayList;
import java.util.List;

public class TrainingMainActivity extends TrainingBaseActivity implements CommonTopBar.onTopBarClickListener {

    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles;
    private long time;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.training_a_main;
    }

    @Override
    public void initData() {
        fragments.add(new Blank1Fragment());
        fragments.add(new Blank2Fragment());
        fragments.add(new Blank3Fragment());
        fragments.add(new Blank4Fragment());
        fragments.add(new Blank5Fragment());
        fragments.add(new Blank6Fragment());
        titles = new String[]{"测试dataBinding", "测试Glide", "碎片3", "碎片4", "碎片5", "碎片6"};

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles));
        viewPager.setOffscreenPageLimit(6);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        CommonTopBar topBar = (CommonTopBar) findViewById(R.id.common_top_bar);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        handleTopBar(topBar);
    }

    private void handleTopBar(CommonTopBar topBar) {
        topBar.setCenterView("主界面");
        topBar.setLeftView(R.mipmap.back);
        topBar.setOnTopBarClickListener(this);
    }

    @Override
    public void loadFragment() {

    }

    @Override
    public void onClickLeftView() {
        long exitTime = System.currentTimeMillis();
        if (exitTime - time < 2000) {
            this.finish();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            time = exitTime;
        }
    }

    @Override
    public void onClickRightView() {

    }

    @Override
    public void onClickRight2View() {

    }

    @Override
    public void onBackPressed() {
        exitApplication();
    }

    /**
     * 退出程序
     */
    private void exitApplication() {
        long exitTime = System.currentTimeMillis();
        if (exitTime - time < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            time = exitTime;
        }
    }
}

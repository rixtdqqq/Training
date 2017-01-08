package com.zhuyx.training.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
import com.zhuyx.training.util.TrainingConstants;
import com.zhuyx.training.util.TrainingUtils;

import java.util.ArrayList;
import java.util.List;

public class TrainingMainActivity extends TrainingBaseActivity {

    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles;
    private long time;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
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
        titles = new String[]{"测试dataBinding", "测试Glide", "测试RxJava", "EventBus", "测试Retrofit", "碎片6"};

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles));
        viewPager.setOffscreenPageLimit(6);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        drawerLayout = (DrawerLayout) findViewById(R.id.training_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        handleToolbar();
        //配置ActionBarDrawerToggle
        toggleSettings();
    }

    private void handleToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TrainingUtils.setTitlePaddingView(findViewById(R.id.padding_view));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, TrainingEventActivity.class);
        switch (item.getItemId()) {
            case R.id.about: //关于
                intent.putExtra(TrainingConstants.FRAGMENT_FLAG, TrainingConstants.TRAINING_ABOUT_FRAGMENT);
                break;
            case R.id.feedback:
                showSnakeBar("意见反馈", toolbar);
                break;
            case R.id.notification:
                showSnakeBar("通知", toolbar);
                break;
            case R.id.search:
                showSnakeBar("搜索", toolbar);
                break;
            default:
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    private void showSnakeBar(String text, Toolbar toolbar) {
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
    }

    private void toggleSettings() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.training_open, R.string.training_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();//初始化状态
    }

    private void initListener() {
        mNavigationView.setNavigationItemSelectedListener(item ->  {
                Intent intent = new Intent(TrainingMainActivity.this,TrainingEventActivity.class);
                switch (item.getItemId()) {
                    case R.id.training_position://定位界面
                        intent.putExtra(TrainingConstants.FRAGMENT_FLAG, TrainingConstants.TRAINING_POSITION_FRAGMENT);
                        break;
                    case R.id.training_collect://收藏界面
                        intent.putExtra(TrainingConstants.FRAGMENT_FLAG, TrainingConstants.TRAINING_COLLECTION_FRAGMENT);
                        break;
                    case R.id.training_tools://工具界面
                        intent.putExtra(TrainingConstants.FRAGMENT_FLAG, TrainingConstants.TRAINING_TOOLS_FRAGMENT);
                        break;
                }
                TrainingMainActivity.this.startActivity(intent);
                return false;
            });
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void loadFragment() {

    }

    private void onClickLeftView() {
        long exitTime = System.currentTimeMillis();
        if (exitTime - time < 2000) {
            this.finish();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            time = exitTime;
        }
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

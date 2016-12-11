package com.zhuyx.training.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 闪屏页，不需要layout.xml
 * Created by zhuyingxin on 2016/11/5.
 * email : rixtdqqq_2015@163.com
 */

public class TrainingSplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, TrainingLoginActivity.class));
        this.finish();
    }
}

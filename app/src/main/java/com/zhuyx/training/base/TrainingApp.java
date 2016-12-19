package com.zhuyx.training.base;

import android.app.Application;

import com.zhuyx.training.util.TrainingConstants;
import com.zhuyx.training.util.TrainingUtils;

/**
 * 程序入口
 * Created by Administrator on 2016/12/18.
 */

public class TrainingApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TrainingUtils.saveUsernameAndPwd(this, TrainingConstants.USERNAME, TrainingConstants.PASSWORD);
    }
}

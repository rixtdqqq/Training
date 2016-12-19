package com.zhuyx.training.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 工具类
 * Created by zhuyingxin on 2016/10/16.
 * email : rixtdqqq_2015@163.com
 */

public final class TrainingUtils {
    /**
     * 将资源id转化为Uri
     *
     * @param context    上下文
     * @param resourceId 资源id
     * @return uri
     */
    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(TrainingConstants.ANDROID_RESOURCE + context.getPackageName() + TrainingConstants.FORWARD_SLASH + resourceId);
    }

    /**
     * 保存用户名和密码
     */
    public static void saveUsernameAndPwd(Context context, String username, String password) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TrainingConstants.USERNAME_KEY, username);
        editor.putString(TrainingConstants.PASSWORD_KEY, password);
        editor.commit();
    }

    public static Map<String, String> getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String username = preferences.getString(TrainingConstants.USERNAME_KEY, "");
        String password = preferences.getString(TrainingConstants.PASSWORD_KEY, "");
        Map<String, String> user = new HashMap<>();
        user.put(TrainingConstants.USERNAME_KEY, username);
        user.put(TrainingConstants.PASSWORD_KEY, password);
        return user;
    }

    /**
     * 分享功能
     *
     * @param context       上下文
     * @param activityTitle Activity的名字
     * @param msgTitle      消息标题
     * @param msgText       消息内容
     * @param imgPath       图片路径，不分享图片则传null
     */
    public void shareMsg(Context context, String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, activityTitle));
    }

    public static void showMsg(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }
}

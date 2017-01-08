package com.zhuyx.training.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.zhuyx.training.entity.TrainingEventEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 19以上版本标题显示
     */
    public static void setTitlePaddingView(View view) {
        if (Build.VERSION_CODES.KITKAT >= Build.VERSION.SDK_INT) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static Map<String, List<TrainingEventEntity>> dealResult(Context context, String result) {
        List<TrainingEventEntity> list = dealStringResult(result, context);
        Map<String, List<TrainingEventEntity>> listMap = new HashMap<>();
        for (int i = 0, size = list.size(); i < size; i++) {
            TrainingEventEntity entity = list.get(i);
            String id = entity.getId();
            if (listMap.containsKey(id)) {
                List<TrainingEventEntity> entities = listMap.get(id);
                entities.add(entity);
                listMap.put(id, entities);
            } else {
                List<TrainingEventEntity> entities = new ArrayList<>();
                entities.add(entity);
                listMap.put(id, entities);
            }
        }
        return listMap;
    }

    private static List<TrainingEventEntity> dealStringResult(String result, Context context) {
        List<TrainingEventEntity> list = new ArrayList<>();
        try {
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            JSONObject object = new JSONObject(result);
            String msg = "";
            String status = "";
            if (object.has("msg")) {
                msg = object.getString("msg");
            }
            if (object.has("status")) {
                status = object.getString("status");
            }
            if (TextUtils.equals("1", status)) {
                JSONArray array = object.getJSONArray("data");
                TrainingEventEntity entity;
                for (int i = 0, length = array.length(); i < length; i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    entity = new TrainingEventEntity(jsonObject.getString("name"), jsonObject.getString("id"));
                    list.add(entity);
                }
            } else {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}

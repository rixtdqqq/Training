package com.zhuyx.training.util;

import android.content.Context;
import android.net.Uri;

/**
 * 工具类
 * Created by zhuyingxin on 2016/10/16.
 * email : rixtdqqq_2015@163.com
 */

public final class TrainingUtils {
    /**
     * 将资源id转化为Uri
     * @param context 上下文
     * @param resourceId 资源id
     * @return uri
     */
    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(TrainingConstants.ANDROID_RESOURCE + context.getPackageName() + TrainingConstants.FORWARD_SLASH + resourceId);
    }

}

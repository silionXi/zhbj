package com.silion.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by silion on 2016/8/22.
 */
public class CacheUtils {
    public static void setCache(Context context, String key, String value) {
        //可以将缓存放在文件中, 文件名就是Md5(url), 文件内容是json
        SharedPreferences pref = context.getSharedPreferences("cache", Context.MODE_PRIVATE);
        pref.edit().putString(key, value).apply();
    }

    public static String getCache(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences("cache", Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }
}

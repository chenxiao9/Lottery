package com.chen.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class PrefUtils {
    private static final String PRENAME ="config";

    public static boolean getBoolen(Context ctx,String key,boolean defValue){
        SharedPreferences sp= ctx.getSharedPreferences(PRENAME,MODE_PRIVATE);
        boolean result=sp.getBoolean(key,defValue);
        return result;
    }

    public static void setBoolen(Context ctx,String key,boolean Value){
        SharedPreferences sp= ctx.getSharedPreferences(PRENAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(key,false);
        editor.apply();
    }
}

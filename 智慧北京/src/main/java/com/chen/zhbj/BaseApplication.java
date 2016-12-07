package com.chen.zhbj;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.zhbj.util.Theme;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class BaseApplication extends Application {
    public BaseApplication() {
    }

    static Context mContext;
    static Resources _resource;
    private static String lastToast = "";
    private static long lastToastTime;
    private static int MyTheme = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        _resource = mContext.getResources();
        getLocalData();

    }

    private void getLocalData() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_settings",MODE_PRIVATE);
        MyTheme = sharedPreferences.getInt("theme",0);

    }
    public static int getThemeValue(){
        return MyTheme;
    }

    public static void setThemeValue(int themeValue){
        MyTheme = themeValue;
    }

    public static int getThemeResources(){
        switch (MyTheme){
            case Theme.DAY_THEME:
                return Theme.RESOURCES_DAY_THEME;
            case Theme.NIGHT_THEME:
                return Theme.RESOURCES_NIGHT_THEME;
            default:
                return Theme.RESOURCES_DAY_THEME;
        }
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) mContext;
    }

    public static String string(int id) {
        return _resource.getString(id);
    }

    public static String string(int id, Object... args) {
        return _resource.getString(id, args);
    }

    public static void showToast(int message) {
        showToast(message, Toast.LENGTH_LONG, 0);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon);
    }

    public static void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, Gravity.BOTTOM);
    }

    public static void showToastShort(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showToastShort(int message, Object... args) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM, args);
    }

    public static void showToast(int message, int duration, int icon) {
        showToast(message, duration, icon, Gravity.BOTTOM);
    }

    public static void showToast(int message, int duration, int icon, int gravity) {
        showToast(context().getString(message), duration, icon, gravity);
    }

    public static void showToast(int message, int duration, int icon, int gravity, Object... args) {
        showToast(context().getString(message, args), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon, int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast) || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(context()).inflate(R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv)).setImageResource(icon);
                    ((ImageView) view.findViewById(R.id.icon_iv)).setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(context());
                toast.setView(view);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 35);
                }

                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }

}

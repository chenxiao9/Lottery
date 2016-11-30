package com.chen.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.chen.zhbj.base.BasePager;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        ivMenu.setVisibility(View.GONE);
        tvTitle.setText("设置");
        setSlideMenuEnable(false);

        TextView tv=new TextView(mActivity);
        tv.setText("设置");
        tv.setTextColor(Color.RED);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
//        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);

        //向Framelayout添加
        flPageContent.addView(tv);
    }
}

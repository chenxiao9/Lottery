package com.chen.zhbj.basePage.impl;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.chen.zhbj.basePage.BasePager;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class HomePager extends BasePager {
    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        ivMenu.setVisibility(View.GONE);
        tvTitle.setText("智慧北京");
        setSlideMenuEnable(false);

        TextView tv=new TextView(mActivity);
        tv.setText("首页");
        tv.setTextColor(Color.RED);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
//        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);

        //向Framelayout添加
        flPageContent.addView(tv);
    }
}

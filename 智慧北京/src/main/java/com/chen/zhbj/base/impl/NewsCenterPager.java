package com.chen.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.chen.zhbj.base.BasePager;


/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class NewsCenterPager extends BasePager {
    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("新闻中心");
        setSlideMenuEnable(true);

        TextView tv=new TextView(mActivity);
        tv.setText("新闻");
        tv.setTextColor(Color.RED);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
//        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);

        //向Framelayout添加
        flPageContent.addView(tv);
    }

    private void getDataFromServer(){
    }

    protected void parseData(){
    }
}

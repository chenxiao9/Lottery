package com.chen.zhbj.basePage.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.chen.zhbj.R;


public class ZhihuDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(App.getThemeResources());
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
        setContentView(R.layout.layout_frame);

        ZhihuDetailFragment fragment = ZhihuDetailFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();

        new ZhihuDetailPresenter(this, fragment);

    }
}
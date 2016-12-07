package com.chen.zhbj.innerbrowser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chen.zhbj.R;


/**
 * Created by Lizhaotailang on 2016/8/30.
 */

public class InnerBrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        setTheme(App.getThemeResources());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frame);

//        Theme.setStatusBarColor(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, InnerBrowserFragment.getInstance()).commit();

    }

}

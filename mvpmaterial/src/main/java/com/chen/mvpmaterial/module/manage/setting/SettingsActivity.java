package com.chen.mvpmaterial.module.manage.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.chen.mvpmaterial.R;
import com.chen.mvpmaterial.injector.components.DaggerDownloadComponent;
import com.chen.mvpmaterial.injector.modules.DownloadModule;
import com.chen.mvpmaterial.module.base.BaseSwipeBackActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class SettingsActivity extends BaseSwipeBackActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    public static void launch(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar,true,"设置");
    }

    @Override
    protected void updateViews() {
        getFragmentManager().beginTransaction().replace(R.id.settings_view, new SettingsFragment()).commit();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_right_exit);
    }
}

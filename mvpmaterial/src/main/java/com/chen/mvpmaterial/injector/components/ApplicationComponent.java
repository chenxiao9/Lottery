package com.chen.mvpmaterial.injector.components;

import android.content.Context;


import com.chen.mvpmaterial.injector.modules.ApplicationModule;
import com.chen.mvpmaterial.local.table.DaoSession;
import com.chen.mvpmaterial.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by long on 2016/8/19.
 * Application Component
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

//    void inject(BaseActivity baseActivity);

    // provide
    Context getContext();
    RxBus getRxBus();
    DaoSession getDaoSession();
}

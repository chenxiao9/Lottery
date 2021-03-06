package com.chen.mvpmaterial.injector.modules;

import android.content.Context;

import com.chen.mvpmaterial.AndroidApplication;
import com.chen.mvpmaterial.local.table.DaoSession;
import com.chen.mvpmaterial.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/19.
 * Application Module
 */
@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;
    private final DaoSession mDaoSession;
    private final RxBus mRxBus;

    public ApplicationModule(AndroidApplication application, DaoSession daoSession, RxBus rxBus) {
        mApplication = application;
        mDaoSession = daoSession;
        mRxBus = rxBus;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplication();
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return mRxBus;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession() {
        return mDaoSession;
    }
}

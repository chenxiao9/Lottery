package com.chen.mvpmaterial.injector.modules;

import com.chen.mvpmaterial.adapter.BaseVideoDLAdapter;
import com.chen.mvpmaterial.adapter.VideoCacheAdpter;
import com.chen.mvpmaterial.injector.PerFragment;
import com.chen.mvpmaterial.local.table.DaoSession;
import com.chen.mvpmaterial.module.base.IRxBusPresenter;
import com.chen.mvpmaterial.module.manage.download.cache.VideoCacheFragment;
import com.chen.mvpmaterial.module.manage.download.cache.VideoCachePresenter;
import com.chen.mvpmaterial.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
@Module
public class VideoCacheModule {
    private final VideoCacheFragment mView;

    public VideoCacheModule(VideoCacheFragment mView) {
        this.mView = mView;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePresenter(DaoSession daoSession, RxBus rxBus){
        return new VideoCachePresenter(mView,daoSession.getVideoInfoDao(),rxBus);
    }

    public BaseVideoDLAdapter provideAdapter(RxBus rxBus){
        return new VideoCacheAdpter(mView.getContext(), rxBus);
    }
}

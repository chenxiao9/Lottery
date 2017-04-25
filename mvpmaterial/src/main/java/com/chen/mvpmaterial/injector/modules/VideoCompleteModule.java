package com.chen.mvpmaterial.injector.modules;


import com.chen.mvpmaterial.adapter.BaseVideoDLAdapter;
import com.chen.mvpmaterial.adapter.VideoCompleteAdapter;
import com.chen.mvpmaterial.injector.PerFragment;
import com.chen.mvpmaterial.local.table.DaoSession;
import com.chen.mvpmaterial.module.base.IRxBusPresenter;
import com.chen.mvpmaterial.module.manage.download.complete.VideoCompleteFragment;
import com.chen.mvpmaterial.module.manage.download.complete.VideoCompletePresenter;
import com.chen.mvpmaterial.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/16.
 * video 缓存完成 Module
 */
@Module
public class VideoCompleteModule {

    private final VideoCompleteFragment mView;

    public VideoCompleteModule(VideoCompleteFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new VideoCompletePresenter(mView, daoSession.getVideoInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public BaseVideoDLAdapter provideAdapter(RxBus rxBus) {
        return new VideoCompleteAdapter(mView.getContext(), rxBus);
    }
}

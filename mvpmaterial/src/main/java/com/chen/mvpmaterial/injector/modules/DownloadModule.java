package com.chen.mvpmaterial.injector.modules;


import com.chen.mvpmaterial.adapter.ViewPagerAdapter;
import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.module.base.IRxBusPresenter;
import com.chen.mvpmaterial.module.manage.download.DownloadActivity;
import com.chen.mvpmaterial.module.manage.download.DownloadPresenter;
import com.chen.mvpmaterial.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/19.
 * video下载Module
 */
@Module
public class DownloadModule {

    private final DownloadActivity mView;

    public DownloadModule(DownloadActivity view) {
        mView = view;
    }

    @PerActivity
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getSupportFragmentManager());
    }

    @PerActivity
    @Provides
    public IRxBusPresenter provideVideosPresenter(RxBus rxBus) {
        return new DownloadPresenter(rxBus);
    }
}

package com.chen.mvpmaterial.injector.modules;

import com.chen.mvpmaterial.adapter.ViewPagerAdapter;
import com.chen.mvpmaterial.injector.PerFragment;
import com.chen.mvpmaterial.local.table.DaoSession;
import com.chen.mvpmaterial.module.base.IRxBusPresenter;
import com.chen.mvpmaterial.module.news.main.NewsMainFragment;
import com.chen.mvpmaterial.module.news.main.NewsMainPresenter;
import com.chen.mvpmaterial.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/22 0022.
 * 新闻主页 Module
 */
@Module
public class NewsMainModule {
    private final NewsMainFragment mView;

    public NewsMainModule(NewsMainFragment mView) {
        this.mView = mView;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter provideMainPresenter(DaoSession daoSession, RxBus rxBus) {
        return new NewsMainPresenter(mView, daoSession.getNewsTypeInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPageAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }
}

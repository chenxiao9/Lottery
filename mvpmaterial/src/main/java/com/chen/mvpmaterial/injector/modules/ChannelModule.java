package com.chen.mvpmaterial.injector.modules;

import com.chen.mvpmaterial.adapter.ManagerAdapter;
import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.local.table.DaoSession;
import com.chen.mvpmaterial.module.news.channel.ChannelActivity;
import com.chen.mvpmaterial.module.news.channel.ChannelPresenter;
import com.chen.mvpmaterial.module.news.channel.IChannelPresenter;
import com.chen.mvpmaterial.rxbus.RxBus;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/6 0006.
 * 频道管理
 */
@Module
public class ChannelModule {
    private final ChannelActivity mView;

    public ChannelModule(ChannelActivity view) {
        mView = view;
    }

    @Provides
    public BaseQuickAdapter providerManageAdapter(){
        return new ManagerAdapter(mView);
    }

    @PerActivity
    @Provides
    public IChannelPresenter providerManagerPresenter(DaoSession daoSession, RxBus rxBus){
        return new ChannelPresenter(mView,daoSession.getNewsTypeInfoDao(),rxBus);
    }

}

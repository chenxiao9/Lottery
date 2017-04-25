package com.chen.mvpmaterial.injector.components;

import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.injector.modules.ChannelModule;
import com.chen.mvpmaterial.module.news.channel.ChannelActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/6 0006.
 * 管理Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ChannelModule.class)
public interface ManagerComponent {
    void inject(ChannelActivity activity);
}

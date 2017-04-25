package com.chen.mvpmaterial.injector.components;

import com.chen.mvpmaterial.injector.PerFragment;
import com.chen.mvpmaterial.injector.modules.NewsMainModule;
import com.chen.mvpmaterial.module.news.main.NewsMainFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/22 0022.
 * 主页 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = NewsMainModule.class)
public interface NewsMainComponent {
    void inject(NewsMainFragment fragment);
}

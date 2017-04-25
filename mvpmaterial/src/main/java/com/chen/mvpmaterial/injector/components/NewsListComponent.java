package com.chen.mvpmaterial.injector.components;

import com.chen.mvpmaterial.injector.PerFragment;
import com.chen.mvpmaterial.injector.modules.NewsListModule;
import com.chen.mvpmaterial.module.news.newslist.NewsListFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/30 0030.
 * 新闻列表
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = NewsListModule.class)
public interface NewsListComponent {
    void inject(NewsListFragment fragment);
}

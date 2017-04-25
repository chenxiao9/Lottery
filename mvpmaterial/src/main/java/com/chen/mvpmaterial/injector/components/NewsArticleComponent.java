package com.chen.mvpmaterial.injector.components;

import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.injector.modules.NewsArticleModule;
import com.chen.mvpmaterial.module.news.article.NewsArticleActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/1 0001.
 * 新闻详情
 */
@PerActivity
@Component(modules = NewsArticleModule.class)
public interface NewsArticleComponent {
    void inject(NewsArticleActivity activity);
}

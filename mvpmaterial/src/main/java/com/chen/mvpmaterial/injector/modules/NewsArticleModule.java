package com.chen.mvpmaterial.injector.modules;

import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.module.base.IBaseContract;
import com.chen.mvpmaterial.module.news.article.NewsArticleActivity;
import com.chen.mvpmaterial.module.news.article.NewsArticlePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/1 0001.
 * 新闻详情 Module
 */
@Module
public class NewsArticleModule {
    private final String mNewsId;
    private final NewsArticleActivity mView;

    public NewsArticleModule(String mNewsId, NewsArticleActivity mView) {
        this.mNewsId = mNewsId;
        this.mView = mView;
    }

    @PerActivity
    @Provides
    public IBaseContract.presenter providePresenter(){
        return new NewsArticlePresenter(mNewsId,mView);
    }
}

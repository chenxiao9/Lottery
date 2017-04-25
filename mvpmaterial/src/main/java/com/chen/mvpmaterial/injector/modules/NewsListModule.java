package com.chen.mvpmaterial.injector.modules;

import com.chen.mvpmaterial.adapter.NewsMultiListAdapter;
import com.chen.mvpmaterial.injector.PerFragment;
import com.chen.mvpmaterial.module.base.IBaseContract;
import com.chen.mvpmaterial.module.news.newslist.NewsListFragment;
import com.chen.mvpmaterial.module.news.newslist.NewsListPresenter;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/22 0022.
 * 新闻列表 Module
 */
@Module
public class NewsListModule {
    private final NewsListFragment mNewsListView;
    private final String mNewsId;

    public NewsListModule(NewsListFragment mNewsListView, String mNewsId) {
        this.mNewsListView = mNewsListView;
        this.mNewsId = mNewsId;
    }

    @PerFragment
    @Provides
    public IBaseContract.presenter providerPresenter(){
        return new NewsListPresenter(mNewsListView,mNewsId);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter providerAdapter(){
        return new NewsMultiListAdapter(mNewsListView.getContext());
    }
}

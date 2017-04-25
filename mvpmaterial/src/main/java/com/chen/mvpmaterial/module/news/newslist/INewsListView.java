package com.chen.mvpmaterial.module.news.newslist;

import com.chen.mvpmaterial.adapter.item.NewsMultiItem;
import com.chen.mvpmaterial.bean.NewsInfo;
import com.chen.mvpmaterial.module.base.ILoadDataView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22 0022.
 * 新闻列表视图接口
 */

public interface INewsListView extends ILoadDataView<List<NewsMultiItem>> {
    /**
     * 加载广告数据
     * @param newsBean 新闻
     */
    void loadAdData(NewsInfo newsBean);
}

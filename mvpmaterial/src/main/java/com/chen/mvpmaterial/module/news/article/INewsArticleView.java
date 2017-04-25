package com.chen.mvpmaterial.module.news.article;

import com.chen.mvpmaterial.bean.NewsDetailInfo;
import com.chen.mvpmaterial.module.base.IBaseContract;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public interface INewsArticleView extends IBaseContract.View{

    /**
     * 显示数据
     * @param newsDetailBean 新闻详情
     */
    void loadData(NewsDetailInfo newsDetailBean);
}

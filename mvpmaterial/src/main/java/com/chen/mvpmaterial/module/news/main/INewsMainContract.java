package com.chen.mvpmaterial.module.news.main;

import com.chen.mvpmaterial.local.table.NewsTypeInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public interface INewsMainContract {
    interface view{
        /**
         * 显示数据
         * @param checkList     选中栏目
         */
        void loadData(List<NewsTypeInfo> checkList);
    }
}

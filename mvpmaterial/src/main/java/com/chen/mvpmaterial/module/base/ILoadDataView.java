package com.chen.mvpmaterial.module.base;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public interface ILoadDataView<T> extends IBaseContract.View {
    /**
     * 加载数据
     * @param data 数据
     */
    void loadData(T data);

    /**
     * 加载更多
     * @param data 数据
     */
    void loadMoreData(T data);

    /**
     * 没有数据
     */
    void loadNoData();
}

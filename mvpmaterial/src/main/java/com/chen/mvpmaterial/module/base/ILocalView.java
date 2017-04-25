package com.chen.mvpmaterial.module.base;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public interface ILocalView<T> {
    /**
     * 显示数据
     * @param dataList 数据
     */
    void loadData(List<T> dataList);

    /**
     * 没有数据
     */
    void noData();
}

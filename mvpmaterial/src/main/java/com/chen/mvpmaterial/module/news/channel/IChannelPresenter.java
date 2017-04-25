package com.chen.mvpmaterial.module.news.channel;

import com.chen.mvpmaterial.module.base.ILocalPresenter;

/**
 * Created by Administrator on 2017/4/5 0005.
 * 频道 presenter 接口
 */

public interface IChannelPresenter<T> extends ILocalPresenter<T>{
    /**
     * 交换
     * @param fromPos
     * @param toPos
     */
    void swap(int fromPos, int toPos);
}

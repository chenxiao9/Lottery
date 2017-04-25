package com.chen.mvpmaterial.module.base;

import com.chen.mvpmaterial.widget.EmptyLayout;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public interface IBaseContract {
    interface View{
        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        /**
         * 显示网络错误
         * @param onRetryListener 点击监听
         */
        void showNetError(EmptyLayout.OnRetryListener onRetryListener);

        /**
         * 绑定生命周期
         * @param <T>
         * @return
         */
        <T> LifecycleTransformer<T> bindToLife();

    }

    interface presenter{
        /**
         *
         * 获取网络数据，更新界面
         * @param isRefresh 新增参数，用来判断是否为下拉刷新调用，下拉刷新的时候不应该再显示加载界面和异常界面
         */
        void getData();

        /**
         * 加载更多数据
         */
        void getMoreData();
    }
}

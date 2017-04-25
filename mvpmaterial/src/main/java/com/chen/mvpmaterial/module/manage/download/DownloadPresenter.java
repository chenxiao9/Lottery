package com.chen.mvpmaterial.module.manage.download;

import com.chen.mvpmaterial.module.base.IRxBusPresenter;
import com.chen.mvpmaterial.rxbus.RxBus;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class DownloadPresenter implements IRxBusPresenter {
    private final RxBus mRxBus;

    public DownloadPresenter(RxBus mRxBus) {
        this.mRxBus = mRxBus;
    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscribe(eventType, action, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Logger.e(throwable.toString());
            }
        });
        mRxBus.addSubscription(this, subscription);
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public void getMoreData() {

    }
}

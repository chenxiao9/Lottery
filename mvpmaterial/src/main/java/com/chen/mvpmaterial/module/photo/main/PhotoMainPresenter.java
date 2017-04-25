package com.chen.mvpmaterial.module.photo.main;

import com.chen.mvpmaterial.local.table.BeautyPhotoInfoDao;
import com.chen.mvpmaterial.module.base.IRxBusPresenter;
import com.chen.mvpmaterial.rxbus.RxBus;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class PhotoMainPresenter implements IRxBusPresenter {
    private final IPhotoMainView mView;
    private final BeautyPhotoInfoDao mDbDao;
    private final RxBus mRxBus;

    public PhotoMainPresenter(IPhotoMainView mView, BeautyPhotoInfoDao mDbDao, RxBus mRxBus) {
        this.mView = mView;
        this.mDbDao = mDbDao;
        this.mRxBus = mRxBus;
    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {

    }

    @Override
    public void unregisterRxBus() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void getMoreData() {

    }
}

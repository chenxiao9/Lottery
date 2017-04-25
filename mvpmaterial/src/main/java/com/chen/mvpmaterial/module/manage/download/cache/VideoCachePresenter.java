package com.chen.mvpmaterial.module.manage.download.cache;

import com.chen.mvpmaterial.local.table.VideoInfo;
import com.chen.mvpmaterial.local.table.VideoInfoDao;
import com.chen.mvpmaterial.module.base.ILocalView;
import com.chen.mvpmaterial.module.base.IRxBusPresenter;
import com.chen.mvpmaterial.rxbus.RxBus;
import com.chen.mvpmaterial.utils.ListUtils;
import com.dl7.downloaderlib.model.DownloadStatus;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class VideoCachePresenter implements IRxBusPresenter {

    private final ILocalView mView;
    private final VideoInfoDao mDbDao;
    private final RxBus mRxBus;

    public VideoCachePresenter(ILocalView mView, VideoInfoDao mDbDao, RxBus mRxBus) {
        this.mView = mView;
        this.mDbDao = mDbDao;
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
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }

    @Override
    public void getData() {
        mDbDao.queryBuilder().rx()
         .oneByOne()
                .filter(new Func1<VideoInfo, Boolean>() {
                    @Override
                    public Boolean call(VideoInfo videoInfo) {
                        return (videoInfo.getDownloadStatus() != DownloadStatus.NORMAL &&
                                videoInfo.getDownloadStatus() != DownloadStatus.COMPLETE);
                    }
                }).toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<VideoInfo>>() {
                    @Override
                    public void call(List<VideoInfo> videoInfos) {
                        if (ListUtils.isEmpty(videoInfos)){
                            mView.noData();
                        }else {
                            mView.loadData(videoInfos);
                        }
                    }
                });
    }

    @Override
    public void getMoreData() {

    }
}

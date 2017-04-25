package com.chen.mvpmaterial.module.news.photoset;

import com.chen.mvpmaterial.api.RetrofitService;
import com.chen.mvpmaterial.bean.PhotoSetInfo;
import com.chen.mvpmaterial.module.base.IBaseContract;
import com.chen.mvpmaterial.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by Administrator on 2017/4/1 0001.
 * 图集presenter
 */

public class PhotoSetPresenter implements IBaseContract.presenter {

    private final IPhotoSetView mView;
    private final String mPhotoSetId;

    public PhotoSetPresenter(IPhotoSetView mView, String mPhotoSetId) {
        this.mView = mView;
        this.mPhotoSetId = mPhotoSetId;
    }

    @Override
    public void getData() {
        RetrofitService.getPhotoSet(mPhotoSetId)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mView.<PhotoSetInfo>bindToLife())
                .subscribe(new Subscriber<PhotoSetInfo>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.showNetError(new EmptyLayout.OnRetryListener() {
                            @Override
                            public void onRetry() {
                                getData();
                            }
                        });

                    }

                    @Override
                    public void onNext(PhotoSetInfo photoSetInfo) {
                        mView.loadData(photoSetInfo);
                    }
                });
    }

    @Override
    public void getMoreData() {

    }
}

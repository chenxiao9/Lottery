package com.chen.zhbj.basePage.impl;

import com.chen.zhbj.AppContext;
import com.chen.zhbj.domain.ZhihuDailyNews;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class NewsCenterModel implements NewsCenterContract.Model{
    private NewsCenterContract.Presenter presenter;
    private Observable<ZhihuDailyNews> observable;
    private Subscription sub;
    private ZhihuDailyNews news;
    private Observer<ZhihuDailyNews> observer=new Observer<ZhihuDailyNews>() {
        @Override
        public void onCompleted() {
            presenter.onLoadSuccess(news);
        }

        @Override
        public void onError(Throwable e) {
            presenter.onLoadFailure(e);
        }

        @Override
        public void onNext(ZhihuDailyNews zhihuDailyNews) {
            news=zhihuDailyNews;
        }
    };


    @Override
    public void getData() {
        observable = AppContext.getInstance().service.getZhiHuNews();
        sub=observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getHistory(String date){
        sub.unsubscribe();
        observable = AppContext.getInstance().service.getZhiHuHistory(date);
        sub=observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void setPresenter(NewsCenterContract.Presenter presenter) {
        this.presenter=presenter;
    }
}

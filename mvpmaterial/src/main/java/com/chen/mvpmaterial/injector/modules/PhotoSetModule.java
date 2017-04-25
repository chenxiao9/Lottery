package com.chen.mvpmaterial.injector.modules;

import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.module.base.IBaseContract;
import com.chen.mvpmaterial.module.news.photoset.PhotoSetActivity;
import com.chen.mvpmaterial.module.news.photoset.PhotoSetPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/1 0001.
 * 图集 Module
 */
@Module
public class PhotoSetModule {
    private final PhotoSetActivity mView;
    private final String mPhotoSetId;

    public PhotoSetModule(PhotoSetActivity mView, String mPhotoSetId) {
        this.mView = mView;
        this.mPhotoSetId = mPhotoSetId;
    }

    @PerActivity
    @Provides
    public IBaseContract.presenter providePresenter(){
        return new PhotoSetPresenter(mView,mPhotoSetId);
    }
}

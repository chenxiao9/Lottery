package com.chen.mvpmaterial.injector.components;

import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.injector.modules.PhotoSetModule;
import com.chen.mvpmaterial.module.news.photoset.PhotoSetActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/1 0001.
 * 图集 component
 */
@PerActivity
@Component(modules = PhotoSetModule.class)
public interface PhotoSetComponent {
    void inject(PhotoSetActivity activity);
}

package com.chen.mvpmaterial.injector.components;


import com.chen.mvpmaterial.injector.PerFragment;
import com.chen.mvpmaterial.injector.modules.VideoCompleteModule;
import com.chen.mvpmaterial.module.manage.download.complete.VideoCompleteFragment;

import dagger.Component;

/**
 * Created by long on 2016/12/16.
 * video 缓存完成 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VideoCompleteModule.class)
public interface VideoCompleteComponent {
    void inject(VideoCompleteFragment fragment);
}

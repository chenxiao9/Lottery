package com.chen.mvpmaterial.injector.components;

import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.injector.modules.SpecialModule;
import com.chen.mvpmaterial.module.news.special.SpecialActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/30 0030.
 * 专题 Component
 */
@PerActivity
@Component(modules = SpecialModule.class)
public interface SpecialComponent {
    void inject(SpecialActivity activity);
}

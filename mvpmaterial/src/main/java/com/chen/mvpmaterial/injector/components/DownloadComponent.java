package com.chen.mvpmaterial.injector.components;

import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.injector.modules.DownloadModule;
import com.chen.mvpmaterial.module.manage.download.DownloadActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = DownloadModule.class)
public interface DownloadComponent {
    void inject(DownloadActivity activity);
}

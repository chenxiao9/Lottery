package com.chen.mvpmaterial;

import android.app.Application;
import android.content.Context;

import com.chen.mvpmaterial.api.RetrofitService;
import com.chen.mvpmaterial.engine.DownloaderWrapper;
import com.chen.mvpmaterial.injector.components.ApplicationComponent;
import com.chen.mvpmaterial.injector.components.DaggerApplicationComponent;
import com.chen.mvpmaterial.injector.modules.ApplicationModule;
import com.chen.mvpmaterial.local.dao.NewsTypeDao;
import com.chen.mvpmaterial.local.table.DaoMaster;
import com.chen.mvpmaterial.local.table.DaoSession;
import com.chen.mvpmaterial.rxbus.RxBus;
import com.chen.mvpmaterial.utils.DownloadUtils;
import com.chen.mvpmaterial.utils.PreferencesUtils;
import com.chen.mvpmaterial.utils.ToastUtils;
import com.dl7.downloaderlib.DownloadConfig;
import com.dl7.downloaderlib.FileDownloader;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

import java.io.File;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class AndroidApplication extends Application {
    private static final String DB_NAME = "news-db";

    private static ApplicationComponent sAppComponent;
    private static Context sContext;
    private DaoSession mDaoSession;
    // 因为下载那边需要用，这里在外面实例化在通过 ApplicationModule 设置
    private RxBus mRxBus = new RxBus();

    public static Context getContext() {
        return sContext;
    }

    public AndroidApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        _initDatabase();
        _initInjector();
        _initConfig();
    }

    public Application getApplication(){
        return this;
    }

    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }
    /**
     * 初始化注射器
     */
    private void _initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
        sAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, mDaoSession, mRxBus))
                .build();
    }

    /**
     * 初始化数据库
     */
    private void _initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplication(), DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        NewsTypeDao.updateLocalData(getApplication(), mDaoSession);
        DownloadUtils.init(mDaoSession.getBeautyPhotoInfoDao());
    }

    /**
     * 初始化配置
     */
    private void _initConfig() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(getApplication());
            Logger.init("LogTAG");
        }
        RetrofitService.init();
        ToastUtils.init(getApplication());
        DownloaderWrapper.init(mRxBus, mDaoSession.getVideoInfoDao());
        FileDownloader.init(getApplication());
        DownloadConfig config = new DownloadConfig.Builder()
                .setDownloadDir(PreferencesUtils.getSavePath(getApplication()) + File.separator + "video/").build();
        FileDownloader.setConfig(config);
    }
}

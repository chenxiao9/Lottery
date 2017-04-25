package com.chen.mvpmaterial.injector.modules;

import com.chen.mvpmaterial.adapter.SpecialAdapter;
import com.chen.mvpmaterial.injector.PerActivity;
import com.chen.mvpmaterial.module.base.IBaseContract;
import com.chen.mvpmaterial.module.news.special.SpecialActivity;
import com.chen.mvpmaterial.module.news.special.SpecialPresenter;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/30 0030.
 * 专题 Module
 */
@Module
public class SpecialModule {
    private final SpecialActivity mView;
    private final String mSpecialId;

    public SpecialModule(SpecialActivity mView, String mSpecialId) {
        this.mView = mView;
        this.mSpecialId = mSpecialId;
    }

    @PerActivity
    @Provides
    public IBaseContract.presenter provideSpecialPresent() {
        return new SpecialPresenter(mSpecialId,mView);
    }

    @PerActivity
    @Provides
    public BaseQuickAdapter provideSpecialAdapter() {
        return new SpecialAdapter(mView);
    }
}

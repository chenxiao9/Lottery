package com.chen.mvpmaterial.module.manage.download;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chen.mvpmaterial.R;
import com.chen.mvpmaterial.adapter.ViewPagerAdapter;
import com.chen.mvpmaterial.injector.components.DaggerDownloadComponent;
import com.chen.mvpmaterial.injector.modules.DownloadModule;
import com.chen.mvpmaterial.module.base.BaseActivity;
import com.chen.mvpmaterial.module.base.BaseVideoDLFragment;
import com.chen.mvpmaterial.module.base.IRxBusPresenter;
import com.chen.mvpmaterial.module.manage.download.cache.VideoCacheFragment;
import com.chen.mvpmaterial.module.manage.download.complete.VideoCompleteFragment;
import com.chen.mvpmaterial.rxbus.event.VideoEvent;
import com.chen.mvpmaterial.widget.FlexibleViewPager;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.functions.Action1;

import static com.chen.mvpmaterial.utils.CommonConstant.INDEX_KEY;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class DownloadActivity extends BaseActivity<IRxBusPresenter> {
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    FlexibleViewPager mViewPager;
    @BindView(R.id.btn_select_all)
    TextView mBtnSelectAll;
    @BindView(R.id.btn_select_del)
    TextView mBtnSelectDel;
    @BindView(R.id.fl_del_layout)
    FrameLayout mFlDelLayout;
    @BindView(R.id.tv_close_edit)
    TextView mTvCloseEdit;

    @Inject
    ViewPagerAdapter mPagerAdapter;
    private BaseVideoDLFragment mCompleteFragment;
    private BaseVideoDLFragment mCacheFragment;
    private int mIndex;

    public static void launch(Context context, int index) {
        Intent intent = new Intent(context, DownloadActivity.class);
        intent.putExtra(INDEX_KEY, index);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.zoom_in_entry, R.anim.hold);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_download;
    }

    @Override
    protected void initInjector() {
        DaggerDownloadComponent.builder().applicationComponent(getAppComponent())
                .downloadModule(new DownloadModule(this))
                .build().inject(this);
    }

    @Override
    protected void initViews() {
        mIndex = getIntent().getIntExtra(INDEX_KEY, 0);
        initToolBar(mToolBar, true, "下载管理");
        mViewPager.setAdapter(mPagerAdapter);
        mPresenter.registerRxBus(VideoEvent.class, new Action1<VideoEvent>() {
            @Override
            public void call(VideoEvent videoEvent) {
                if (videoEvent.checkStatus != VideoEvent.CHECK_INVALID) {
                    _handleVideoEvent(videoEvent);
                }
            }
        });
    }

    @Override
    protected void updateViews() {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        mCompleteFragment = new VideoCompleteFragment();
        mCacheFragment = new VideoCacheFragment();
        fragments.add(mCompleteFragment);
        fragments.add(mCacheFragment);
        titles.add("已缓存");
        titles.add("缓存中");
        mPagerAdapter.setItems(fragments, titles);
        mTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(mIndex);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterRxBus();
    }

    @Override
    public void onBackPressed() {
        if (mCompleteFragment.exitEditMode() || mCacheFragment.exitEditMode()) {
            enableEditMode(false);
            return;
        }
        super.onBackPressed();
    }

    /**
     * 使能编辑状态
     *
     * @param isEnable
     */
    public void enableEditMode(boolean isEnable) {
        mViewPager.setCanScroll(!isEnable);
        mFlDelLayout.setVisibility(isEnable ? View.VISIBLE : View.GONE);
        mTvCloseEdit.setVisibility(isEnable ? View.VISIBLE : View.GONE);
    }

    /**
     * 处理 VideoEvent，来改变编辑状态UI
     *
     * @param videoEvent
     */
    private void _handleVideoEvent(VideoEvent videoEvent) {
        mBtnSelectDel.setEnabled(videoEvent.checkStatus != VideoEvent.CHECK_NONE);
        mBtnSelectAll.setText(videoEvent.checkStatus == VideoEvent.CHECK_ALL ? "取消全选" : "全选");
        mBtnSelectAll.setSelected(videoEvent.checkStatus == VideoEvent.CHECK_ALL);
    }
}

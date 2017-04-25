package com.chen.mvpmaterial.module.manage.download.cache;

import android.widget.TextView;

import com.chen.mvpmaterial.R;
import com.chen.mvpmaterial.local.table.VideoInfo;
import com.chen.mvpmaterial.module.base.BaseVideoDLFragment;
import com.chen.mvpmaterial.module.base.ILocalView;
import com.chen.mvpmaterial.module.base.IRxBusPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class VideoCacheFragment extends BaseVideoDLFragment<IRxBusPresenter> implements ILocalView<VideoInfo> {

    @BindView(R.id.default_bg)
    TextView mDefaultBg;

    @Override
    public void loadData(List<VideoInfo> dataList) {

    }

    @Override
    public void noData() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_download;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {

    }
}

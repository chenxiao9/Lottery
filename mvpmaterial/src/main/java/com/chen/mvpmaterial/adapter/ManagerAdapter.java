package com.chen.mvpmaterial.adapter;

import android.content.Context;

import com.chen.mvpmaterial.R;
import com.chen.mvpmaterial.local.table.NewsTypeInfo;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6 0006.
 * 界面管理适配器
 */

public class ManagerAdapter extends BaseQuickAdapter<NewsTypeInfo> {

    public ManagerAdapter(Context context) {
        super(context);
    }

    public ManagerAdapter(Context context, List<NewsTypeInfo> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_manage;
    }

    @Override
    protected void convert(BaseViewHolder holder, NewsTypeInfo item) {
        holder.setText(R.id.tv_channel_name, item.getName());
    }
}

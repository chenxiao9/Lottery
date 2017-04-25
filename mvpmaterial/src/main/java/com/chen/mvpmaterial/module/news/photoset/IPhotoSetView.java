package com.chen.mvpmaterial.module.news.photoset;

import com.chen.mvpmaterial.bean.PhotoSetInfo;
import com.chen.mvpmaterial.module.base.IBaseContract;

/**
 * Created by Administrator on 2017/4/1 0001.
 * 图集界面接口
 */

public interface IPhotoSetView extends IBaseContract.View {
    /**
     * 显示数据
     * @param photoSetBean
     */
    void loadData(PhotoSetInfo photoSetBean);
}

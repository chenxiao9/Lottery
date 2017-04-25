package com.chen.mvpmaterial.module.video.main;

import com.chen.mvpmaterial.module.base.IBaseContract;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public interface IVideoMainView {
    /**
     * 更新数据
     * @param lovedCount 收藏数
     */
    void updateLovedCount(int lovedCount);

    /**
     * 更新数据
     * @param downloadCount 下载中个数
     */
    void updateDownloadCount(int downloadCount);
}

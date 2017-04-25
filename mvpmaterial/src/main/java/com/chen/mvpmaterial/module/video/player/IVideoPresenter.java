package com.chen.mvpmaterial.module.video.player;


import com.chen.mvpmaterial.local.table.DanmakuInfo;
import com.chen.mvpmaterial.local.table.VideoInfo;
import com.chen.mvpmaterial.module.base.ILocalPresenter;

/**
 * Created by long on 2016/12/23.
 * Video Presenter
 */
public interface IVideoPresenter extends ILocalPresenter<VideoInfo> {

    /**
     * 添加一条弹幕到数据库
     * @param danmakuInfo
     */
    void addDanmaku(DanmakuInfo danmakuInfo);

    /**
     * 清空该视频所有缓存弹幕
     */
    void cleanDanmaku();
}

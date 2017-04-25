package com.chen.mvpmaterial.module.video.player;


import com.chen.mvpmaterial.local.table.VideoInfo;
import com.chen.mvpmaterial.module.base.IBaseContract;

import java.io.InputStream;

/**
 * Created by long on 2016/12/23.
 * Video接口
 */
public interface IVideoView extends IBaseContract.View {

    /**
     * 获取Video数据
     * @param data 数据
     */
    void loadData(VideoInfo data);

    /**
     * 获取弹幕数据
     * @param inputStream 数据
     */
    void loadDanmakuData(InputStream inputStream);

}

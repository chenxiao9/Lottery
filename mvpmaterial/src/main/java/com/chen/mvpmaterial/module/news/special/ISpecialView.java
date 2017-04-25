package com.chen.mvpmaterial.module.news.special;

import com.chen.mvpmaterial.adapter.item.SpecialItem;
import com.chen.mvpmaterial.bean.SpecialInfo;
import com.chen.mvpmaterial.module.base.IBaseContract;

import java.util.List;


/**
 * Created by long on 2016/8/26.
 * 专题View接口
 */
public interface ISpecialView extends IBaseContract.View {

    /**
     * 显示数据
     * @param specialItems 新闻
     */
    void loadData(List<SpecialItem> specialItems);

    /**
     * 添加头部
     * @param specialBean
     */
    void loadBanner(SpecialInfo specialBean);
}

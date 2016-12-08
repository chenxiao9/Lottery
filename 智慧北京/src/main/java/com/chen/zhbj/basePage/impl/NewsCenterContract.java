package com.chen.zhbj.basePage.impl;

import com.chen.zhbj.basePage.BaseModel;
import com.chen.zhbj.basePage.BasePresenter;
import com.chen.zhbj.basePage.BaseView;
import com.chen.zhbj.domain.ZhihuDailyNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public interface NewsCenterContract {

    interface View extends BaseView<Presenter> {

        void showError(String msg);

        void showLoading();

        void stopLoading();

        void showResults(ArrayList<ZhihuDailyNews.StoriesBean> list);

        void showTop(List<ZhihuDailyNews.TopStoriesBean> topList);

        void showPickDialog();

    }

    interface Presenter extends BasePresenter {

        void loadFromModel();
        void onLoadFailure(Throwable e);
        void onLoadSuccess(ZhihuDailyNews news);
        void startReading(int position);
        void onRefresh();
    }

    interface Model extends BaseModel {
        void setPresenter(Presenter presenter);
        void getHistory(String date);
    }

}

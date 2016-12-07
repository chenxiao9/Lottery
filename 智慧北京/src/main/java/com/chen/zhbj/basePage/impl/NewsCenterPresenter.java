package com.chen.zhbj.basePage.impl;

import android.content.Context;
import android.content.Intent;

import com.chen.zhbj.basePage.detail.ZhihuDetailActivity;
import com.chen.zhbj.domain.ZhihuDailyNews;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class NewsCenterPresenter implements NewsCenterContract.Presenter{

    private NewsCenterContract.Model model;
    private NewsCenterContract.View view;
    private Context context;
    private ZhihuDailyNews news;


    public NewsCenterPresenter(Context context,NewsCenterContract.View view) {
        model=new NewsCenterModel();
        this.context=context;
        this.view=view;
        this.view.setPresenter(this);
        model.setPresenter(this);
    }


    @Override
    public void loadFromModel() {
        model.getData();
        view.showLoading();
    }

    @Override
    public void onLoadFailure(Throwable e) {
        view.showError(e.getMessage());
    }

    @Override
    public void onLoadSuccess(ZhihuDailyNews news) {
        this.news=news;
        view.showResults((ArrayList<ZhihuDailyNews.StoriesBean>) this.news.getStories());
        view.stopLoading();
    }

    @Override
    public void start() {
        loadFromModel();
    }

    @Override
    public void startReading(int position) {
        context.startActivity(new Intent(context, ZhihuDetailActivity.class)
                .putExtra("id",this.news.getStories().get(position).getId()));
    }
}

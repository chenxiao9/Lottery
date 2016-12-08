package com.chen.zhbj.basePage.impl;

import android.content.Context;
import android.content.Intent;

import com.chen.zhbj.basePage.detail.ZhihuDetailActivity;
import com.chen.zhbj.domain.ZhihuDailyNews;
import com.chen.zhbj.util.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class NewsCenterPresenter implements NewsCenterContract.Presenter{

    private NewsCenterContract.Model model;
    private NewsCenterContract.View view;
    private Context context;
    private ZhihuDailyNews news;
    private Date dateLoad;
    private List<ZhihuDailyNews.StoriesBean> storiesBeen=new ArrayList<>();
    private Calendar calendar=Calendar.getInstance();
    private DateFormatter formatter = new DateFormatter();
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);


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
        this.storiesBeen.addAll(news.getStories());
        view.showResults((ArrayList<ZhihuDailyNews.StoriesBean>) this.news.getStories());
        if (news.getTop_stories()!=null)
        view.showTop(this.news.getTop_stories());
        view.stopLoading();
    }

    @Override
    public void start() {
        dateLoad=new Date();
        loadFromModel();
    }

    @Override
    public void startReading(int position) {
        context.startActivity(new Intent(context, ZhihuDetailActivity.class)
                .putExtra("id",this.storiesBeen.get(position).getId()));
    }

    @Override
    public void onRefresh() {
        dateLoad=formatter.getYesterDay(dateLoad);
        model.getHistory(format.format(dateLoad));
    }
}

package com.chen.zhbj.basePage.impl;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chen.zhbj.AppContext;
import com.chen.zhbj.R;
import com.chen.zhbj.adapter.DividerItemDecoration;
import com.chen.zhbj.adapter.ZhihuDailyNewsAdapter;
import com.chen.zhbj.basePage.BasePager;
import com.chen.zhbj.domain.ZhihuDailyNews;
import com.chen.zhbj.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class NewsCenterPager extends BasePager implements NewsCenterContract.View,OnRecyclerViewOnClickListener {
//    @BindView(R.id.vp_news_center)
    ViewPager vpNewsCenter;
//    @BindView(R.id.ryv_news_center)
    RecyclerView ryvNewsCenter;
//    @BindView(R.id.sw_lay_news_center)
    SwipeRefreshLayout swLayNewsCenter;

    private NewsCenterContract.Presenter presenter;
    private ArrayList<ZhihuDailyNews.StoriesBean> list=new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private ZhihuDailyNewsAdapter adapter;

    public NewsCenterPager(Activity activity) {
        super(activity);
        presenter=new NewsCenterPresenter(mActivity,this);
    }

    private View views;


    @Override
    public void initViews() {
        views = View.inflate(mActivity,R.layout.view_news_center , null);
        vpNewsCenter=(ViewPager)views.findViewById(R.id.vp_news_center);
        ryvNewsCenter=(RecyclerView)views.findViewById(R.id.ryv_news_center);
        swLayNewsCenter=(SwipeRefreshLayout)views.findViewById(R.id.sw_lay_news_center);

        swLayNewsCenter.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent);
        mLayoutManager=new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false);
        ryvNewsCenter.setLayoutManager(mLayoutManager);
        ryvNewsCenter.addItemDecoration(new DividerItemDecoration(mActivity,LinearLayoutManager.VERTICAL));
        ryvNewsCenter.setItemAnimator(new DefaultItemAnimator());

        adapter=new ZhihuDailyNewsAdapter(mActivity,list);
        adapter.setItemClickListener(this);
        ryvNewsCenter.setAdapter(adapter);
    }

    @Override
    public void initData() {
        initViews();
        flPageContent.addView(views);
        presenter.start();
    }

    @Override
    public void showError(String msg) {
        swLayNewsCenter.setRefreshing(false);
        Snackbar.make(views,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        swLayNewsCenter.setRefreshing(true);
        AppContext.showToast("正在获取数据");
    }

    @Override
    public void showPickDialog() {

    }

    @Override
    public void showResults(ArrayList<ZhihuDailyNews.StoriesBean> list) {
//        this.list = list;
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        swLayNewsCenter.setRefreshing(false);

    }

    @Override
    public void stopLoading() {
        swLayNewsCenter.setRefreshing(false);

    }


    @Override
    public void setPresenter(NewsCenterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void OnItemClick(View v, int position) {
        presenter.startReading(position);
    }
}

package com.chen.zhbj.basePage.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chen.zhbj.AppContext;
import com.chen.zhbj.R;
import com.chen.zhbj.adapter.DividerItemDecoration;
import com.chen.zhbj.adapter.ZhihuDailyNewsAdapter;
import com.chen.zhbj.basePage.BasePager;
import com.chen.zhbj.domain.ZhihuDailyNews;
import com.chen.zhbj.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

    LinearLayout pointGroup;

    private NewsCenterContract.Presenter presenter;
    private ArrayList<ZhihuDailyNews.StoriesBean> list=new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private ZhihuDailyNewsAdapter adapter;
    private List<ImageView> imageList;

    private List<ZhihuDailyNews.TopStoriesBean> tops;
    /**
     * 上一个页面的位置
     */
    protected int lastPosition;
    private int lastVisibleItem;
    /**
     * 判断是否自动滚动
     */
    private boolean isRunning = false;

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            vpNewsCenter.setCurrentItem(vpNewsCenter.getCurrentItem() + 1);
            if (isRunning) {
                mhandler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };
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
        pointGroup=(LinearLayout)views.findViewById(R.id.point_group);

        swLayNewsCenter.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent);
        swLayNewsCenter.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });

        mLayoutManager=new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false);
        ryvNewsCenter.setLayoutManager(mLayoutManager);
        ryvNewsCenter.addItemDecoration(new DividerItemDecoration(mActivity,LinearLayoutManager.VERTICAL));
        ryvNewsCenter.setItemAnimator(new DefaultItemAnimator());

        ryvNewsCenter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem=mLayoutManager.findLastVisibleItemPosition();

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    presenter.onRefresh();
                }
            }
        });

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
        this.list.addAll(list) ;
        adapter.setList(this.list);
        adapter.notifyDataSetChanged();
        swLayNewsCenter.setRefreshing(false);

    }

    @Override
    public void showTop(List<ZhihuDailyNews.TopStoriesBean> topList) {
        tops=topList;
        imageList = new ArrayList<>();int count=topList.size();

        for (int i=0;i<count; i++) {
            //初始化图片资源
            ZhihuDailyNews.TopStoriesBean bean=topList.get(i);
            ImageView imageView = new ImageView(mActivity);
            Glide.with(mActivity)
                    .load(bean.getImage())
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
            imageList.add(imageView);
            //添加指示点
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 20;

            ImageView point = new ImageView(mActivity);
            point.setBackgroundResource(R.drawable.point_bg);
            point.setLayoutParams(params);

            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointGroup.addView(point);
        }


        vpNewsCenter.setAdapter(new MyAdapter());

        vpNewsCenter.setCurrentItem(0);

        vpNewsCenter.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 页面正在滑动时会不断回调
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 页面切换后调用
             * @param position 新页面的位置
             */
            @Override
            public void onPageSelected(int position) {
                position = position % imageList.size();
                //改变指示点的状态
                //把当前点enbale 为true
                pointGroup.getChildAt(position).setEnabled(true);
                //把上一个点设为false
                pointGroup.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;
            }

            /**
             * 当页面状态改变的时候
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        /**
         * 自动循环
         * 1，定时器Timer
         * 2，开子线程while true 循环
         * 3，ClockManager
         * 4，Handle发送延时信息，实现循环
         */
        isRunning = true;
        mhandler.sendEmptyMessageDelayed(0, 2000);
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

    private class MyAdapter extends PagerAdapter {

        /**
         * 判断view与object对应关系
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {//获得页面总数
            return imageList.size();
        }

        /**
         * 获得相应位置上的view
         *
         * @param container 容器，就是pager本身
         * @param position  位置
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageList.get(position % imageList.size()));
            return imageList.get(position % imageList.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
            object = null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tops.get(position).getTitle();
        }
    }



}

package com.chen.zhbj.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.chen.zhbj.R;
import com.chen.zhbj.basePage.BasePager;
import com.chen.zhbj.basePage.impl.GovAffairsPager;
import com.chen.zhbj.basePage.impl.HomePager;
import com.chen.zhbj.basePage.impl.NewsCenterPager;
import com.chen.zhbj.basePage.impl.SettingPager;
import com.chen.zhbj.basePage.impl.SmartServicePager;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class ContentFragment extends BaseFragment {
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;

    @BindView(R.id.vp_content)
    ViewPager vpContent;

    private List<BasePager> mPageList;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
//        ButterKnife.bind(this,view);
        rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        vpContent = (ViewPager) view.findViewById(R.id.vp_content);
        return view;
    }


    @Override
    public void initData() {
        rgGroup.check(R.id.rb_home);
        mPageList = new ArrayList<>();
        //添加5个子页面
        mPageList.add(new HomePager(mActivity));
        mPageList.add(new NewsCenterPager(mActivity));
        mPageList.add(new SmartServicePager(mActivity));
        mPageList.add(new GovAffairsPager(mActivity));
        mPageList.add(new SettingPager(mActivity));

        vpContent.setAdapter(new ContentAdapter());

        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPageList.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPageList.get(0).initData();//手动初始化首页

        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vpContent.setCurrentItem(0, false);
                        break;
                    case R.id.rb_news:
                        vpContent.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smart:
                        vpContent.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:
                        vpContent.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        vpContent.setCurrentItem(4, false);
                        break;
                }
            }
        });
    }

    private class ContentAdapter extends PagerAdapter {
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mPageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPageList.get(position);
            container.addView(pager.getRootView());
            return pager.getRootView();
        }
    }


}

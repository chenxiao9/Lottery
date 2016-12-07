package com.chen.zhbj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chen.zhbj.util.PrefUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.ll_points_group)
    LinearLayout llPointsGroup;
    @BindView(R.id.view_red_point)
    View viewRedPoint;
    @BindView(R.id.btn_start)
    Button btnStart;

    private int[] imgSrcs = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private ArrayList<ImageView> imgList;
    private int pointWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initViews();

    }

    /**
     * 初始化界面
     */
    private void initViews() {


        imgList = new ArrayList<>();
        for (int src :
                imgSrcs) {
            //初始化引导页界面
            ImageView imgView = new ImageView(getApplicationContext());
            imgView.setImageResource(src);
            imgList.add(imgView);
            //初始化小圆点
            View pointV = new View(getApplicationContext());
            pointV.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(35, 35);
            params.rightMargin = 20;
            pointV.setLayoutParams(params);

            llPointsGroup.addView(pointV);//将原点添加至线性布局
        }

        llPointsGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //当layout()执行结束后回调次方法
                llPointsGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                pointWidth = llPointsGroup.getChildAt(1).getLeft() - llPointsGroup.getChildAt(0).getLeft();
                System.out.println("圆点距离：" + pointWidth);
            }
        });

        viewPage.setAdapter(new GuideAdapter());
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //正在滑动
                System.out.println("当前位置:" + position + ";百分比：" + positionOffset + "；移动距离：" + positionOffsetPixels);
                float length = pointWidth * positionOffset + position * pointWidth;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
                params.leftMargin = (int) length;
                viewRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                //页面被选中
                if (position==imgList.size()-1){
                    btnStart.setVisibility(View.VISIBLE);
                }else {
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //状态发生变化
            }
        });
    }

    @OnClick(R.id.btn_start)
    public void onClick() {
        //已完成新手引导页
        PrefUtils.setBoolen(GuideActivity.this,"is_guide_show",false);
        Intent intent=new Intent(GuideActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imgList.get(position));
            return imgList.get(position);
        }
    }
}

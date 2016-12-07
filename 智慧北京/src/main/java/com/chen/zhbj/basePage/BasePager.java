package com.chen.zhbj.basePage;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.zhbj.AppContext;
import com.chen.zhbj.MainActivity;
import com.chen.zhbj.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页下5个子页的基类
 */
public class BasePager {

    protected Activity mActivity;
    @BindView(R.id.iv_menu)
    protected ImageButton ivMenu;
    @BindView(R.id.tv_title)
    protected TextView tvTitle;
    @BindView(R.id.fl_page_content)
    protected FrameLayout flPageContent;

    private View rootView;//布局对象

    public BasePager(Activity activity) {
        this.mActivity = activity;

        addViews();
    }

    /**
     * 初始化布局
     */
    public void addViews() {
        rootView = View.inflate(mActivity, R.layout.layout_base_page, null);
//        ButterKnife.bind(this, rootView);
        ivMenu=(ImageButton)rootView.findViewById(R.id.iv_menu);
        tvTitle=(TextView)rootView.findViewById(R.id.tv_title);
        flPageContent=(FrameLayout)rootView.findViewById(R.id.fl_page_content);
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    public View getRootView() {
        return rootView;
    }

    /**
     * 设置侧边栏开启或关闭
     * @param enable
     */
    protected void setSlideMenuEnable(boolean enable){
        MainActivity main=(MainActivity)mActivity;
        SlidingMenu menu=main.getSlidingMenu();
        if (enable){
            menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}

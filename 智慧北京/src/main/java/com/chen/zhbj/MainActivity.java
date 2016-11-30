package com.chen.zhbj;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.chen.zhbj.fragment.ContentFragment;
import com.chen.zhbj.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    private final static String FRAGMENTCONTENT = "fragment_contain";
    private final static String FRAGMENTMENU = "fragment_menu";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.left_menu);

        SlidingMenu slidingMenu = getSlidingMenu();//获取侧边栏对象
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏触摸
//        slidingMenu.setSecondaryMenu(R.layout.right_menu);//设置右边栏
        slidingMenu.setMode(SlidingMenu.LEFT);//设置展现模式
        slidingMenu.setBehindOffset(900);//设置预留屏幕宽度

        initFragment();

    }

    /**
     * 初始化fragment，将fragment数据填充给布局文件
     */
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();

        tx.replace(R.id.content_main, new ContentFragment(), FRAGMENTCONTENT);
        tx.replace(R.id.left_menu, new LeftMenuFragment(), FRAGMENTMENU);

        tx.commit();
    }

}

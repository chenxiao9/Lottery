package com.chen.zhbj;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chen.zhbj.util.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.activity_splash)
    RelativeLayout activitySplash;
    @BindView(R.id.img_splash)
    ImageView imgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
        setContentView(R.layout.activity_splash);
//        ButterKnife.bind(this);
        activitySplash=(RelativeLayout)findViewById(R.id.activity_splash);
        imgSplash=(ImageView)findViewById(R.id.img_splash);
        startAni();
    }

    private void startAni(){
//        ObjectAnimator rotate = ObjectAnimator.ofFloat(imgSplash, "rotation", 0f, 360f);
//        ObjectAnimator scale=ObjectAnimator.ofFloat(imgSplash,"scale",0,1f);
        Animator animator= AnimatorInflater.loadAnimator(SplashActivity.this,R.animator.splash_ani);
        animator.setTarget(imgSplash);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
              jumpNextPage();
            }
        });
        animator.start();
    }

    /**
     * 跳转至下一个界面
     */
    private void jumpNextPage(){
        boolean isShowGuide= PrefUtils.getBoolen(SplashActivity.this,"is_guide_show",true);
        if (isShowGuide){
            startActivity(new Intent(SplashActivity.this,GuideActivity.class));
        }else {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        }
        finish();

    }
}

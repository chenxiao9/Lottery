package com.chen.mvpmaterial.module.home;

import android.content.Intent;

import com.chen.mvpmaterial.R;
import com.chen.mvpmaterial.module.base.BaseActivity;
import com.chen.mvpmaterial.utils.RxHelper;
import com.dl7.tag.TagView;

import butterknife.BindView;
import rx.Subscriber;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.tag_skip)
    TagView mTagSkip;

    private boolean mIsSkip = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        mTagSkip.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int i, String s, int i1) {
                _doSkip();
            }
        });
    }

    @Override
    protected void updateViews() {
        RxHelper.countdown(5)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        _doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        _doSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mTagSkip.setText("跳过 "+integer);
                    }
                });
    }

    private void _doSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            finish();
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    @Override
    public void onBackPressed() {
        //不响应后退键
        return;
    }
}

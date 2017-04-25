package com.chen.mvpmaterial.module.news.article;

import com.chen.mvpmaterial.api.RetrofitService;
import com.chen.mvpmaterial.bean.NewsDetailInfo;
import com.chen.mvpmaterial.module.base.IBaseContract;
import com.chen.mvpmaterial.utils.ListUtils;
import com.chen.mvpmaterial.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class NewsArticlePresenter implements IBaseContract.presenter {
    private static final String HTML_IMG_TEMPLATE = "<img src=\"http\" />";
    private final String mNewsId;
    private final INewsArticleView mView;

    public NewsArticlePresenter(String mNewsId, INewsArticleView mView) {
        this.mNewsId = mNewsId;
        this.mView = mView;
    }

    @Override
    public void getData() {
        RetrofitService.getNewsDetail(mNewsId)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .doOnNext(new Action1<NewsDetailInfo>() {
                    @Override
                    public void call(NewsDetailInfo newsDetailInfo) {
                        _handleRichTextWithImg(newsDetailInfo);
                    }
                })
                .compose(mView.<NewsDetailInfo>bindToLife())
                .subscribe(new Subscriber<NewsDetailInfo>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.showNetError(new EmptyLayout.OnRetryListener() {
                            @Override
                            public void onRetry() {
                                getData();
                            }
                        });

                    }

                    @Override
                    public void onNext(NewsDetailInfo newsDetailInfo) {
                        mView.loadData(newsDetailInfo);
                    }
                });
    }

    @Override
    public void getMoreData() {

    }

    /**
     * 处理富文本包含图片的情况
     * @param newsDetailInfo 原始数据
     */
    private void _handleRichTextWithImg(NewsDetailInfo newsDetailInfo){
        if (!ListUtils.isEmpty(newsDetailInfo.getImg())){
            String body=newsDetailInfo.getBody();
            for (NewsDetailInfo.ImgEntity imgEntity:newsDetailInfo.getImg()){
                String ref=imgEntity.getRef();
                String src=imgEntity.getSrc();
                String img = HTML_IMG_TEMPLATE.replace("http",src);
                body=body.replaceAll(ref,img);
                Logger.w(img);
                Logger.w(body);
            }
            newsDetailInfo.setBody(body);
        }
    }
}

package com.chen.zhbj.basePage.detail;

import android.webkit.WebView;

import com.chen.zhbj.basePage.BasePresenter;
import com.chen.zhbj.basePage.BaseView;


/**
 * Created by Lizhaotailang on 2016/9/17.
 */

public interface ZhihuDetailContract {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void stopLoading();

        void showLoadError();

        void showShareError();

        void showResult(String result);

        void showResultWithoutBody(String url);

        void showMainImage(String url);

        void setUsingLocalImage();

        void setTitle(String title);

        void setImageMode(boolean showImage);

        void showBrowserNotFoundError();

        void showTextCopied();

        void showCopyTextError();

    }

    interface Presenter extends BasePresenter {

        void openInBrowser();

        void share();

        void requestData();

        void setId(int id);

        void openUrl(WebView webView, String url);

        void reload();

        void copyText();

    }

}

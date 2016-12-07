package com.chen.zhbj.basePage.detail;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.webkit.WebView;

import com.chen.zhbj.AppContext;
import com.chen.zhbj.BaseApplication;
import com.chen.zhbj.R;
import com.chen.zhbj.customtabs.CustomFallback;
import com.chen.zhbj.customtabs.CustomTabActivityHelper;
import com.chen.zhbj.domain.ZhihuDailyNews;
import com.chen.zhbj.domain.ZhihuDailyStory;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Lizhaotailang on 2016/9/17.
 */

public class ZhihuDetailPresenter implements ZhihuDetailContract.Presenter{

    private ZhihuDetailContract.View view;
    private AppCompatActivity activity;
    private int id;

    private Subscription sub;
    private ZhihuDailyStory detail;
    private SharedPreferences sp;

    private Observer<ZhihuDailyStory> observer=new Observer<ZhihuDailyStory>() {
        @Override
        public void onCompleted() {
            BaseApplication.showToast("ZhiHuDetail信息读取完成");
            view.showResult(convertResult(detail.getBody()));
            view.showMainImage(detail.getImage());
            view.setImageMode(sp.getBoolean("no_picture_mode",false));
            view.setTitle(detail.getTitle());
            view.stopLoading();
        }

        @Override
        public void onError(Throwable e) {
            BaseApplication.showToast(e.getMessage());
            view.showResultWithoutBody(detail.getShare_url());
            view.setUsingLocalImage();
            view.stopLoading();
            view.showLoadError();
        }

        @Override
        public void onNext(ZhihuDailyStory story) {
            detail=story;
        }
    };

    public ZhihuDetailPresenter(AppCompatActivity activity, ZhihuDetailContract.View view) {
        this.activity = activity;
        this.view = view;
        this.view.setPresenter(this);
        sp = activity.getSharedPreferences("user_settings",MODE_PRIVATE);
    }

    @Override
    public void start() {
        requestData();
    }

    @Override
    public void openInBrowser() {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(detail.getShare_url())));
        } catch (android.content.ActivityNotFoundException ex){
            view.showLoadError();
        }
    }

    @Override
    public void share() {
        try {
            Intent shareIntent = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
            String shareText = detail.getTitle() + " " +  detail.getShare_url() + "\t\t\t" + activity.getString(R.string.share_extra);
            shareIntent.putExtra(Intent.EXTRA_TEXT,shareText);
            activity.startActivity(Intent.createChooser(shareIntent, activity.getString(R.string.share_to)));
        } catch (android.content.ActivityNotFoundException ex){
            view.showShareError();
        }
    }

    @Override
    public void requestData() {
        sub = AppContext.getInstance().service.getZhiHuDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void openUrl(WebView webView, String url) {

        if (sp.getBoolean("in_app_browser",true)) {
            CustomTabsIntent.Builder customTabsIntent = new CustomTabsIntent.Builder()
                    .setToolbarColor(activity.getResources().getColor(R.color.colorAccent))
                    .setShowTitle(true);
            CustomTabActivityHelper.openCustomTab(
                    activity,
                    customTabsIntent.build(),
                    Uri.parse(url),
                    new CustomFallback() {
                        @Override
                        public void openUri(Activity activity, Uri uri) {
                            super.openUri(activity, uri);
                        }
                    }
            );
        } else {

            try{
                activity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
            } catch (android.content.ActivityNotFoundException ex){
                view.showBrowserNotFoundError();
            }

        }

    }

    @Override
    public void reload() {
        requestData();
    }

    @Override
    public void copyText() {
        if (detail == null) {
            view.showCopyTextError();
            return;
        }
        ClipboardManager manager = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = null;
        if (Build.VERSION.SDK_INT >= 24) {
            clipData = ClipData.newPlainText("text", Html.fromHtml(detail.getBody(), Html.FROM_HTML_MODE_LEGACY).toString());
        } else {
            clipData = ClipData.newPlainText("text", Html.fromHtml(detail.getBody()).toString());
        }
        manager.setPrimaryClip(clipData);
        view.showTextCopied();
    }


    private String convertResult(String preReuslt) {

        preReuslt = preReuslt.replace("<div class=\"img-place-holder\">", "");
        preReuslt = preReuslt.replace("<div class=\"headline\">", "");

        // 在api中，css的地址是以一个数组的形式给出，这里需要设置
        // in fact,in api,css addresses are given as an array
        // api中还有js的部分，这里不再解析js
        // javascript is included,but here I don't use it
        // 不再选择加载网络css，而是加载本地assets文件夹中的css
        // use the css file from local assets folder,not from network
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";


        // 根据主题的不同确定不同的加载内容
        // load content judging by different theme
        String theme = "<body className=\"\" onload=\"onLoaded()\">";
//        if (App.getThemeValue() == Theme.NIGHT_THEME){
//            theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
//        }

        return new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
                .append("<head>\n")
                .append("\t<meta charset=\"utf-8\" />")
                .append(css)
                .append("\n</head>\n")
                .append(theme)
                .append(preReuslt)
                .append("</body></html>").toString();

    }

}
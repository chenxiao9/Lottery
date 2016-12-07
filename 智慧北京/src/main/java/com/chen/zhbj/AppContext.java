package com.chen.zhbj;


import android.util.Log;

import com.chen.zhbj.network.ApiService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chen.zhbj.network.Api.BASE_URL;
import static com.chen.zhbj.network.Api.SPILT;
import static com.chen.zhbj.network.Api.ZHIHU;
import static com.chen.zhbj.network.Api.ZHIHU_BASE;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class AppContext extends BaseApplication {
    public AppContext() {
    }
    private static final String TAG = "AppContext";

    private static AppContext instance;

    public ApiService service;


    /**
     * 获得当前app运行的AppContext
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }

    //初始化OkHttp3的客户端
    private LogIntercept logIntercept;
    private OkHttpClient client;
    private Retrofit sRetrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initNetwork();
    }

    void initNetwork(){
        logIntercept=new LogIntercept();
        client=new OkHttpClient.Builder()
                .addInterceptor(logIntercept)
                .retryOnConnectionFailure(true).build();
        sRetrofit=new Retrofit.Builder()
                .client(client)
                .baseUrl(ZHIHU_BASE)
                .addConverterFactory(GsonConverterFactory.create())//加入json解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// 使用RxJava作为回调适配器
                .build();
        service = sRetrofit.create(ApiService.class);
    }

    private static class LogIntercept implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            Log.i(TAG, "request: "+request.toString());

            Response response=chain.proceed(request);

            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.i(TAG, "response body:" + content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();

        }
    }
}

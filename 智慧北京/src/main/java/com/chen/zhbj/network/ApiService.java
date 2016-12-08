package com.chen.zhbj.network;

import com.chen.zhbj.domain.ZhihuDailyNews;
import com.chen.zhbj.domain.ZhihuDailyStory;

import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

import static com.chen.zhbj.network.Api.*;


/**
 * Created by Administrator on 2016/11/30 0030.
 */

public interface ApiService {
    @GET(LAST)
    Observable<ZhihuDailyNews> getZhiHuNews();
    @GET("{id}")
    Observable<ZhihuDailyStory> getZhiHuDetail(@Path ("id") int id);
    @GET("before/{date}")
    Observable<ZhihuDailyNews> getZhiHuHistory(@Path ("date") String date);
}
